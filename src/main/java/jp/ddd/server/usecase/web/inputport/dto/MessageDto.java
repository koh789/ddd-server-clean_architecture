package jp.ddd.server.usecase.web.inputport.dto;

import jp.ddd.server.adapter.web.presenter.api.output.message.ReadJson;
import jp.ddd.server.domain.entity.core.MessageAt;
import jp.ddd.server.domain.entity.core.MessageId;
import jp.ddd.server.domain.entity.room.core.JoinAt;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.other.data.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

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
