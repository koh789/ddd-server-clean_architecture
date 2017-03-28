package jp.ddd.server.usecase.web.inputport;

import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.usecase.web.inputport.dto.RoomDto;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
public interface RoomUseCase {

    Room register(String loginSessionId, String roomName, ImmutableList<UserId> joinUserIds);

    boolean isRoomUser(Integer roomId, Integer userId);

    /**
     * メッセージルームを読み込みます。
     * 同時に既読情報が更新されます。
     * @param roomId
     * @param userId
     * @return
     */
    RoomDto load(Integer roomId, Integer userId);
}
