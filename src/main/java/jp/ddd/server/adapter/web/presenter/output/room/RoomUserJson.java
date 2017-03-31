package jp.ddd.server.adapter.web.presenter.output.room;

import jp.ddd.server.adapter.web.presenter.output.Json;
import jp.ddd.server.adapter.web.presenter.output.message.MessagesJson;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class RoomUserJson implements Json {
    private static final long serialVersionUID = 910139523300534581L;
    private Integer roomId;
    private Integer userId;
    private String joinAt;
    private List<MessagesJson> messages;
}
