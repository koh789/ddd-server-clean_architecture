package jp.ddd.server.adapter.web.presenter.api.output.room;

import jp.ddd.server.adapter.web.presenter.api.output.Json;
import jp.ddd.server.adapter.web.presenter.api.output.message.MessagesJson;
import jp.ddd.server.domain.entity.room.Room;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class RoomJson implements Json {
    private static final long serialVersionUID = 910139523300534581L;

    private String roomName;

    private String lastMessageAt;

    private List<MessagesJson> messages;
}
