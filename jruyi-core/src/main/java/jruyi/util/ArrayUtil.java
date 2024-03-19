package jruyi.util;

import jakarta.annotation.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * <h2>数组工具</h2>
 *
 * @Date 2023-10-20 12:58
 */
public abstract class ArrayUtil
{
    // PART ----- CHECK -----

    /**
     * @param arr 数组
     * @return 数组是否为空
     */
    public static boolean isEmpty(@Nullable Object[] arr) { return arr == null || arr.length == 0; }

    /**
     * @param arr     被扫描数组
     * @param checker 检测器
     * @param <T>     数组元素类型
     * @return 若有一个元素检测通过，返回 true，否则返回 false
     */
    public static <T> boolean check(T[] arr, Predicate<T> checker)
    {
        Assert.paramNonnull(checker, "checker");
        for (T v : arr)
            if (checker.test(v))
                return true;
        return false;
    }

    /**
     * @param arr 数组
     * @return 对象数组中是否包含 null
     */
    public static boolean hasNull(Object[] arr) { return check(arr, Objects::isNull); }

    // PART ----- TYPE -----

    /**
     * <ul>
     *     <li>参数不为数组类型时，返回参数本身</li>
     *     <li>参数为一维数组时，返回数组的元素类型</li>
     *     <li>参数为多维数组时，递归调用该方法，返回多维数组的根元素类型</li>
     * </ul>
     *
     * @param clazz Class 对象
     * @return 数组的元素类型
     * @see Class#getComponentType()
     */
    public static Class<?> getAtomicType(Class<?> clazz)
    {
        var component = clazz.getComponentType();
        // 如果元素类型为 null，返回类本身
        if (component == null) return clazz;
        // 如果 getOrigin 为 true，递归调用该方法，否则返回元素类型
        return getAtomicType(component);
    }
}