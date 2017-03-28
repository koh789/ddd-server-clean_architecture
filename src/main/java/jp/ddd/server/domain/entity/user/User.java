package jp.ddd.server.domain.entity.user;

import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import jp.ddd.server.adapter.gateway.rds.entity.UserRds;
import jp.ddd.server.adapter.web.controller.input.user.UserForm;
import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Builder
@AllArgsConstructor
@Value
public class User implements Entity<User> {
    private static final long serialVersionUID = 5130723129760236659L;

    private final UserId userId;

    private final LoginId loginId;

    private final HashPass hashPass;

    private final Status status;

    private final UserInfo userInfo;

    public static User create(UserRds userRds) {
        val info = UserInfo.builder().email(userRds.getEmail()).name(userRds.getName()).tel(userRds.getTel()).build();
        return jp.ddd.server.domain.entity.user.User.builder() //
          .userId(new UserId(userRds.getId())) //
          .loginId(new LoginId(userRds.getLoginId())) //
          .hashPass(new HashPass(userRds.getPass())) //
          .status(userRds.getStatus()) //
          .userInfo(info) //
          .build();
    }

    public static User create(UserDyn userDyn) {
        val info = UserInfo.builder().email(userDyn.getEmail()).name(userDyn.getName()).tel(userDyn.getTel()).build();
        return jp.ddd.server.domain.entity.user.User.builder() //
          .userId(new UserId(userDyn.getUserId())) //
          .loginId(new LoginId(userDyn.getLoginId())) //
          .hashPass(new HashPass(userDyn.getPass())) //
          .userInfo(info) //
          .build();
    }

    public static User create(UserForm form) {
        val info = UserInfo.builder().email(form.getEmail()).name(form.getName()).tel(form.getTel()).build();
        return jp.ddd.server.domain.entity.user.User.builder() //
          .loginId(new LoginId(form.getLoginId())) //
          .hashPass(HashPass.createByRawPass(form.getPassword())) //
          .status(Status.VALID) //
          .userInfo(info) //
          .build();
    }

}