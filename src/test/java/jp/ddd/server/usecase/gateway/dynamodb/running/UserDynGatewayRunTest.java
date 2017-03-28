package jp.ddd.server.usecase.gateway.dynamodb.running;

import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import jp.ddd.server.usecase.gateway.dynamodb.UserDynGateway;
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
public class UserDynGatewayRunTest {
    @Autowired
    private UserDynGateway userDynGateway;

    @Test
    public void saveTest() {
        try {
            val userDyn = UserDyn.builder().loginId("dummy@gmail.com").email("dummy@gmail.com")
              .pass("B5A2C96250612366EA272FFAC6D9744AAF4B45AACD96AA7CFCB931EE3B558259").tel("08010001000").build();
            //            val result = userDynGateway.saveWithIncrementKey(userDyn);
            val resultOpt = userDynGateway.getOptByLoginId("dummy@gmail.com");
            System.out.println(resultOpt);
        } catch (Exception e) {
            fail();
        }
    }
}
