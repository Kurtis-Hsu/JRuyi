package jruyi.util;

import jakarta.annotation.Nullable;

import java.util.StringJoiner;

/**
 * <h2>字符串工具</h2>
 *
 * @Date 2023-10-20 15:51
 */
public abstract class StringUtil
{
    // PART ----- CHECK -----

    /**
     * @param csq 字符序列
     * @return 字符序列是否为空
     * @see #notEmpty(CharSequence)
     */
    public static boolean isEmpty(@Nullable CharSequence csq) { return csq == null || csq.isEmpty(); }

    /**
     * @param csq 字符序列
     * @return 字符序列是否有内容（包括空白字符）
     * @see #isEmpty(CharSequence)
     */
    public static boolean notEmpty(@Nullable CharSequence csq) { return csq != null && !csq.isEmpty(); }

    /**
     * 若只包含空白字符，返回 true
     *
     * @param csq 字符序列
     * @return 字符序列是否为空白序列
     * @see #isEmpty(CharSequence)
     * @see #notBlank(CharSequence)
     * @see #hasText(CharSequence)
     */
    public static boolean isBlank(@Nullable CharSequence csq) { return isEmpty(csq) || !hasText(csq); }

    /**
     * 若只包含空白字符，返回 false
     *
     * @param csq 字符序列
     * @return 字符序列是否包含有效字符（不为空白字符）
     * @see #notEmpty(CharSequence)
     * @see #isBlank(CharSequence)
     * @see #hasText(CharSequence)
     */
    public static boolean notBlank(@Nullable CharSequence csq) { return notEmpty(csq) && hasText(csq); }

    /**
     * 不进行 null 检查
     *
     * @param csq 字符序列
     * @return 字符串包含非空白字符
     */
    public static boolean hasText(CharSequence csq)
    {
        for (int i = 0; i < csq.length(); i++)
            if (!Character.isWhitespace(csq.charAt(i)))
                return true;
        return false;
    }

    // PART ----- JOIN -----

    /**
     * 连接数组元素为字符串
     *
     * @param delimiter 分隔符
     * @param args      参数数组
     * @return 字符串
     */
    public static String join(CharSequence delimiter, Object... args) { return join(delimiter, "", "", args); }

    /**
     * 连接迭代器元素为字符串
     *
     * @param delimiter 分隔符
     * @param itr       可迭代参数
     * @return 字符串
     */
    public static String join(CharSequence delimiter, Iterable<?> itr) { return join(delimiter, "", "", itr); }

    /**
     * 连接数组元素为字符串
     *
     * @param delimiter 分隔符
     * @param prefix    前缀
     * @param suffix    后缀
     * @param args      参数数组
     * @return 字符串
     */
    public static String join(CharSequence delimiter, CharSequence prefix, CharSequence suffix, Object... args)
    {
        var joiner = new StringJoiner(delimiter, prefix, suffix);
        for (var arg : args) joiner.add(String.valueOf(arg));
        return joiner.toString();
    }

    /**
     * 连接迭代器元素为字符串
     *
     * @param delimiter 分隔符
     * @param prefix    前缀
     * @param suffix    后缀
     * @param itr       可迭代参数
     * @return 字符串
     */
    public static String join(CharSequence delimiter, CharSequence prefix, CharSequence suffix, Iterable<?> itr)
    {
        var joiner = new StringJoiner(delimiter, prefix, suffix);
        for (var arg : itr) joiner.add(String.valueOf(arg));
        return joiner.toString();
    }
}