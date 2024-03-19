package jruyi.util;

import jakarta.annotation.Nullable;

import java.util.Iterator;

/**
 * <h2>{@link Iterable} 和 {@link Iterator} 工具</h2>
 *
 * @Date 2023-10-20 13:00
 */
public abstract class IteratorUtil
{
    // ----- 检查 ------

    /**
     * @param itr {@link Iterable}
     * @return {@link Iterable} 是否为空
     */
    public static boolean isEmpty(@Nullable Iterable<?> itr) { return itr == null || isEmpty(itr.iterator()); }

    /**
     * @param itr {@link Iterator}
     * @return {@link Iterator} 是否为空
     */
    public static boolean isEmpty(@Nullable Iterator<?> itr) { return itr == null || !itr.hasNext(); }

    /**
     * @param itr {@link Iterable}
     * @return {@link Iterable} 是否包含 {@code null}
     */
    public static boolean hasNull(Iterable<?> itr) { return hasNull(itr.iterator()); }

    /**
     * @param itr {@link Iterator}
     * @return {@link Iterator} 是否包含 null
     */
    public static boolean hasNull(Iterator<?> itr)
    {
        Assert.paramNonnull(itr, "itr");
        while (itr.hasNext())
            if (itr.next() == null)
                return true;
        return false;
    }
}