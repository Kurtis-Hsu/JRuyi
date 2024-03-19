package jruyi.util;

import jakarta.annotation.Nullable;
import jruyi.core.exception.UnsupportedTypeException;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <h2>通用工具</h2>
 *
 * @Date 2023-10-11 13:20
 */
public abstract class Util
{
    // PART ----- CHECK -----

    /**
     * <p>
     * 支持的类型：
     *     <ul>
     *         <li>{@link String}</li>
     *         <li>{@link Collection}</li>
     *         <li>{@link Map}</li>
     *         <li>{@link Iterator}</li>
     *         <li>{@link Iterable}</li>
     *         <li>{@link Array}</li>
     *     </ul>
     * </p>
     *
     * @param arg 被检测参数
     * @return 参数是否为空，参数为 null 时返回 true
     * @throws UnsupportedTypeException 参数类型不支持检测
     */
    public static boolean isEmpty(@Nullable Object arg)
    {
        return switch (arg)
        {
            case null -> true;
            case String s -> s.isEmpty();
            case Collection<?> coll -> coll.isEmpty();
            case Map<?, ?> m -> m.isEmpty();
            case Iterator<?> itr -> !itr.hasNext();
            case Iterable<?> itr -> !itr.iterator().hasNext();
            default ->
            {
                try
                {
                    yield Array.getLength(arg) == 0;
                }
                catch (IllegalArgumentException _)
                {
                    throw new UnsupportedTypeException(arg.getClass());
                }
            }
        };
    }

    /**
     * @param checked      被检测参数
     * @param defaultValue 被检测参数为 null 时返回的结果
     * @param <T>          参数类型
     * @return 参数1为 null 时返回参数2，否则返回参数1
     */
    @Nullable
    public static <T> T defaultIfNull(@Nullable T checked, @Nullable T defaultValue)
    {
        return checked != null ? checked : defaultValue;
    }

    /**
     * @param checked      被检测参数
     * @param defaultValue 被检测参数为 null 时返回的结果
     * @param <T>          参数类型
     * @return 参数1为 null 时返回参数2，否则返回参数1
     */
    @Nullable
    public static <T> T defaultIfNull(@Nullable T checked, Supplier<T> defaultValue)
    {
        return checked != null ? checked : defaultValue.get();
    }

    /**
     * 安全获取
     *
     * <p>
     * 判断对象是否为 null，若为 null 则返回 null，
     * 若不为 null 则返回回调函数 {@link Function#apply(Object)} )} 的返回值
     * </p>
     *
     * @param <A>      参数类型
     * @param <V>      值类型
     * @param arg      被检测参数
     * @param function 获取器
     * @return 结果
     */
    @Nullable
    public static <A, V> V safeDo(@Nullable A arg, Function<A, V> function)
    {
        return arg != null ? function.apply(arg) : null;
    }

    // PART ----- OTHER -----

    /**
     * <p>偏移量修正</p><br>
     *
     * <p>
     * <strong>偏移量修正规则：</strong><br>当参数在以下范围时的修正变化
     *     <table>
     *         <tr><th>区间</th><th>结果</th></tr>
     *         <tr><td>[0, total)</td><td>x</td></tr>
     *         <tr><td>(-total, 0)</td><td>total + x</td></tr>
     *         <tr><td>[total, +∞)</td><td>total - 1</td></tr>
     *         <tr><td>(-∞, -total)</td><td>0</td></ttdr>
     *     </table>
     * </p>
     *
     * <p>x表示传入的偏移量参数</p>
     *
     * <p>例：-1在修正后表示文本右数第一个字符的位置</p>
     *
     * @param total  数值总量（例如：文本长度，数组元素数，集合元素数）
     * @param offset 偏移量
     * @return 修正后的位置值
     */
    public static int offsetFix(int total, int offset)
    {
        if (offset >= 0)
            return offset >= total ? total - 1 : offset;
        else
            return offset <= -total ? 0 : total - offset;
    }
}