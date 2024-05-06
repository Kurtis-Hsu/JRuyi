package jruyi.util;

import jakarta.annotation.Nullable;

import java.util.function.BooleanSupplier;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * <h2>布尔值工具</h2>
 *
 * @Date 2023-10-20 16:22
 */
public abstract class BooleanUtil
{
    /**
     * 定义所有非 0 数表示 true，0 表示 false，用于布尔值与整数值转换
     */
    public static final int DIGIT_FALSE = 0;
    public static final int DIGIT_TRUE = 1;

    private static final double double_false = DIGIT_FALSE;

    /**
     * <h2>与</h2>
     * <p>只要有一个为 {@code false}，则返回 {@code false}，否则返回 {@code true}</p>
     *
     * @param arr 一组布尔值或布尔表达式
     * @return 与运算结果
     */
    public static boolean and(boolean... arr)
    {
        Assert.notEmpty(arr);
        for (var b : arr)
            if (!b) return false;
        return true;
    }

    /**
     * <h2>与</h2>
     * <p>只要有一个为 {@code false}，则返回 {@code false}，否则返回 {@code true}</p>
     *
     * @param arr 一组布尔值或布尔表达式
     * @return 与运算结果
     */
    public static boolean and(BooleanSupplier... arr)
    {
        Assert.notEmpty(arr);
        for (var b : arr)
            if (!b.getAsBoolean())
                return false;
        return true;
    }

    /**
     * <h2>或</h2>
     * <p>只要有一个为 {@code true}，则返回 {@code true}，否则返回 {@code false}</p>
     *
     * @param arr 一组布尔值或布尔表达式
     * @return 或运算结果
     */
    public static boolean or(boolean... arr)
    {
        Assert.notEmpty(arr);
        for (var b : arr)
            if (b) return true;
        return false;
    }

    /**
     * <h2>或</h2>
     * <p>只要有一个为 {@code true}，则返回 {@code true}，否则返回 {@code false}</p>
     * <p>元素为 null 时忽略，元素返回值为 null 时，算作 false</p>
     *
     * @param arr 一组布尔值或布尔表达式
     * @return 或运算结果
     */
    public static boolean or(BooleanSupplier... arr)
    {
        Assert.notEmpty(arr);
        for (var b : arr)
            if (b.getAsBoolean()) return true;
        return false;
    }

    /**
     * @param bool 布尔值
     * @return 反值
     */
    public static boolean negate(boolean bool) { return !bool; }

    /**
     * @param bool 引用类型布尔值
     * @return 反值
     */
    @Nullable
    public static Boolean negate(Boolean bool)
    {
        if (bool == null) return null;
        return bool ? FALSE : TRUE;
    }

    // PART ----- CHECK -----

    /**
     * @param bool 布尔值
     * @return 布尔值是否为 true, 为 null 时返回 false
     */
    public static boolean isTrue(@Nullable Boolean bool) { return TRUE.equals(bool); }

    /**
     * @param bool 布尔值
     * @return 布尔值是否为 false, 为 null 时返回 false
     */
    public static boolean isFalse(@Nullable Boolean bool) { return FALSE.equals(bool); }

    // PART ----- CONVERT -----

    /**
     * @param digit 整数
     * @return 整数值是否表示 true
     * @see #DIGIT_FALSE
     */
    public static boolean ofDigit(int digit) { return digit != double_false; }

    /**
     * @param digit 数值，为 null 时默认返回 false
     * @return 数值是否表示 true，为 null 时返回 false
     * @see #DIGIT_FALSE
     */
    public static boolean ofDigit(@Nullable Number digit) { return ofDigit(digit, false); }

    /**
     * @param digit        数值
     * @param defaultValue 数值为 null 时的默认值
     * @return 数值是否表示 true，为 null 时返回 false
     * @see #DIGIT_FALSE
     */
    public static boolean ofDigit(@Nullable Number digit, boolean defaultValue)
    {
        return digit == null ? defaultValue : digit.doubleValue() != double_false;
    }

    /**
     * @param bool 布尔值
     * @return 布尔值若为 true 返回 {@link #DIGIT_TRUE}，否则返回 {@link #DIGIT_FALSE}
     */
    public static int toDigit(boolean bool) { return bool ? DIGIT_TRUE : DIGIT_FALSE; }

    /**
     * @param bool 布尔值，为 null 时默认为 false
     * @return 布尔值若为 true 返回 {@link #DIGIT_TRUE}，否则返回 {@link #DIGIT_FALSE}，为 null 时默认为 false
     * @see #isTrue(Boolean)
     */
    public static int toDigit(@Nullable Boolean bool) { return toDigit(bool, false); }

    /**
     * @param bool         布尔值
     * @param defaultValue 布尔值为 null 时的默认值
     * @return 布尔值若为 true 返回 {@link #DIGIT_TRUE}，否则返回 {@link #DIGIT_FALSE}，为 null 时默认为 false
     * @see #isTrue(Boolean)
     */
    public static int toDigit(@Nullable Boolean bool, boolean defaultValue)
    {
        var b = bool != null ? bool : defaultValue;
        return b ? DIGIT_TRUE : DIGIT_FALSE;
    }

    /**
     * 将对象实例转换为布尔值（默认值为 false）
     *
     * @param arg 参数
     * @return 布尔值
     * @see #ofObject(Object, boolean)
     */
    public static boolean ofObject(@Nullable Object arg) { return ofObject(arg, false); }

    /**
     * 将对象实例转换为布尔值
     *
     * <p>
     * 支持以下情况：
     *     <ul>
     *         <li>null -> 默认值</li>
     *         <li>Boolean -> 其值本身</li>
     *         <li>Number -> double 值不为 0</li>
     *     </ul>
     * </p>
     *
     * <p>类型为不支持的类型时，以不为 null 值表示为 ture（<b>直接返回 true 而非传入的默认值</b>）</p>
     *
     * @param arg          参数
     * @param defaultValue 默认值（仅适用参数为 null 的情况）
     * @return 布尔值
     */
    public static boolean ofObject(@Nullable Object arg, boolean defaultValue)
    {
        return switch (arg)
        {
            case null -> defaultValue;
            case Boolean b -> b;
            case Number n -> n.doubleValue() != double_false;
            default -> true;
        };
    }

    // PART ----- OTHER -----

    /**
     * 比较两个布尔值，相等时返回 0，若 x == false && y == true 返回 -1，若 x == true && y == false 返回 1
     *
     * @param x 布尔值 x
     * @param y 布尔值 y
     * @return 比较结果
     */
    public static int compare(boolean x, boolean y) { return x == y ? 0 : x ? 1 : -1; }
}