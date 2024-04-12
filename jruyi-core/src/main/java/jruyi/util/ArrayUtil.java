package jruyi.util;

import jakarta.annotation.Nullable;

import java.lang.reflect.Array;
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
     * 优先检测对象为数组的情况，若不为数组，调用 {@link Util#isEmpty(Object)}
     *
     * @param arr 被检测对象
     * @return 被检测对象是否为空
     */
    public static boolean isEmpty(@Nullable Object arr)
    {
        if (arr == null) return true;
        if (arr.getClass().isArray()) return Array.getLength(arr) == 0;
        return Util.isEmpty(arr);
    }

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
    public static Class<?> parseArrayType(Class<?> clazz)
    {
        var component = clazz.getComponentType();
        // 如果元素类型为 null，返回类本身
        if (component == null) return clazz;
        // 如果 getOrigin 为 true，递归调用该方法，否则返回元素类型
        return parseArrayType(component);
    }

    // PART ----- NEW -----

    /**
     * 将可变参数转换为固定数组
     *
     * @param args 可变参数
     * @param <T>  参数类型
     * @return 可变参数转换后的数组
     */
    @SafeVarargs public static <T> T[] arrayOf(T... args) { return args; }

    /**
     * 将 byte 可变参数转换为固定数组
     *
     * @param args byte 可变参数
     * @return 可变参数转换后的数组
     */
    public static byte[] arrayOf(byte... args) { return args; }

    /**
     * 将 short 可变参数转换为固定数组
     *
     * @param args short 可变参数
     * @return 可变参数转换后的数组
     */
    public static short[] arrayOf(short... args) { return args; }

    /**
     * 将 int 可变参数转换为固定数组
     *
     * @param args int 可变参数
     * @return 可变参数转换后的数组
     */
    public static int[] arrayOf(int... args) { return args; }

    /**
     * 将 long 可变参数转换为固定数组
     *
     * @param args long 可变参数
     * @return 可变参数转换后的数组
     */
    public static long[] arrayOf(long... args) { return args; }

    /**
     * 将 float 可变参数转换为固定数组
     *
     * @param args float 可变参数
     * @return 可变参数转换后的数组
     */
    public static float[] arrayOf(float... args) { return args; }

    /**
     * 将 double 可变参数转换为固定数组
     *
     * @param args double 可变参数
     * @return 可变参数转换后的数组
     */
    public static double[] arrayOf(double... args) { return args; }

    /**
     * 将 char 可变参数转换为固定数组
     *
     * @param args char 可变参数
     * @return 可变参数转换后的数组
     */
    public static char[] arrayOf(char... args) { return args; }

    /**
     * 将 boolean 可变参数转换为固定数组
     *
     * @param args boolean 可变参数
     * @return 可变参数转换后的数组
     */
    public static boolean[] arrayOf(boolean... args) { return args; }
}