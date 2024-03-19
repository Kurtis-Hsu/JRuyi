package jruyi.core.exception;

/**
 * <h2>构建异常</h2>
 *
 * @Date 2024-03-19 17:34
 */
public class BuildingException extends RuntimeException
{
    public BuildingException() { }

    public BuildingException(String message) { super(message); }

    public BuildingException(String message, Throwable cause) { super(message, cause); }

    public BuildingException(Throwable cause) { super(cause); }

    public BuildingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}