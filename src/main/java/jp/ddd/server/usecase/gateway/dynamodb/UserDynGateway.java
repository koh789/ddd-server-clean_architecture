package jp.ddd.server.usecase.gateway.dynamodb;

import jp.ddd.server.adapter.gateway.dynamodb.custom.UserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import java.util.List;
import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@EnableScan
public interface UserDynGateway extends DynamoDBPagingAndSortingRepository<UserDyn, String>, UserDynGatewayCtm {


}
