package lib;

import java.util.Map;

import com.google.common.base.Function;


public class MapEntries {
    public static <V> Function<Map.Entry<?, V>, V> valueFunction() {
        return new Function<Map.Entry<?, V>, V>() {
            @Override public V apply(Map.Entry<?, V> input) {
                return input.getValue();
            }
        };
    }


    public static <K> Function<Map.Entry<K, ?>, K> keyFunction() {
        return new Function<Map.Entry<K, ?>, K>() {
            @Override public K apply(Map.Entry<K, ?> input) {
                return input.getKey();
            }
        };
    }


    private MapEntries() {}
}
