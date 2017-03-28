package jp.ddd.server.domain.entity.room.core;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * room内での最終メッセージ日時を表現します。
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Value
public class LastMessageAt implements Serializable {
    private static final long serialVersionUID = 4935513382112496976L;

    private final Date date;
}
