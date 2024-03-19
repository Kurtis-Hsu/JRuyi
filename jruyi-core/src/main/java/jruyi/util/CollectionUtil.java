package jruyi.util;

import jakarta.annotation.Nullable;

import java.util.Collection;

/**
 * <h2>集合工具</h2>
 *
 * @Date 2024-02-23 20:52
 */
public abstract class CollectionUtil
{
    /**
     * @param coll 集合
     * @return 集合是否为空，集合为 null 时返回 true
     */
    public static boolean isEmpty(@Nullable Collection<?> coll) { return coll == null || coll.isEmpty(); }
}