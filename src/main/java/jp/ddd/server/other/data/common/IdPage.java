package jp.ddd.server.other.data.common;

import jp.ddd.server.other.data.Dto;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Value
public class IdPage implements Dto {
    private static final long serialVersionUID = 4295148173038043011L;

    private final Optional<Long> lastIdOpt;
    private final int limit;

    public static IdPage init(int limit) {
        return IdPage.builder() //
          .lastIdOpt(Optional.empty()) //
          .limit(limit) //
          .build();
    }

    public static IdPage createWithLastId(Long lastId, int limit) {

        return IdPage.builder() //
          .lastIdOpt(Optional.of(lastId))
          .limit(limit) //
          .build();
    }
}
