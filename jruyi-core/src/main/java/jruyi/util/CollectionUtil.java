package jruyi.util;

import jakarta.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;

/**
 * <h2>集合工具</h2>
 *
 * @Date 2024-02-23 20:52
 */
public abstract class CollectionUtil
{
    /**
     * @param itr 迭代器
     * @return 迭代器是否为空，为 null 时返回 true
     */
    public static boolean isEmpty(@Nullable Iterator<?> itr) { return itr == null || !itr.hasNext(); }

    /**
     * @param itr 可迭代对象
     * @return 可迭代对象是否为空，为 null 时返回 true
     */
    public static boolean isEmpty(@Nullable Iterable<?> itr) { return itr == null || isEmpty(itr.iterator()); }

    /**
     * @param coll 集合
     * @return 集合是否为空，集合为 null 时返回 true
     */
    public static boolean isEmpty(@Nullable Collection<?> coll) { return coll == null || coll.isEmpty(); }
}