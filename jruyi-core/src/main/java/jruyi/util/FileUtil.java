package jruyi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <h2>文件工具</h2>
 *
 * @Date 2024-05-05 17:27
 */
public abstract class FileUtil
{
    /**
     * 复制文件
     *
     * @param from 输入文件
     * @param to   输出文件
     * @throws IOException IO 异常
     */
    public static void copy(File from, File to) throws IOException
    {
        try (var fis = new FileInputStream(from); var fos = new FileOutputStream(to))
        {
            fis.transferTo(fos);
        }
    }
}