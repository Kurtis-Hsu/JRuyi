package jruyi.core.mutable;

import jakarta.annotation.Nullable;

/**
 * <h2>可变 Integer</h2>
 *
 * @Date 2024-04-02 20:27
 */
public class MutableInteger extends Number implements Mutable<Integer>
{
    private int value; // TODO 可变数据类

    public static MutableInteger of(int value)
    {
        var instance = new MutableInteger();
        instance.value = value;
        return instance;
    }

    @Override
    public boolean equals(@Nullable Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MutableInteger that = (MutableInteger) o;

        return value == that.value;
    }

    @Override public int hashCode() { return value; }

    @Override public String toString() { return String.valueOf(value); }

    @Override public Integer get() { return value; }

    @Override public void set(Integer value) { this.value = value; }

    @Override public int intValue() { return value; }

    @Override public long longValue() { return value; }

    @Override public float floatValue() { return value; }

    @Override public double doubleValue() { return value; }
}