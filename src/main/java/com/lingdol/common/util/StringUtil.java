package com.lingdol.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class StringUtil {

    public static final String EMPTY = "";

    /**
     * 按顺序获取第一个不为空的字符串
     *
     * @param es 元素集
     * @return 第一个不为空的字符串
     */
    public static String one(String... es) {
        if (es != null) {
            for (String e : es) {
                if (e != null && !e.trim().isEmpty()) return e;
            }
        }
        return EMPTY;
    }


    /**
     * 用分隔符连接多个字符串元素
     *
     * @param separator 分隔符
     * @param es        待连接的元素
     * @return 使用分隔符连接后的字符串
     */
    public static String join(String separator, Object... es) {
        if (separator == null) separator = EMPTY;

        if (es == null || es.length == 0) return EMPTY;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < es.length; i++) {
            Object e = es[i];
            if (e == null) continue;

            if (i != 0) {
                sb.append(separator);
            }
            sb.append(e);
        }
        return sb.toString();
    }

    /**
     * 两字符串是否相同
     */
    public static boolean equal(String s, String t) {
        return s != null && s.equals(t);
    }


    /**
     * 用分隔符连接多个字符串元素
     *
     * @param separator 分隔符
     * @param es        待连接的元素
     * @return 使用分隔符连接后的字符串
     */
    public static String join(String separator, Collection<String> es) {
        if (ListUtil.isEmpty(es)) return EMPTY;
        return join(separator, (Object[]) es.toArray(new String[0]));
    }


    /**
     * 判断字符串为空
     *
     * @param e 字符串
     * @return 为空
     */
    public static boolean isEmpty(String e) {
        return e == null || e.length() <= 0;
    }

    /**
     * 判断字符串不为空
     *
     * @param e 字符串
     * @return 不为空
     */
    public static boolean isNotEmpty(String e) {
        return !isEmpty(e);
    }

    /**
     * 判断字符串为空或者都是空白符
     *
     * @param e 元素
     */
    public static boolean isBlank(String e) {
        if (e == null) return true;
        return e.trim().length() <= 0;
    }

    /**
     * 去掉字符串左右的空格，如果去除后为空，返回null
     */
    public static String trimToNull(String s) {
        if (isBlank(s)) return null;
        return s.trim();
    }

    public static String trim(String s) {
        if (isEmpty(s)) return EMPTY;
        return s.trim();
    }

    /**
     * 如果字符串为null，返回空字符串
     */
    public static String nullToEmp(String s) {
        return s == null ? EMPTY : s;
    }


    /**
     * 判断字符串不为空且不都为空白符
     *
     * @param e 元素
     */
    public static boolean isNotBlank(String e) {
        return !isBlank(e);
    }

    /**
     * 所有元素都不为空
     *
     * @param input 所有元素
     * @return 是否
     */
    public static boolean noEmpty(String... input) {
        if (input == null) return false;

        for (String p : input) {
            if (isEmpty(p)) {
                return false;
            }
        }
        return true;
    }


    public static boolean hasEmpty(String... input) {
        return !noEmpty(input);
    }

    /**
     * 如果为null，返回空字符串
     */
    public static String filterNullToEmp(String c) {
        return c == null ? "" : c;
    }


    /**
     * 去尾
     *
     * @param tar  目标
     * @param tail 尾
     * @return 如果尾部存在指定的字符，截取掉
     */
    public static String cutTail(String tar, String tail) {
        if (allEmpty(tar, tail)) return tar;

        return tar.endsWith(tail) ? tar.substring(0, tar.lastIndexOf(tail)) : tar;
    }

    /**
     * 所有元素中至少一个不为空
     *
     * @param input 所有元素
     * @return 是否
     */
    public static boolean notAllEmpty(String... input) {
        if (input != null) {
            for (String p : input) {
                if (isNotEmpty(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 入参为空或元素都为空
     */
    public static boolean allEmpty(String... input) {
        return !notAllEmpty(input);
    }

    /**
     * 依次分割字符串
     *
     * @param str       字符串
     * @param delimiter 分隔符
     */
    public static String[] splitStr(String str, String... delimiter) {
        if (isEmpty(str) || delimiter == null || delimiter.length == 0) return null;
        for (String s : delimiter) {
            List<String> result = split(str, s);
            if (result != null && result.size() > 1) return result.toArray(new String[0]);
        }
        return new String[]{str};
    }

    /*
     * 分隔
     * @param original 数据源
     * @param separator  分隔符
     */
    public static List<String> split(String original, String separator) {
        if (original == null) return null;
        if (separator == null) return ListUtil.asList(original);
        if (StringUtil.isEmpty(original)) return ListUtil.emptyList();

        String[] splitInfo = original.split(separator);
        return ListUtil.asList(splitInfo);
    }

    /**
     * 判断是否存在为空的元素
     */
    public static boolean existEmpty(String... es) {
        if (es == null || es.length <= 0) return true;
        boolean f = false;
        for (String e : es) {
            if (isEmpty(e)) {
                f = true;
                break;
            }
        }
        return f;
    }

    /**
     * 判断是否存在为空的元素
     */
    public static boolean existEmpty(List<String> es) {
        if (es == null || es.size() <= 0) return true;
        boolean f = false;
        for (String e : es) {
            if (isEmpty(e)) {
                f = true;
                break;
            }
        }
        return f;
    }

    /**
     * 指定元素中是否存在指定值（模糊查）
     */
    public static boolean anyLike(String f, List<String> es) {
        if (es == null || es.size() <= 0 || isEmpty(f)) return false;

        boolean flag = false;
        for (String e : es) {
            if (e.contains(f)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    /**
     * 指定元素中是否存在指定值（模糊查）
     */
    public static boolean anyLike(String f, String... es) {
        if (es == null || es.length <= 0 || isEmpty(f)) return false;

        boolean flag = false;
        for (String e : es) {
            if (e.contains(f)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 指定元素中是否存在指定值（模糊查）
     */
    public static boolean anyLike(List<String> keywords, String... es) {
        if (es == null || es.length <= 0 || ListUtil.isEmpty(keywords)) return false;

        boolean flag = false;
        outer:
        for (String e : es) {
            for (String keyword : keywords) {
                if (e.contains(keyword)) {
                    flag = true;
                    break outer;
                }
            }
        }
        return flag;
    }


    /**
     * 如果字符串为空字符串，返回null,否则返回原字符串
     */
    public static String blankToNull(String s) {
        return isBlank(s) ? null : s;
    }

    /**
     * 判断字符串是否包含另一个字符串
     */
    public static boolean contains(String source, String part) {
        return isNotEmpty(source) && source.contains(part);
    }

    /**
     * 判断字符串是否包含某些字符串
     */
    public static boolean contains(String source, String... partArray) {
        if (isEmpty(source) || partArray == null || partArray.length < 1) return false;
        boolean contain = false;
        for (String part : partArray) {
            if (source.contains(part)) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    /**
     * 获取字符串的长度
     */
    public static int length(String str) {
        if (str == null) return 0;
        return str.length();
    }

    public static boolean endWith(String str, String tar) {
        if (isEmpty(str)) return false;

        return str.endsWith(tar);
    }

    /**
     * 拼接字符串,null当做空字符串
     */
    public static String concat(String... ss) {
        if (ss == null || ss.length <= 0) return EMPTY;

        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            if (s == null) s = EMPTY;
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 截取字符串
     *
     * @param str   原字符串
     * @param start 开始位置
     * @param end   结束位置（不包含）
     * @return 截取后的字符串
     */
    public static String subString(String str, int start, int end) {
        return subString(str, start, end, false);
    }

    /**
     * 截取字符串
     *
     * @param str   原字符串
     * @param start 开始位置
     * @param end   结束位置（不包含）
     * @return 截取后的字符串
     */
    public static String subString(String str, int start, int end, boolean nullToEmp) {
        if (str == null) return nullToEmp ? EMPTY : null;
        if (end < start) {
            int temp = end;
            end = start;
            start = temp;
        }
        if (start < 0) start = 0;
        if (end > str.length()) end = str.length();
        if (start >= str.length()) return EMPTY;
        return str.substring(start, end);
    }

    /**
     * 是否包含占用四个字节的字符
     *
     * @param text 待判断的字符串
     * @return 是否包含占用四个字节的字符
     */
    public static boolean containsFourBytesChar(String text) {
        if (isEmpty(text)) return false;

        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            if ((b & 0xF8) == 0xF0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将驼峰命名法的字符串转换为下划线命名法的字符串
     *
     * @param camelCaseStr 驼峰命名法的字符串
     * @return 下划线命名法的字符串，如果输入为空则返回空字符串
     */
    public static String toSnakeCase(String camelCaseStr) {
        if (camelCaseStr == null || camelCaseStr.isEmpty() || camelCaseStr.contains("_")) {
            return camelCaseStr;
        }
        StringBuilder snakeCaseStr = new StringBuilder();
        for (char c : camelCaseStr.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (snakeCaseStr.length() > 0) {
                    snakeCaseStr.append("_");
                }
                snakeCaseStr.append(Character.toLowerCase(c));
            } else {
                snakeCaseStr.append(c);
            }
        }
        return snakeCaseStr.toString();
    }

    /**
     * 判断字符串是否是一个数字
     */
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是一个整数
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否以某个片段开始
     * @param str   字符串
     * @param part  字符片段
     * @return  是否以字符片段开始
     */
    public static boolean startWith(String str, String part) {
        if (existEmpty(str, part)) return false;
        return str.startsWith(part);
    }

    /**
     * 判断字符串是否以小写字母开头
     * @param str   字符串
     * @return  是否以小写字母开头
     */
    public static boolean startWithLowerCase(String str) {
        if (isEmpty(str)) return false;
        return Character.isLowerCase(str.charAt(0));
    }

    /**
     * 判断字符串是否以大写字母开头
     * @param str   字符串
     * @return  是否以大写字母开头
     */
    public static boolean startWithUpperCase(String str) {
        if (isEmpty(str)) return false;
        return Character.isUpperCase(str.charAt(0));
    }

    /**
     * 将字符串的第一个字母大写
     * @param str   字符串
     * @return  首字母大写的字符串
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) return str;

        char firstChar = str.charAt(0);
        if (!Character.isLowerCase(firstChar)) return str;

        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toUpperCase(firstChar));
        return sb.toString();
    }

    /**
     * 将字符串的第一个字母小写
     * @param str   待转换的字符串
     * @return  首字母小写的字符串
     */
    public static String decapitalizeFirstLetter(String str) {
        if (isEmpty(str)) return str;

        char firstChar = str.charAt(0);
        if (!Character.isUpperCase(firstChar)) return str;

        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toLowerCase(firstChar));
        return sb.toString();
    }

    /**
     * 提取字符串中第一个括号（中文或英文）内的内容，支持中英文括号混用
     * @param str 输入字符串
     * @return 括号内的内容，如果没有找到括号则返回null
     */
    public static String extractContentInBracketsMixed(String str) {
        if (isEmpty(str)) {
            return null;
        }

        // 定义所有可能的左括号和右括号
        Set<Character> leftBrackets = new HashSet<>();
        leftBrackets.add('(');
        leftBrackets.add('（');

        Set<Character> rightBrackets = new HashSet<>();
        rightBrackets.add(')');
        rightBrackets.add('）');

        // 查找第一个左括号的位置
        int startIndex = -1;
        for (int i = 0; i < str.length(); i++) {
            if (leftBrackets.contains(str.charAt(i))) {
                startIndex = i;
                break;
            }
        }

        // 如果没有找到左括号，返回null
        if (startIndex == -1) {
            return null;
        }

        // 查找第一个右括号位置
        int endIndex = -1;
        for (int i = startIndex + 1; i < str.length(); i++) {
            if (rightBrackets.contains(str.charAt(i))) {
                endIndex = i;
                break;
            }
        }

        // 如果没有找到右括号，返回null
        if (endIndex == -1 || endIndex <= startIndex) {
            return null;
        }

        // 返回括号内的内容
        return str.substring(startIndex + 1, endIndex);
    }

    /**
     * 字符串左填充
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) str = EMPTY;
        if (str.length() >= size) return str;
        StringBuilder sb = new StringBuilder(size);
        for (int i = str.length(); i < size; i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 字符串右填充
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) str = EMPTY;
        if (str.length() >= size) return str;
        StringBuilder sb = new StringBuilder(str);
        for (int i = str.length(); i < size; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }
}