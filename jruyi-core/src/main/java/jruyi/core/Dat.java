package jruyi.core;

import jakarta.annotation.Nullable;

import java.util.Objects;

/**
 * <h2>数据存储器</h2>
 *
 * <p>用于包装一个数据，主要用于在 lambda 中对数据进行操作</p>
 * <p>
 * {@snippet lang = "java":
 * var v = Dat.of("string");
 * var v = Dat.of(1);
 * var v = Dat.of(1L);
 * var v = Dat.of('1');
 * var v = Dat.of(true);
 *}
 *
 * @Date 2023-11-05 15:35
 */
public final class Dat<V>
{
    private Dat(@Nullable V value) { v = value; }

    /**
     * value
     */
    private V v;

    public static <V> Dat<V> of(@Nullable V value) { return new Dat<>(value); }

    @Nullable
    public V get() { return v; }

    public void set(@Nullable V value) { v = value; }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(@Nullable Object o) { return Objects.equals(v, o); }

    @Override
    public String toString() { return Objects.toString(v); }

    @Override
    public int hashCode() { return Objects.hashCode(v); }
}