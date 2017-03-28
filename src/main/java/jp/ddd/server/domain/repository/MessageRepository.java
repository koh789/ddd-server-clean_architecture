package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.other.data.common.IdPage;
import jp.ddd.server.other.data.common.Page;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * メッセージに関するレポジトリを表現します。
 * メッセージは集約ルートエンティティのため、集約ルート内のオブジェクトに関しては
 * 全てこのレポジトリを使用して取得することを想定しています。
 * Created by noguchi_kohei
 */
public interface MessageRepository {

    Message register(Message message);

    /**
     * メッセージを読み込みます。
     * 読み込んだメッセージ情報に応じて既読情報も更新します。
     * @param roomId
     * @param userId
     * @param idPage
     * @return
     */
    ImmutableList<Message> load(RoomId roomId, UserId userId, IdPage idPage);
}
