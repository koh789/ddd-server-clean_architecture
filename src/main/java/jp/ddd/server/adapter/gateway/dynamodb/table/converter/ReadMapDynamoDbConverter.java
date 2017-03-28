package jp.ddd.server.adapter.gateway.dynamodb.table.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import jp.ddd.server.adapter.gateway.dynamodb.table.MessageReadDyn;
import jp.ddd.server.other.utils.DsMaps;
import jp.ddd.server.other.utils.Strings;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.tuple.Tuples;

import java.util.Map;

/**
 * Created by noguchi_kohei 
 */
public class ReadMapDynamoDbConverter
  implements DynamoDBTypeConverter<Map<Integer, String>, MutableMap<Integer, MessageReadDyn>> {

    @Override
    public Map<Integer, String> convert(MutableMap<Integer, MessageReadDyn> readMap) {
        return readMap.collect((uid, read) -> Tuples.pair(uid, Strings.toJson(read))).toImmutable().castToMap();
    }

    @Override
    public MutableMap<Integer, MessageReadDyn> unconvert(Map<Integer, String> readStrMap) {
        if (readStrMap == null) {
            return Maps.mutable.empty();
        }
        MutableMap<Integer, String> map = DsMaps.toMt(readStrMap);
        return map.collect((uid, readStr) -> Tuples.pair(uid, Strings.toObj(readStr, MessageReadDyn.class)));
    }
}
