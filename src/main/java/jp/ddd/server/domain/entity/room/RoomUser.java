package jp.ddd.server.domain.entity.room;

import jp.ddd.server.adapter.gateway.dynamodb.table.RoomUserDyn;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserRds;
import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.room.core.JoinAt;
import jp.ddd.server.domain.entity.room.core.JoinAt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class RoomUser implements Entity<RoomUser> {
    private static final long serialVersionUID = 3373581417552541323L;

    private final RoomId roomId;

    private final UserId userId;

    private final JoinAt joinAt;

    public static RoomUser create(RoomUserRds roomUserRds) {
        return RoomUser.builder() //
          .roomId(new RoomId(roomUserRds.getRoomId())) //
          .userId(new UserId(roomUserRds.getUserId()))//
          .joinAt(new JoinAt(roomUserRds.getJoinAt())).build();//
    }

    public static RoomUser create(RoomUserDyn roomUserDyn) {
        return RoomUser.builder() //
          .roomId(new RoomId(roomUserDyn.getRoomId())) //
          .userId(new UserId(roomUserDyn.getUserId()))//
          .joinAt(new JoinAt(roomUserDyn.getJoinAt())).build();
    }
}