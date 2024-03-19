package jruyi.util.design;

/**
 * <h2>构建者</h2>
 *
 * @Date 2024-03-19 16:20
 */
public interface Builder<T>
{
    /**
     * @return 构建实例
     * @throws Exception 构建实例时发生的异常
     */
    T build() throws Exception;
}