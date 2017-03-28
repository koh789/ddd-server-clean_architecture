package jp.ddd.server.domain.entity.user.core;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Value
public class UserId implements Serializable {
    private static final long serialVersionUID = 4935513382112496976L;

    private final Integer id;

    public boolean isEquals(Integer userId) {
        if (this.id == null) {
            return false;
        }
        return this.id.equals(userId);
    }

    public boolean isNotEquals(Integer userId) {
        return !isEquals(userId);
    }
}
