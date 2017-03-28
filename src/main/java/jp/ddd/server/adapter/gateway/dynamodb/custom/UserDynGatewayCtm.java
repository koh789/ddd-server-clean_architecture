package jp.ddd.server.adapter.gateway.dynamodb.custom;

import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface UserDynGatewayCtm {

    UserDyn saveWithIncrementKey(UserDyn userDyn);

    Optional<UserDyn> getOptByLoginId(String loginId);

}
