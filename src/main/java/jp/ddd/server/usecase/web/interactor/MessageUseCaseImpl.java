package jp.ddd.server.usecase.web.interactor;

import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.usecase.web.inputport.MessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
@Component
public class MessageUseCaseImpl implements MessageUseCase {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message register(Integer roomId, Integer userId, String content) {
        return messageRepository.register(Message.create(roomId, userId, content));
    }
}
