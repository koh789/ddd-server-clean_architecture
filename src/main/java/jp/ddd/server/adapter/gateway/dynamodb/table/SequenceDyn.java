package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.Dyn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by noguchi_kohei 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamoDBTable(tableName = "sequence")
public class SequenceDyn implements Dyn {
    private static final long serialVersionUID = 4034253252658415216L;

    @DynamoDBHashKey(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "current_num")
    private Long currentNum;

    public static SequenceDyn create(String name, Long currentNum) {
        return SequenceDyn.builder().name(name).currentNum(currentNum).build();
    }
}
