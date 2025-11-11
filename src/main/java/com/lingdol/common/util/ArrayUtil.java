package com.lingdol.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    public static <T> boolean isEmpty(T[] ts) {
        return (ts == null || ts.length == 0);
    }

    public static <T> boolean isNotEmpty(T[] ts) {
        return !isEmpty(ts);
    }

    public static <T> int length(T[] ts) {
        if (isEmpty(ts)) return 0;
        return ts.length;
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Object[] data, int index) {
        if (data == null || index > data.length - 1) return null;
        return (T) data[index];
    }

    /**
     * 数组转List
     */
    public static <T> List<T> toList(T[] array) {
        if (isEmpty(array)) return new ArrayList<>();
        return Arrays.asList(array);
    }

    /**
     * 判断数组是否包含指定元素
     */
    public static <T> boolean contains(T[] array, T element) {
        if (isEmpty(array) || element == null) return false;
        for (T item : array) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取数组中第一个元素
     */
    @SafeVarargs
    public static <T> T first(T... array) {
        if (isEmpty(array)) return null;
        return array[0];
    }

    /**
     * 获取数组中最后一个元素
     */
    @SafeVarargs
    public static <T> T last(T... array) {
        if (isEmpty(array)) return null;
        return array[array.length - 1];
    }
}