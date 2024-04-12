package jruyi.core.mutable;

import jakarta.annotation.Nullable;

import java.io.Serial;
import java.util.Objects;

/**
 * <h2>可变对象</h2>
 *
 * @param <V> 被包装类型
 * @Date 2023-11-05 15:35
 */
public final class MutableObject<V> implements Mutable<V>
{
    @Serial private static final long serialVersionUID = 1L;

    @Nullable private V value;

    private MutableObject() { }

    public static <D> MutableObject<D> of(@Nullable D value)
    {
        var instance = new MutableObject<D>();
        instance.value = value;
        return instance;
    }

    @Override public String toString() { return String.valueOf(value); }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (this == o) return true;
        if (o instanceof MutableObject<?> m) return Objects.equals(value, m.value);
        return false;
    }

    @Override public int hashCode() { return value != null ? value.hashCode() : 0; }

    @Override @Nullable public V get() { return value; }

    @Override public void set(@Nullable V data) { this.value = data; }
}