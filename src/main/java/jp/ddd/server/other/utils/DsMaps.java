package jp.ddd.server.other.utils;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

import java.util.Map;
import java.util.Optional;

public class DsMaps {

    public static <K, V> ImmutableMap toImt(Map<K, V> map) {
        return Maps.immutable.ofAll(map);
    }

    public static <K, V> MutableMap toMt(Map<K, V> map) {
        if (map == null) {
            return Maps.mutable.empty();
        }
        return Maps.immutable.ofAll(map).toMap();
    }

    public static <K, V> Optional<V> getOpt(ImmutableMap<K, V> map, K key) {
        return Optional.ofNullable(map.get(key));
    }
}
