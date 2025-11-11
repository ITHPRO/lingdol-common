package com.lingdol.common.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtil {

    /**
     * 集合不为空且元素都不为空
     */
    public static boolean noEmpty(Object... ts) {
        if (ts == null || ts.length == 0) return false;

        for (Object t : ts) {
            if (!notEmpty(t)) return false;
        }
        return true;
    }

    /**
     * 判断两个元素是否相等
     *
     * @param c 元素1
     * @param t 元素2
     * @return 是否相等
     */
    public static <C, T> boolean equals(C c, T t) {
        if (c == null || t == null) return false;
        return c.equals(t);
    }


    /**
     * 集合本身为空 或 存在空元素
     */
    public static boolean existEmpty(Object... ts) {
        if (ts == null || ts.length == 0) return true;

        for (Object t : ts) {
            if (!notEmpty(t)) return true;
        }

        return false;
    }

    /**
     * 集合不为空并且存在部分为空的数据
     */
    public static boolean partEmpty(Object... ts) {
        if (ts == null || ts.length == 0) return false;
        boolean existEmpty = false;
        boolean existNotEmpty = false;
        for (Object t : ts) {
            if (notEmpty(t)) existNotEmpty = true;
            else existEmpty = true;
        }
        return existEmpty && existNotEmpty;
    }


    /**
     * 检查对象是否非空（支持 String、Collection、Map、数组等类型）。
     *
     * @param t 待检查的对象
     * @return 若对象非空或非空集合/数组/字符串，返回 true；否则返回 false
     */
    public static <T> boolean notEmpty(T t) {
        if (t == null) {
            return false;
        } else if (t instanceof String) {
            return StringUtil.isNotEmpty((String) t);
        } else if (t instanceof Collection<?>) {
            return ListUtil.isNotEmpty((Collection<?>) t);
        } else if (t instanceof Map<?, ?>) {
            return !((Map<?, ?>) t).isEmpty();
        } else if (t.getClass().isArray()) {
            return Array.getLength(t) > 0;
        }
        return true;
    }

    /**
     * 集中不为空且存在不为空的元素
     */
    public static boolean notAllEmpty(Object... ts) {
        if (ts == null) return false;

        for (Object t : ts) {
            if (notEmpty(t)) return true;
        }
        return false;
    }

    /**
     * 集合为空 或 元素都为空
     */
    public static boolean allEmpty(Object... ts) {
        if (ts == null) return true;

        for (Object t : ts) {
            if (notEmpty(t)) return false;
        }
        return true;
    }

    /**
     * 取第一个不为空的元素
     */
    @SafeVarargs
    public static <T> T one(T... ts) {
        if (ts == null) return null;

        for (T t : ts) {
            if (notEmpty(t)) return t;
        }

        return null;
    }

    /**
     * 若为空，取默认值，若不为空，返回本身
     */
    public static <T> T defaultIfNull(T object, T defaultValue) {
        return object != null ? object : defaultValue;
    }
}