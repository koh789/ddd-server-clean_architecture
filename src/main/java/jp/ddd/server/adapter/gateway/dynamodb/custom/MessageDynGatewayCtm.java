package jp.ddd.server.adapter.gateway.dynamodb.custom;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.other.data.common.IdPage;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
public interface MessageDynGatewayCtm {

    MessageDyn saveWithIncrementKey(MessageDyn messageDyn);

    /**
     * 指定したroomIdに紐づくメッセージを取得します。
     * @param roomId
     * @param idPage
     * @return
     */
    ImmutableList<MessageDyn> findDesc(Integer roomId, IdPage idPage);

    ImmutableList<MessageDyn> findDesc(Integer roomId);

    ImmutableList<Long> updateReads(Integer roomId, Integer userId);
}
