package jruyi.core.exception;

import jakarta.annotation.Nullable;
import jruyi.util.Assert;

import java.util.Arrays;

/**
 * <h2>类型不支持异常</h2>
 *
 * <p>用于表示不支持某些类，使用一个数组 {@link #causeTypes} 存储导致该异常的所有类型</p>
 *
 * @Date 2024-01-26 15:45
 */
public class UnsupportedTypeException extends RuntimeException
{
    /**
     * 导致该异常的所有类型的数组
     */
    private Class<?>[] causeTypes;

    /**
     * 构造一个类型不支持异常，
     */
    public UnsupportedTypeException() { }

    public UnsupportedTypeException(@Nullable String message) { super(message); }

    public UnsupportedTypeException(@Nullable String message, Class<?> causeType)
    {
        super(message);
        this.causeTypes = new Class[] { causeType };
    }

    public UnsupportedTypeException(@Nullable String message, Class<?>... causeTypes)
    {
        super(message);
        Assert.notEmpty(causeTypes, "the cause-types must not be empty");
        this.causeTypes = causeTypes;
    }

    public UnsupportedTypeException(Class<?>... causeTypes)
    {
        super(STR."by: \{causeTypes.length == 1 ? causeTypes[0] : Arrays.toString(causeTypes)}");
    }

    public Class<?>[] getCauseTypes() { return causeTypes; }

    public synchronized UnsupportedTypeException initCause(Class<?>... causeTypes)
    {
        Assert.notEmpty(causeTypes, "the cause-types must not be empty");
        if (this.causeTypes != null) this.causeTypes = causeTypes;
        return this;
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) { return this; }
}