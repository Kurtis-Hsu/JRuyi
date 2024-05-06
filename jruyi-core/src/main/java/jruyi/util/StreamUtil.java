package jruyi.util;

import java.io.*;

/**
 * <h2>流工具</h2>
 *
 * @Date 2024-05-06 10:16
 */
public abstract class StreamUtil
{
    /**
     * 传输数据并关闭流
     *
     * @param in  输入流
     * @param out 输出流
     * @throws IOException IO 异常
     */
    public static void transfer(InputStream in, OutputStream out) throws IOException
    {
        try (in; out) { in.transferTo(out); }
    }

    /**
     * 传输数据并关闭流
     *
     * @param in  输入流
     * @param out 输出流
     * @throws IOException IO 异常
     */
    public static void transfer(Reader in, Writer out) throws IOException
    {
        try (in; out) { in.transferTo(out); }
    }
}