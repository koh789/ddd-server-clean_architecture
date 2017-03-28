package jp.ddd.server.domain.entity.room;

import jp.ddd.server.adapter.gateway.dynamodb.table.RoomDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.RoomUserDyn;
import jp.ddd.server.adapter.gateway.rds.entity.RoomRds;
import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.room.core.LastMessageAt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserRds;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;

@Builder
@AllArgsConstructor
@Value
public class Room implements Entity<Room> {
    private static final long serialVersionUID = -5335233503985740063L;

    private final RoomId roomId;

    private final LastMessageAt lastMessageAt;

    private final String name;

    private final UserId userId;

    private final ImmutableList<RoomUser> roomUsers;

    public static Room create(RoomRds roomRds, ImmutableList<RoomUserRds> extRoomUsers) {

        ImmutableList<RoomUser> roomUsers = extRoomUsers.collect(eru -> RoomUser.create(eru));

        return Room.builder()//
          .roomId(new RoomId(roomRds.getId()))//
          .lastMessageAt(new LastMessageAt(roomRds.getLastMessageAt()))//
          .name(roomRds.getName())//
          .userId(new UserId(roomRds.getUserId()))//
          .roomUsers(roomUsers).build();
    }

    public static Room create(RoomDyn roomDyn, ImmutableList<RoomUserDyn> roomUserDynList) {

        val roomUsers = roomUserDynList.collect(ru -> RoomUser.create(ru));

        return Room.builder()//
          .roomId(new RoomId(roomDyn.getRoomId()))//
          .lastMessageAt(new LastMessageAt(roomDyn.getLastMessageAt()))//
          .name(roomDyn.getName())//
          .userId(new UserId(roomDyn.getCreateUserId()))//
          .roomUsers(roomUsers).build();
    }
}