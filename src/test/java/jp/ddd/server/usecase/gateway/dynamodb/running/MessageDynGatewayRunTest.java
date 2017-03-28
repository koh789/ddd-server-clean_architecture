package jp.ddd.server.usecase.gateway.dynamodb.running;

import jp.ddd.server.other.data.common.IdPage;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.usecase.gateway.dynamodb.MessageDynGateway;
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
public class MessageDynGatewayRunTest {
    @Autowired
    private MessageDynGateway messageDynGateway;

    @Test
    public void findDescTest() {
        try {
            val limit = 2;
            val results = messageDynGateway.findDesc(1, IdPage.init(limit));
            val lastMessageIdOpt = DsLists.getLastOpt(results).map(result -> result.getMessageId());
            lastMessageIdOpt.ifPresent(mid -> {
                val secondResults = messageDynGateway.findDesc(1, IdPage.createWithLastId(mid, limit));
                System.out.println(secondResults);
            });
            System.out.println(results);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void updateReadsTest() {
        try {
            messageDynGateway.updateReads(1,3);
        } catch (Exception e) {
            fail();
        }
    }
}
