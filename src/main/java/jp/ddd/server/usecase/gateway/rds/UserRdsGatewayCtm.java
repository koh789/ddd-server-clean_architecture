package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.UserRds;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface UserRdsGatewayCtm {

    Optional<UserRds> getOpt(String loginId);

    Optional<UserRds> getOpt(String loginId, String hashedPass);

}
