package jp.ddd.server.domain.entity.message;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.adapter.gateway.rds.entity.MessageRds;
import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.core.LastEditAt;
import jp.ddd.server.domain.entity.core.MessageAt;
import jp.ddd.server.domain.entity.core.MessageId;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.DsMaps;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

/**
 * The persistent class for the message database table.
 */
@Builder
@AllArgsConstructor
@Value
public class Message implements Entity<Message> {
    private static final long serialVersionUID = 5227353660764112526L;

    private final MessageId messageId;

    private final RoomId roomId;

    private final String content;

    private final LastEditAt lastEditAt;

    private final MessageAt messageAt;

    private final UserId userId;

    private final Status status;

    private final ImmutableList<MessageRead> messageReads;

    public static Message create(MessageRds messageRds) {
        return Message.builder()//
          .messageId(new MessageId(messageRds.getId()))//
          .roomId(new RoomId(messageRds.getRoomId()))//
          .content(messageRds.getContent())//
          .lastEditAt(new LastEditAt(messageRds.getLastEditAt()))//
          .messageAt(new MessageAt(messageRds.getMessageAt()))//
          .userId(new UserId(messageRds.getUserId())).build();
    }

    public static Message create(MessageDyn messageDyn) {
        val messageReads = DsLists.toImt(messageDyn.getUserReadMap().values()).collect(ur -> MessageRead.create(ur));
        return Message.builder()//
          .messageId(new MessageId(messageDyn.getMessageId()))//
          .roomId(new RoomId(messageDyn.getRoomId()))//
          .content(messageDyn.getContent())//
          .lastEditAt(new LastEditAt(messageDyn.getLastEditAt()))//
          .messageAt(new MessageAt(messageDyn.getMessageAt()))//
          .userId(new UserId(messageDyn.getUserId())).messageReads(messageReads) //
          .build();
    }

    public static Message create(Integer roomId, Integer userId, String content) {
        val now = Dates.now();
        return Message.builder()//
          .roomId(new RoomId(roomId))//
          .content(content)//
          .lastEditAt(new LastEditAt(now))//
          .messageAt(new MessageAt(now))//
          .userId(new UserId(userId)) //
          .messageReads(Lists.immutable.empty()).build();
    }
}