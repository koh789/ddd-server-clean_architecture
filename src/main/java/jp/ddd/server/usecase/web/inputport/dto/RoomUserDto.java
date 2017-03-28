package jp.ddd.server.usecase.web.inputport.dto;

import jp.ddd.server.domain.entity.room.core.JoinAt;
import jp.ddd.server.domain.entity.room.core.JoinAt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.other.data.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Value
public class RoomUserDto implements Dto{


    private final String userName;

    private final UserId userId;

    private final LoginId loginId;

    private final JoinAt joinAt;
}
