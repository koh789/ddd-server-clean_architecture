package jp.ddd.server.adapter.web.presenter.output.message;

import jp.ddd.server.adapter.web.presenter.output.Json;
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
