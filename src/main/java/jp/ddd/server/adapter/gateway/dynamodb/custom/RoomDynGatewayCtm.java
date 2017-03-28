package jp.ddd.server.adapter.gateway.dynamodb.custom;

import jp.ddd.server.adapter.gateway.dynamodb.table.RoomDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomDynGatewayCtm {

    RoomDyn saveWithIncrementKey(RoomDyn roomDyn);
}
