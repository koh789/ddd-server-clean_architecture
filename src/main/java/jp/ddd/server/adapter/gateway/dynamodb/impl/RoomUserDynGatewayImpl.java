package jp.ddd.server.adapter.gateway.dynamodb.impl;

import jp.ddd.server.adapter.gateway.dynamodb.custom.RoomUserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.RoomUserDyn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class RoomUserDynGatewayImpl implements RoomUserDynGatewayCtm {

    @Autowired
    private DynamoDbClient<RoomUserDyn> dynamoDbClient;

}
