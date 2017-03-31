package jp.ddd.server.adapter.web.presenter.output.message;

import jp.ddd.server.adapter.web.presenter.output.Json;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class ReadJson implements Json {

    private static final long serialVersionUID = 4329939331917276010L;

    private Long messageId;

    private Integer readUserId;

    private final String readUserName;

    private final String readAt;

}
