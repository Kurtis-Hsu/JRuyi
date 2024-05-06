package jruyi.util;

/**
 * <h2>数字工具</h2>
 *
 * @Date 2024-04-20 09:04
 */
public abstract class NumberUtil
{
    /**
     * @param start 开始数字（包含）
     * @param end   结束数字（不包含）
     * @return 指定范围内的 int 数组
     */
    public static int[] range(int start, int end)
    {
        if (start >= end) return ArrayUtil.EMPTY_INT_ARRAY;
        int[] result = new int[end - start];
        for (int i = start; i < end; i++) result[i - start] = i;
        return result;
    }

    /**
     * @param start 开始数字（包含）
     * @param end   结束数字（包含）
     * @return 指定范围内的 int 数组
     */
    public static int[] rangeClosed(int start, int end)
    {
        if (start >= end) return ArrayUtil.EMPTY_INT_ARRAY;
        int[] result = new int[end - start + 1];
        for (int i = start; i <= end; i++) result[i - start] = i;
        return result;
    }
}