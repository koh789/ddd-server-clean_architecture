package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.Dyn;
import jp.ddd.server.other.utils.Dates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageReadDyn implements Dyn {
    private static final long serialVersionUID = 8630209538476323532L;
    private Long messageId;
    private Integer userId;
    private String readAt;

    @DynamoDBIgnore
    public Date getReadAdWithConvert() {
        return Dates.toDate(this.readAt, Dates.DEFAULT_DATE_FORMAT);
    }

    public static MessageReadDyn create(Long messageId, Integer userId, Date readAt) {
        return MessageReadDyn.builder().messageId(messageId).userId(userId)
          .readAt(Dates.toString(readAt, Dates.DEFAULT_DATE_FORMAT)).build();
    }
}
