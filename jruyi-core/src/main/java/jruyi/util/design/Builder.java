package jruyi.util.design;

import jruyi.core.exception.BuildingException;

/**
 * <h2>构建者</h2>
 *
 * @Date 2024-03-19 16:20
 */
public interface Builder<T>
{
    /**
     * @return 被构建类的新实例
     * @throws BuildingException 构建实例时发生的异常
     */
    T build() throws BuildingException;
}