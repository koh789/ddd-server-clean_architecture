package jp.ddd.server.usecase.gateway.dynamodb;

import jp.ddd.server.adapter.gateway.dynamodb.custom.RoomUserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.RoomUserDyn;
import org.eclipse.collections.api.list.ImmutableList;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@EnableScan
public interface RoomUserDynGateway
  extends DynamoDBPagingAndSortingRepository<RoomUserDyn, String>, RoomUserDynGatewayCtm {

    List<RoomUserDyn> findByRoomId(Integer roomId);
}
