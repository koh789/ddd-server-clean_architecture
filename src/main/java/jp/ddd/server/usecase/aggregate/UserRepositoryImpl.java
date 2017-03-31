package jp.ddd.server.usecase.aggregate;

import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import jp.ddd.server.adapter.gateway.redis.entity.SessionUser;
import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.exception.IllegalDataException;
import jp.ddd.server.usecase.gateway.dynamodb.UserDynGateway;
import jp.ddd.server.usecase.gateway.rds.UserRdsGateway;
import jp.ddd.server.usecase.gateway.redis.SessionUserRedisGateway;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 * {@link UserRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Component
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserRdsGateway userRdsGateway;
    @Autowired
    private UserDynGateway userDynGateway;
    @Autowired
    private SessionUserRedisGateway sessionUserRedisGateway;

    @Override
    public User register(User user) {
        if (isExist(user.getLoginId())) {
            throw new IllegalDataException("登録済みloginIdです。" + user.getLoginId().getId());
        }

        val result = userDynGateway.saveWithIncrementKey(UserDyn.create(user));
        return User.create(result);
    }

    @Override
    public boolean isExist(LoginId loginId) {
        return userDynGateway.getOptByLoginId(loginId.getId()).isPresent();
    }

    @Override
    public Optional<User> getOpt(LoginId loginId, HashPass hashPass) {
        return userDynGateway.getOptByLoginId(loginId.getId()) //
          .filter(u -> u.getPass().equals(hashPass.getPass())) //
          .map(u -> User.create(u));
    }

    @Override
    public User login(String sid, LoginId loginId, HashPass hashPass) {
        return userDynGateway.getOptByLoginId(loginId.getId()) //
          .filter(u -> u.getPass().equals(hashPass.getPass())) //
          .map(u -> {
              val sessionUser = SessionUser.create(sid, User.create(u));
              return sessionUserRedisGateway.save(sessionUser).getUser();
          }).orElseThrow(() -> new AuthException("invalid loginId and password!" + loginId.getId()));
    }

    @Override
    public void logout(String sid) {
        sessionUserRedisGateway.del(sid);
    }

    @Override
    public boolean isLogin(String sid) {
        return sessionUserRedisGateway.getOpt(sid).isPresent();
    }

    @Override
    public Optional<User> getOptBySid(String sid) {
        return sessionUserRedisGateway.getOpt(sid).map(su -> su.getUser());
    }
}
