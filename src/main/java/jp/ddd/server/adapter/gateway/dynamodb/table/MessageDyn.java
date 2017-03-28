package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.Dyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.converter.DateDynamoDbConverter;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.other.utils.Const;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;
import org.eclipse.collections.impl.factory.Maps;

import java.util.Date;
import java.util.Map;

/**
 * Created by noguchi_kohei 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamoDBTable(tableName = "message")
public class MessageDyn implements Dyn {
    private static final long serialVersionUID = 8630209538476323532L;

    @DynamoDBHashKey(attributeName = "message_id")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = Const.IDX_MESSAGE_RID_MID)
    private Long messageId;

    @DynamoDBIndexHashKey(attributeName = "room_id", globalSecondaryIndexName = Const.IDX_MESSAGE_RID_MID)
    private Integer roomId;

    @DynamoDBAttribute(attributeName = "message_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date messageAt;

    @DynamoDBAttribute(attributeName = "user_id")
    private Integer userId;

    private String content;

    @DynamoDBAttribute(attributeName = "last_edit_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date lastEditAt;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private Status status;

    @DynamoDBAttribute(attributeName = "user_read_map")
    @DynamoDBTypeConvertedJson
    private Map<Integer, MessageReadDyn> userReadMap;

    public MessageDyn withMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public static MessageDyn create(Message message) {
        val builder = MessageDyn.builder() //
          .roomId(message.getRoomId().getId())//
          .messageAt(message.getMessageAt().getDate())//
          .userId(message.getUserId().getId()) //
          .content(message.getContent()).lastEditAt(message.getLastEditAt().getDate())//
          .status(message.getStatus()) //
          .userReadMap(Maps.mutable.empty());
        if (message.getMessageId() != null) {
            builder.messageId(message.getMessageId().getId());
        }
        return builder.build();
    }
}
