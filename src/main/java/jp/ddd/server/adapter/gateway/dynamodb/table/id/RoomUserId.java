package jp.ddd.server.adapter.gateway.dynamodb.table.id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomUserId implements Serializable {
    private static final long serialVersionUID = 5232389369383066987L;
    @DynamoDBHashKey(attributeName = "room_id")
    private Integer roomId;
    @DynamoDBRangeKey(attributeName = "user_id")
    private Integer userId;
}
