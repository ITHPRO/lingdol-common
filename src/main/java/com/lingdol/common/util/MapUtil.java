package com.lingdol.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUtil {

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    public static <K, V> Map<K, V> emptyMap() {
        return new HashMap<>();
    }

    public static <K, V> int size(Map<K, V> map) {
        return map == null ? 0 : map.size();
    }

    /**
     * 安全获取Map中的值
     */
    public static <K, V> V get(Map<K, V> map, K key) {
        if (isEmpty(map) || key == null) return null;
        return map.get(key);
    }

    /**
     * 获取Map中指定key的值，如果不存在则返回默认值
     */
    public static <K, V> V getOrDefault(Map<K, V> map, K key, V defaultValue) {
        if (isEmpty(map) || key == null) return defaultValue;
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * 合并两个Map
     */
    public static <K, V> Map<K, V> merge(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> result = new HashMap<>();
        if (isNotEmpty(map1)) result.putAll(map1);
        if (isNotEmpty(map2)) result.putAll(map2);
        return result;
    }

    /**
     * 过滤Map中满足条件的条目
     */
    public static <K, V> Map<K, V> filter(Map<K, V> map, java.util.function.BiPredicate<K, V> predicate) {
        if (isEmpty(map) || predicate == null) return emptyMap();
        return map.entrySet().stream()
                .filter(entry -> predicate.test(entry.getKey(), entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}