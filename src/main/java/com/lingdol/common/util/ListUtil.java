package com.lingdol.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtil {

    public static <T> List<T> emptyList() {
        return new ArrayList<>();
    }

    /**
     * 将集合拆成多个达到指定长度的集合
     *
     * @param source 原集合
     * @param size   每个集合的元素个数
     * @param <T>    泛型
     * @return 拆分后的集合
     */
    public static <T> List<List<T>> splitBySize(List<T> source, int size) {
        if (source == null || source.size() <= 0 || size < 1) {
            return Collections.emptyList();
        }

        List<List<T>> resultList = new ArrayList<>();
        BigDecimal temp = BigDecimal.valueOf(source.size()).divide(BigDecimal.valueOf(size), RoundingMode.CEILING);
        int num = temp.intValue();
        for (int i = 0; i < num; i++) {
            int start = size * i;
            int end = size * (i + 1);
            if (end >= source.size()) {
                end = source.size();
            }
            resultList.add(new ArrayList<>(source.subList(start, end)));
        }
        return resultList;
    }


    public static List<Integer> toList(int[] is) {
        List<Integer> r = new ArrayList<>();
        if (is != null) {
            for (int i : is) {
                r.add(i);
            }
        }
        return r;
    }

    public static <T> List<T> toList(Collection<T> cs) {
        if (cs == null) return emptyList();
        return new ArrayList<>(cs);
    }

    @SafeVarargs
    public static <T> List<T> asList(T... ts) {
        List<T> r = new ArrayList<>();
        if (ts != null) {
            for (T t : ts) {
                if (t != null) {
                    r.add(t);
                }
            }
        }
        return r;
    }


    /**
     * 判断集合是否为空
     *
     * @param list 原集合
     * @param <T>  泛型
     * @return 是否为空
     */
    public static <T> boolean isEmpty(Collection<T> list) {
        return list == null || list.size() <= 0;
    }

    /**
     * 判断集合不为空
     *
     * @param list 原集合
     * @param <T>  泛型
     * @return 是否不为空
     */
    public static <T> boolean isNotEmpty(Collection<T> list) {
        return !isEmpty(list);
    }

    /**
     * 判断集合的长度
     *
     * @param source 原集合
     * @param <T>    泛型
     * @return 集合的元素个数
     */
    public static <T> int size(Collection<T> source) {
        int size = 0;
        if (ListUtil.isNotEmpty(source)) {
            size = source.size();
        }
        return size;
    }

    /**
     * 将原集合拆分成指定个数的集合
     *
     * @param source 原集合
     * @param number 指定需要的集合个数
     * @param <T>    泛型
     * @return 指定个数的集合
     */
    public static <T> List<List<T>> splitAverage(List<T> source, int number) {
        if (source == null || source.size() <= 0 || number < 1) {
            return Collections.emptyList();
        }

        int left = source.size() % number;
        int size = source.size() / number;

        List<List<T>> resultList = new ArrayList<>();
        if (size > 0) {
            int start = 0;
            for (int i = 0; i < number; i++) {
                if (i != 0) start += size;
                if (i <= left && i != 0) {
                    start += 1;
                }

                int end = start + size;
                if (i < left) {
                    end += 1;
                }
                if (end >= source.size()) {
                    end = source.size();
                }
                resultList.add(new ArrayList<>(source.subList(start, end)));
            }
        } else {
            for (int i = 0; i < left; i++) {
                List<T> s = new ArrayList<>();
                s.add(source.get(i));
                resultList.add(s);
            }
        }
        return resultList;
    }


    /**
     * @param input 将集合拼凑成一个集合
     * @param <T>   泛型
     * @return 合并后的集合
     */
    public static <T> List<T> merge(Collection<List<T>> input) {
        List<T> result = new ArrayList<>();
        if (input != null && !input.isEmpty()) {
            for (List<T> e : input) {
                result.addAll(e);
            }
        }
        return result;
    }


    public static boolean noEmpty(List<?>... es) {
        if (es == null || es.length <= 0) return false;

        boolean f = true;
        for (List<?> e : es) {
            if (isEmpty(e)) {
                f = false;
                break;
            }
        }
        return f;
    }

    public static boolean notAllEmpty(List<?>... es) {
        if (es == null || es.length <= 0) return false;

        boolean f = false;
        for (List<?> e : es) {
            if (isNotEmpty(e)) {
                f = true;
                break;
            }
        }
        return f;
    }


    public static <T> T first(Collection<T> c) {
        return isNotEmpty(c) ? c.iterator().next() : null;
    }

    public static <T> List<T> first(Collection<T> c, int nums) {
        if (isEmpty(c)) return null;

        return c.stream().limit(Math.min(nums, size(c))).collect(Collectors.toList());
    }

    public static <T> T last(Collection<T> c) {
        return isNotEmpty(c) ? c.stream().reduce((pre, cur) -> cur).orElse(null) : null;
    }

    /**
     * 找出两个集合中有差异的元素
     */
    public static <T> List<T> difference(List<T> t1, List<T> t2) {
        if (isEmpty(t1)) return isEmpty(t2) ? new ArrayList<>() : t2;
        if (isEmpty(t2)) return isEmpty(t1) ? new ArrayList<>() : t1;

        List<T> result = new ArrayList<>();
        for (T o1 : t1) {
            if (!t2.contains(o1) && !result.contains(o1)) {
                result.add(o1);
            }
        }

        for (T o2 : t2) {
            if (!t1.contains(o2) && !result.contains(o2)) {
                result.add(o2);
            }
        }
        return result;
    }

    /**
     * 取交集
     *
     * @param t1  集合1
     * @param t2  集合2
     * @param <T> 泛型
     * @return 两集合的交集
     */
    public static <T> List<T> intersection(List<T> t1, List<T> t2) {
        if (isEmpty(t1)) return emptyList();
        if (isEmpty(t2)) return emptyList();

        List<T> result = new ArrayList<>();
        for (T c : t1) {
            for (T v : t2) {
                if (c != null && c.equals(v) && !result.contains(c)) result.add(c);
            }
        }
        return result;
    }

    public static <T> boolean existIntersection(List<T> t1, List<T> t2) {
        if (isEmpty(t1) || isEmpty(t2)) return false;

        // 将较小的集合转换为Set以提高查找效率
        List<T> smaller = t1.size() <= t2.size() ? t1 : t2;
        List<T> larger = t1.size() <= t2.size() ? t2 : t1;

        Set<T> smallerSet = new HashSet<>(smaller);
        for (T item : larger) {
            if (item != null && smallerSet.contains(item)) {
                return true;
            }
        }
        return false;
    }

    @SafeVarargs
    public static <T> boolean existIntersection(List<T> t1, T... t2) {
        if (t2 == null || t2.length == 0) return false;
        return existIntersection(t1, asList(t2));
    }

    /**
     * 去重
     */
    public static <T> List<T> deDuplicate(List<T> source) {
        if (isEmpty(source)) return source;

        List<T> r = new ArrayList<>();
        for (T s : source) {
            if (!r.contains(s)) {
                r.add(s);
            }
        }
        return r;
    }

    /**
     * 集合中是否包含某个元素
     *
     * @param list 集合
     * @param ele  元素
     * @param <T>  泛型
     * @return 是否包含
     */
    public static <T> boolean contains(List<T> list, T ele) {
        if (isEmpty(list) || ele == null) return false;

        return list.contains(ele);
    }

    /**
     * 集合中是否有至少一个元素能模糊匹配指定元素
     *
     * @param pool 集合
     * @param fish 指定元素
     * @return 是否能模糊匹配上
     */
    public static boolean like(List<String> pool, String fish) {
        if (ListUtil.isEmpty(pool) || StringUtil.isEmpty(fish)) return false;

        for (String p : pool) {
            if (p == null) continue;
            if (p.contains(fish)) return true;
        }
        return false;
    }

    /**
     * 通过连接符连接字符串集合中每个元素
     *
     * @param list   字符串集合
     * @param joiner 连接符号
     * @return 连接后的字符串
     */
    public static String join(List<String> list, String joiner) {
        if (isEmpty(list)) return StringUtil.EMPTY;
        if (joiner == null) joiner = StringUtil.EMPTY;
        StringBuilder sb = new StringBuilder();
        for (String li : list) {
            if (li == null) continue;
            sb.append(li).append(joiner);
        }
        String s = sb.toString();
        if (!s.isEmpty() && s.endsWith(joiner)) s = s.substring(0, s.length() - joiner.length());
        return s;
    }

    /**
     * 如果集合为null，返回空集合，否则返回本身
     */
    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? emptyList() : list;
    }

    public static <T> boolean addIfNotEmpty(List<T> list, T element) {
        if (list == null || ObjectUtil.existEmpty(element)) return false;

        list.add(element);
        return true;
    }

    /**
     * 获取List中指定索引的元素，避免IndexOutOfBoundsException
     */
    public static <T> T get(List<T> list, int index) {
        if (isEmpty(list) || index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    /**
     * 过滤List中满足条件的元素
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (isEmpty(list) || predicate == null) return emptyList();
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 对List中每个元素进行转换
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        if (isEmpty(list) || mapper == null) return emptyList();
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 对List中每个元素进行转换，并去重
     */
    public static <T, R> List<R> mapAndDistinct(List<T> list, Function<T, R> mapper) {
        if (isEmpty(list) || mapper == null) return emptyList();
        return list.stream().map(mapper).distinct().collect(Collectors.toList());
    }

}