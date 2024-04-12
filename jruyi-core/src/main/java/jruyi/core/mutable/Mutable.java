package jruyi.core.mutable;

import java.io.Serializable;

/**
 * <h2>可变数据接口</h2>
 *
 * @param <V> value 类型
 * @Date 2024-04-02 10:59
 */
public interface Mutable<V> extends Serializable
{
    /**
     * @return value
     */
    V get();

    /**
     * 设置 value
     */
    void set(V v);
}