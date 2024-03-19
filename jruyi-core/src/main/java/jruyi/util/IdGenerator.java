package jruyi.util;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h2>Id 生成器</h2>
 *
 * @param <ID> id 的数据类型
 * @Date 2024-02-26 12:42
 */
@FunctionalInterface
public interface IdGenerator<ID extends Serializable>
{
    /**
     * @return id
     */
    ID newId();

    /**
     * @return 使用 {@link AtomicLong} 生成 long 类型自增 id
     */
    static IdGenerator<Long> simpleIncrementId()
    {
        var leastSigBits = new AtomicLong();
        return leastSigBits::incrementAndGet;
    }

    /**
     * @return 使用 {@link UUID#randomUUID()} 生成随机 uuid
     */
    static IdGenerator<UUID> randomUUID() { return UUID::randomUUID; }

    /**
     * @return 使用 {@link UUID#randomUUID()} 生成随机 uuid
     */
    static IdGenerator<String> randomUUIDString()
    {
        return () -> UUID.randomUUID().toString().replaceAll("-", "");
    }
}