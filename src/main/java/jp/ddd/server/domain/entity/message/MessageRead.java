package jp.ddd.server.domain.entity.message;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageReadDyn;
import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.core.MessageId;
import jp.ddd.server.domain.entity.core.ReadAt;
import jp.ddd.server.domain.entity.user.core.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class MessageRead implements Entity<MessageRead> {
    private static final long serialVersionUID = 2377932200497420692L;

    private final ReadAt readAt;

    private final MessageId messageId;

    private final UserId userId;

    public static MessageRead create(MessageReadDyn messageReadDyn) {
        return MessageRead.builder()//
          .readAt(new ReadAt(messageReadDyn.getReadAdWithConvert())) //
          .messageId(new MessageId(messageReadDyn.getMessageId())) //
          .userId(new UserId(messageReadDyn.getUserId())).build();//
    }
}