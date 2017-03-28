package jp.ddd.server.adapter.web.presenter.api.output.message;

import jp.ddd.server.adapter.web.presenter.api.output.Json;
import jp.ddd.server.other.utils.Dates;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class MessagesJson implements Json {

    private Integer roomId;

    private Long messageId;

    private Integer messageUserId;

    private String messageUserName;

    private String content;

    private String messageAt;

    private List<ReadJson> reads;

}
