package jp.ddd.server.adapter.web.presenter.api.impl;

import jp.ddd.server.adapter.web.presenter.api.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.api.output.message.RegisteredMessageJson;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.usecase.web.outputport.MessagePresenter;
import org.springframework.stereotype.Component;

/**
 * Created by noguchi_kohei 
 */
@Component
public class MessagePresenterImpl implements MessagePresenter {

    public ResultJson<RegisteredMessageJson> toRegisteredJson(Message message) {
        return ResultJson.create(RegisteredMessageJson.create(message));
    }
}
