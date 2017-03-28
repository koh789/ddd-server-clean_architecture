package jp.ddd.server.adapter.web.presenter.api.output.message;

import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.adapter.web.presenter.api.output.Json;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.other.utils.Dates;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class RegisteredMessageJson implements Json {

    private Long messageId;
    /** yyyy/MM/dd hh:mm:ss */
    private String messageAt;
    /** yyyy/MM/dd hh:mm:ss */
    private String lastEditAt;

    public static RegisteredMessageJson create(Message message) {
        return RegisteredMessageJson.builder().messageId(message.getMessageId().getId())
          .messageAt(Dates.toString(message.getMessageAt().getDate()))
          .lastEditAt(Dates.toString(message.getLastEditAt().getDate())).build();
    }
}
