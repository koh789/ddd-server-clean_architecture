package jp.ddd.server.usecase.gateway.dynamodb.base.running;

import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import jp.ddd.server.usecase.gateway.dynamodb.RoomDynGateway;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

/**
 * Created by noguchi_kohei 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
public class DynamoDbClientTest {
    @Autowired
    private DynamoDbClient<UserDyn> dynamoDbClient;

    @Test
    public void incrementTest() {
        try {
            val result = dynamoDbClient.incrementNum(UserDyn.class);
            System.out.println(result);
        } catch (Exception e) {
            fail();
        }
    }
}
