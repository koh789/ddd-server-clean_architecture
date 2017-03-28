package jp.ddd.server.usecase.web.inputport;

import jp.ddd.server.domain.entity.message.Message;

/**
 * ユーザのUseCaseに関するInPutPortです。
 * Created by noguchi_kohei
 */
public interface MessageUseCase {

    Message register(Integer roomId, Integer userId, String content);
}
