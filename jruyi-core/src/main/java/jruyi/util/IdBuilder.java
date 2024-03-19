package jruyi.util;

import jruyi.core.exception.BuildingException;
import jruyi.util.design.Builder;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h2>Id 构建器</h2>
 *
 * @Date 2024-02-26 12:42
 */
@FunctionalInterface
public interface IdBuilder<ID extends Serializable> extends Builder<ID>
{
    /**
     * @return 新的 id
     * @throws BuildingException 无法创建 id
     */
    @Override
    ID build() throws BuildingException;

    /**
     * @return 使用 {@link AtomicLong} 生成 long 类型自增 id 的构建器
     */
    static IdBuilder<Long> simpleLongId()
    {
        var leastSigBits = new AtomicLong();
        return leastSigBits::incrementAndGet;
    }

    /**
     * @return 使用 {@link UUID#randomUUID()} 的构建器
     */
    static IdBuilder<UUID> randomUUID() { return UUID::randomUUID; }
}