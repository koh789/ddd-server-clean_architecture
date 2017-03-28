package jp.ddd.server.usecase.web.interactor;

import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.usecase.web.inputport.RoomUseCase;
import jp.ddd.server.usecase.web.inputport.dto.RoomDto;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
@Component
public class RoomUseCaseImpl implements RoomUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Room register(String loginSessionId, String roomName, ImmutableList<UserId> joinUserIds) {
        val loginUserId = userRepository.getOptBySid(loginSessionId) //
          .map(u -> u.getUserId()).orElseThrow(() -> new AuthException());
        return roomRepository.register(loginUserId, roomName, joinUserIds);
    }

    @Override
    public boolean isRoomUser(Integer roomId, Integer userId) {
        return roomRepository.getOpt(new RoomId(roomId))
          .map(room -> room.getRoomUsers().anySatisfy(ru -> ru.getUserId().equals(userId))) //
          .orElseGet(() -> false);
    }

    /**
     * メッセージルームを読み込みます。
     * 同時に既読情報が更新されます。
     * @param roomId
     * @param userId
     * @return
     */
    @Override
    public RoomDto load(Integer roomId, Integer userId) {

        //TODO　実装
        return null;
    }
}
