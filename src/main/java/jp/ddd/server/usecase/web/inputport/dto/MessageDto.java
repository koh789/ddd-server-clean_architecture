package jp.ddd.server.usecase.web.inputport.dto;

import jp.ddd.server.domain.entity.core.MessageAt;
import jp.ddd.server.domain.entity.core.MessageId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.other.data.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Value
public class MessageDto implements Dto{

    private final MessageId messageId;

    private final UserId messageUserId;

    private final String messageUserName;

    private final String content;

    private final MessageAt messageAt;


}
