package jp.ddd.server.adapter.web.presenter.api;

import jp.ddd.server.adapter.web.presenter.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.output.message.RegisteredMessageJson;
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
