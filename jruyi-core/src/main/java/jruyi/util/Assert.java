package jruyi.util;

import jakarta.annotation.Nullable;
import jruyi.core.exception.UnsupportedTypeException;

import java.util.function.Supplier;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static jruyi.util.Util.defaultIfNull;
import static jruyi.util.Util.isEmpty;

/**
 * <h2>断言工具</h2>
 *
 * <p>断言某个对象或变量是否符合指定规则，否则抛出异常</p>
 *
 * @Date 2023-10-11 11:07
 */
public abstract class Assert
{
    public static final String EMPTY_MSG = "";

    // PART ----- NULL ------

    /**
     * @param arg       被检测参数
     * @param paramName 参数名
     * @param <O>       参数类型
     * @return 被检测参数
     * @throws NullPointerException 参数为 null
     */
    public static <O> O paramNonnull(@Nullable O arg, String paramName)
    {
        if (arg == null)
            throw new NullPointerException(
                    addPrefix(STR."the parameter [\{nonnull(paramName)}] must not be null")
            );
        return arg;
    }

    /**
     * @param arg 被检测参数
     * @param <O> 参数类型
     * @return 被检测参数
     * @throws NullPointerException 参数为 null
     */
    public static <O> O nonnull(@Nullable O arg)
    {
        return nonnull(arg, () -> addPrefix("the argument must not be null"));
    }

    /**
     * @param arg 被检测参数
     * @param msg 异常警报信息
     * @param <O> 参数类型
     * @return 被检测参数
     * @throws NullPointerException 参数为 null
     */
    public static <O> O nonnull(@Nullable O arg, @Nullable String msg)
    {
        if (arg == null) throw new NullPointerException(msg);
        return arg;
    }

    /**
     * @param arg 被检测参数
     * @param msg 异常警报信息
     * @param <O> 参数类型
     * @return 被检测参数
     * @throws NullPointerException 参数为 null
     */
    public static <O> O nonnull(@Nullable O arg, Supplier<String> msg)
    {
        if (arg == null) throw new NullPointerException(msg.get());
        return arg;
    }

    // PART ----- EMPTY -----

    /**
     * 断言指定参数不为空
     *
     * @param arg 指定参数
     * @throws UnsupportedTypeException 指定参数的类型不支持
     * @see Util#isEmpty(Object)
     */
    public static void notEmpty(@Nullable Object arg)
    {
        asserts(
                !isEmpty(arg),
                () -> addPrefix(STR."the argument\{arg == null ? "" : STR." [\{arg}]"} must not be empty")
        );
    }

    /**
     * 断言指定参数不为空
     *
     * @param arg 指定参数
     * @param msg 异常警报信息
     * @throws UnsupportedTypeException 指定参数的类型不支持
     * @see Util#isEmpty(Object)
     */
    public static void notEmpty(@Nullable Object arg, String msg) { asserts(!isEmpty(arg), msg); }

    /**
     * 断言指定参数不为空
     *
     * @param arg 指定参数
     * @param msg 异常警报信息
     * @throws UnsupportedTypeException 指定参数的类型不支持
     * @see Util#isEmpty(Object)
     */
    public static void notEmpty(@Nullable Object arg, Supplier<String> msg) { asserts(!isEmpty(arg), msg); }

    // PART ----- BOOLEAN -----

    /**
     * @param exp 表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 表达式不为真
     * @see BooleanUtil#isTrue(Boolean)
     */
    public static void isTrue(@Nullable Boolean exp, @Nullable String msg) { asserts(TRUE.equals(exp), msg); }

    /**
     * @param exp 表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 表达式不为真
     * @see Boolean#equals(Object)
     */
    public static void isTrue(@Nullable Boolean exp, @Nullable Supplier<String> msg) { asserts(TRUE.equals(exp), msg); }

    /**
     * @param exp 表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 表达式不为假
     * @see BooleanUtil#isFalse(Boolean)
     */
    public static void isFalse(@Nullable Boolean exp, @Nullable String msg) { asserts(FALSE.equals(exp), msg); }

    /**
     * @param exp 表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 表达式不为假
     * @see BooleanUtil#isFalse(Boolean)
     */
    public static void isFalse(@Nullable Boolean exp, Supplier<String> msg) { asserts(FALSE.equals(exp), msg); }

    // PART ----- TEXT ------

    /**
     * @param str 被检测文本
     * @throws IllegalArgumentException 文本无效
     * @see StringUtil#notBlank(CharSequence)
     */
    public static void notBlank(@Nullable String str)
    {
        asserts(StringUtil.notBlank(str), () -> addPrefix("the text is invalid"));
    }

    /**
     * @param str 被检测文本
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 文本无效
     * @see StringUtil#notBlank(CharSequence)
     */
    public static void notBlank(@Nullable String str, @Nullable String msg) { asserts(StringUtil.notBlank(str), msg); }

    /**
     * @param str 被检测文本
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 文本无效
     * @see StringUtil#notBlank(CharSequence)
     */
    public static void notBlank(@Nullable String str, @Nullable Supplier<String> msg)
    {
        asserts(StringUtil.notBlank(str), msg);
    }

    // PART ----- STATE ------

    /**
     * <p>断言 boolean 表达式结果为真</p>
     *
     * @param exp 表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 表达式不为真
     */
    public static void state(boolean exp, @Nullable String msg)
    {
        if (!exp) throw new IllegalStateException(defaultIfNull(msg, EMPTY_MSG));
    }

    /**
     * <p>断言 boolean 表达式结果为真</p>
     *
     * @param exp 表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 表达式不为真
     */
    public static void state(boolean exp, Supplier<String> msg)
    {
        if (!exp) throw new IllegalStateException(msg.get());
    }

    // PART ----- CLASS -----

    /**
     * 断言指定对象为指定类的实例
     *
     * @param obj   指定对象
     * @param clazz 指定类
     * @throws IllegalArgumentException 指定对象不为指定类的实例
     * @throws NullPointerException     传入的类参数为 null
     */
    public static void isInstance(@Nullable Object obj, Class<?> clazz)
    {
        nonnull(clazz, addPrefix("the super class to check must not be null"));
        asserts(
                clazz.isInstance(obj),
                addPrefix(STR."the object must not be null and must be instance of class [\{clazz}]")
        );
    }

    /**
     * 断言指定对象为指定类的实例
     *
     * @param obj   指定对象
     * @param clazz 指定类
     * @param msg   异常警报信息
     * @throws IllegalArgumentException 指定对象不为指定类的实例
     * @throws NullPointerException     传入的类参数为 null
     */
    public static void isInstance(@Nullable Object obj, Class<?> clazz, @Nullable String msg)
    {
        nonnull(clazz, addPrefix("the super class to check must not be null"));
        asserts(clazz.isInstance(obj), msg);
    }

    /**
     * 断言指定对象为指定类的实例
     *
     * @param obj   指定对象
     * @param clazz 指定类
     * @param msg   异常警报信息
     * @throws IllegalArgumentException 指定对象不为指定类的实例
     * @throws NullPointerException     传入的类参数为 null
     */
    public static void isInstance(@Nullable Object obj, Class<?> clazz, Supplier<String> msg)
    {
        paramNonnull(clazz, "clazz");
        asserts(clazz.isInstance(obj), msg);
    }

    /**
     * 断言指定子类为指定超类的子类
     *
     * @param subClass   指定子类
     * @param superClass 指定超类
     * @throws IllegalArgumentException 指定子类不为指定超类的子类
     * @throws NullPointerException     传入的任意参数为 null
     */
    public static void isSuper(Class<?> subClass, Class<?> superClass)
    {
        paramNonnull(subClass, "subClass");
        paramNonnull(superClass, "superClass");
        asserts(
                superClass.isAssignableFrom(subClass),
                addPrefix(STR."class [\{subClass}] must be a subclass of class [\{superClass}]")
        );
    }

    /**
     * 断言指定子类为指定超类的子类
     *
     * @param subClass   指定子类
     * @param superClass 指定超类
     * @param msg        异常警报信息
     * @throws IllegalArgumentException 指定子类不为指定超类的子类
     * @throws NullPointerException     传入的任意参数为 null
     */
    public static void isSuper(Class<?> subClass, Class<?> superClass, @Nullable String msg)
    {
        paramNonnull(subClass, "subClass");
        paramNonnull(superClass, "superClass");
        asserts(superClass.isAssignableFrom(subClass), msg);
    }

    /**
     * 断言指定子类为指定超类的子类
     *
     * @param subClass   指定类
     * @param superClass 指定超类
     * @param msg        异常警报信息
     * @throws IllegalArgumentException 指定类不为指定超类的子类
     * @throws NullPointerException     传入的任意参数为 null
     */
    public static void isSuper(Class<?> subClass, Class<?> superClass, Supplier<String> msg)
    {
        paramNonnull(subClass, "subClass");
        paramNonnull(superClass, "superClass");
        asserts(superClass.isAssignableFrom(subClass), msg);
    }

    // PART ----- ASSERT ------

    /**
     * 断言方法
     *
     * @param exp 布尔表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 布尔表达式结果为 false
     */
    protected static void asserts(boolean exp, @Nullable String msg)
    {
        if (!exp) throw new IllegalArgumentException(msg);
    }

    /**
     * 断言方法
     *
     * @param exp 布尔表达式
     * @param msg 异常警报信息
     * @throws IllegalArgumentException 布尔表达式结果为 false
     */
    protected static void asserts(boolean exp, Supplier<String> msg)
    {
        if (!exp) throw new IllegalArgumentException(msg.get());
    }

    /**
     * @param msg 断言失败信息
     * @return 加上了 "[Assertion failed] - " 前缀的断言失败信息
     */
    public static String addPrefix(String msg) { return STR."[Assertion failed] - \{msg}"; }
}