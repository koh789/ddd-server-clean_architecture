package jp.ddd.server.adapter.gateway.dynamodb.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import jp.ddd.server.adapter.gateway.dynamodb.custom.UserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import jp.ddd.server.other.utils.Const;
import jp.ddd.server.other.utils.DsLists;
import lombok.val;
import org.eclipse.collections.impl.factory.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class UserDynGatewayImpl implements UserDynGatewayCtm {
    @Autowired
    private DynamoDbClient<UserDyn> dynamoDbClient;

    @Override
    public UserDyn saveWithIncrementKey(UserDyn userDyn) {
        val id = dynamoDbClient.incrementNum(UserDyn.class);
        userDyn.setUserId(id);
        dynamoDbClient.getMapper().save(userDyn);
        return userDyn;
    }

    @Override
    public Optional<UserDyn> getOptByLoginId(String loginId) {

        val queryExpression = new DynamoDBQueryExpression<UserDyn>()//
          .withKeyConditionExpression("login_id=:loginId")
          .withExpressionAttributeValues(Maps.immutable.of(":loginId", new AttributeValue(loginId)).castToMap())
          .withIndexName(Const.IDX_USER_LID)
          .withConsistentRead(false);

        val results = dynamoDbClient.getMapper()
          .queryPage(UserDyn.class, queryExpression, dynamoDbClient.getMapperConfig()).getResults();
        return DsLists.getFirstOpt(results);
    }

}
