package jruyi.core.exception;

import jakarta.annotation.Nullable;

import java.io.Serial;

/**
 * <h2>反射异常</h2>
 *
 * <p>可用于包装一个 {@link ReflectiveOperationException}, 将其转换为运行时异常</p>
 *
 * @Date 2024-01-29 10:04
 * @see ReflectiveOperationException
 */
public class ReflectionException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public ReflectionException() { super(); }

    public ReflectionException(@Nullable String message) { super(message); }

    public ReflectionException(@Nullable String message, Throwable cause) { super(message, cause); }

    public ReflectionException(Throwable cause) { super(cause); }
}