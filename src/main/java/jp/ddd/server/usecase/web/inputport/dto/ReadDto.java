package jp.ddd.server.usecase.web.inputport.dto;

import jp.ddd.server.domain.entity.core.MessageId;
import jp.ddd.server.domain.entity.core.ReadAt;
import jp.ddd.server.domain.entity.room.core.LastMessageAt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.other.data.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.eclipse.collections.api.set.ImmutableSet;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Value
public class ReadDto implements Dto {

    private final MessageId messageId;

    private final UserId userId;

    private final ReadAt readAt;
}
