package com.lingdol.common.util;

public class BooleanUtil {

    public static final String var_true = "true";
    public static final String var_true_num_str = "1";
    public static final Integer var_true_num = 1;

    /**
     * true, 或者 "true", 或者 "1" 或者 1
     */
    public static boolean isTrue(Object src) {
        if (src == null) return false;

        if (src instanceof Boolean) {
            return (Boolean) src;
        } else if (src instanceof String) {
            return var_true.equalsIgnoreCase((String) src) || var_true_num_str.equals(src);
        } else if (src instanceof Number) {
            return var_true_num.equals(((Number) src).intValue());
        }
        return false;
    }

    public static Boolean isAllTrue(Object... src) {
        if (src == null || src.length == 0) return false;
        boolean flag = true;
        for (Object obj : src) {
            if (!isTrue(obj)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean isTrue(Object src, boolean def) {
        if (src == null) return def;
        return isTrue(src);
    }
}