package jruyi.core.mutable;

import jakarta.annotation.Nullable;

/**
 * <h2>可变 Integer</h2>
 *
 * @Date 2024-04-02 20:27
 */
public class MutableInteger extends Number implements Mutable<Integer>, Comparable<MutableInteger>
{
    private int value;

    private MutableInteger(int value) { this.value = value; }

    public static MutableInteger of(int value) { return new MutableInteger(value); }

    public static MutableInteger of(Integer value) { return new MutableInteger(value); }

    @Override public Integer get() { return value; }

    @Override public void set(Integer value) { this.value = value; }

    @Override
    public boolean equals(@Nullable Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MutableInteger that = (MutableInteger) o;

        return value == that.value;
    }

    @Override public int hashCode() { return value; }

    @Override public String toString() { return Integer.toString(value); }

    @Override public int intValue() { return value; }

    @Override public long longValue() { return value; }

    @Override public float floatValue() { return value; }

    @Override public double doubleValue() { return value; }

    @Override public int compareTo(MutableInteger o) { return Integer.compare(value, o.value); }
}