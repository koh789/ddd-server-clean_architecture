package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.adapter.gateway.dynamodb.table.RoomDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.RoomUserDyn;
import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.enums.Status;
import jp.ddd.server.usecase.gateway.dynamodb.MessageDynGateway;
import jp.ddd.server.usecase.gateway.dynamodb.RoomDynGateway;
import jp.ddd.server.usecase.gateway.dynamodb.RoomUserDynGateway;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * {@link RoomRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Component
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private RoomDynGateway roomDynGateway;
    @Autowired
    private RoomUserDynGateway roomUserDynGateway;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Room register(UserId userId, String roomName, ImmutableList<UserId> joinUserIds) {
        val now = Dates.now();
        val roomDynResult = roomDynGateway
          .saveWithIncrementKey(RoomDyn.create(userId.getId(), roomName, now, Status.VALID));
        ImmutableList<RoomUserDyn> roomUserDynResults = joinUserIds.distinct() //
          .collect(uid -> RoomUserDyn.create(roomDynResult.getRoomId(), uid.getId(), now, now))
          .collect(ru -> roomUserDynGateway.save(ru));

        return Room.create(roomDynResult, roomUserDynResults);
    }

    @Override
    public Optional<Room> getOpt(RoomId roomId) {
        return roomDynGateway.getOptByRoomId(roomId.getId()).map(r -> {
            val roomUserDynList = DsLists.toImt(roomUserDynGateway.findByRoomId(roomId.getId()));
            return Room.create(r, roomUserDynList);
        });
    }
}
