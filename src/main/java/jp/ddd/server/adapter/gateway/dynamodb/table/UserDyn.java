package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.Dyn;
import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.other.utils.Const;
import jp.ddd.server.other.utils.enums.Status;
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
@DynamoDBTable(tableName = "user")
public class UserDyn implements Dyn {
    private static final long serialVersionUID = 5533582298663995575L;

    @DynamoDBHashKey(attributeName = "user_id")
    private Integer userId;

    @DynamoDBIndexHashKey(attributeName = "login_id", globalSecondaryIndexName = Const.IDX_USER_LID)
    private String loginId;

    @DynamoDBAttribute
    private String pass;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private String tel;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private Status status;

    public static UserDyn create(User user) {
        return UserDyn.builder().email(user.getUserInfo().getEmail()).loginId(user.getLoginId().getId())
          .name(user.getUserInfo().getName()).pass(user.getHashPass().getPass()).tel(user.getUserInfo().getTel())
          .status(user.getStatus()).build();
    }
}
