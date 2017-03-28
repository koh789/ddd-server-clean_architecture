package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.Dyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.converter.DateDynamoDbConverter;
import jp.ddd.server.adapter.gateway.dynamodb.table.id.RoomUserId;
import jp.ddd.server.other.utils.Const;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamoDBTable(tableName = "room_user")
public class RoomUserDyn implements Dyn {
    private static final long serialVersionUID = 5215040292663232116L;

    @Id
    @DynamoDBIgnore
    private RoomUserId roomUserId = new RoomUserId();

    @DynamoDBHashKey(attributeName = "room_id")
    public Integer getRoomId() {
        return this.roomUserId.getRoomId();
    }

    public void setRoomId(Integer roomId) {
        this.roomUserId.setRoomId(roomId);
    }

    @DynamoDBRangeKey(attributeName = "user_id")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = Const.ROOM_USER_UID_LASTAT, attributeName = "user_id")
    public Integer getUserId() {
        return this.roomUserId.getUserId();
    }

    public void setUserId(Integer userId) {
        this.roomUserId.setUserId(userId);
    }

    /** yyyy-MM-dd HH:mm:ss */
    @DynamoDBAttribute(attributeName = "last_room_message_at")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = Const.ROOM_USER_UID_LASTAT, attributeName = "last_room_message_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date lastRoomMessageAt;

    /** yyyy-MM-dd HH:mm:ss */
    @DynamoDBAttribute(attributeName = "join_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date joinAt;

    public static RoomUserDyn create(Integer roomId, Integer userId, Date joinAt, Date lastRoomMessageAt) {
        return RoomUserDyn.builder().joinAt(joinAt).lastRoomMessageAt(lastRoomMessageAt)
          .roomUserId(RoomUserId.builder().roomId(roomId).userId(userId).build()).build();
    }
}
