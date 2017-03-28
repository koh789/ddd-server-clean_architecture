package jp.ddd.server.usecase.gateway.dynamodb;

import com.amazonaws.services.dynamodbv2.document.Page;
import jp.ddd.server.adapter.gateway.dynamodb.custom.MessageDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.domain.PageRequest;

/**
 * Created by noguchi_kohei 
 */
@EnableScan
public interface MessageDynGateway
  extends DynamoDBPagingAndSortingRepository<MessageDyn, String>, MessageDynGatewayCtm {

}
