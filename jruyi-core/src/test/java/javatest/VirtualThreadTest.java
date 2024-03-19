package javatest;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * <h2></h2>
 *
 * @Date 2024-01-30 09:54
 */
@SuppressWarnings("unused")
public class VirtualThreadTest
{
    private static final ThreadFactory VIRTUAL_THREAD_FACTORY =
            Thread.ofVirtual().name("virtual-thread-", 1).factory();
    private static final ThreadFactory THREAD_FACTORY =
            Thread.ofPlatform().name("thread-", 1).factory();

    @Test
    void test1()
    {
        List<Thread> list = new LinkedList<>();
        for (int i = 0; i < 10; i++)
        {
            VIRTUAL_THREAD_FACTORY.newThread(() ->
                                             {
                                                 try
                                                 {
//                                                     System.out.println(Thread.currentThread());
                                                     Thread.sleep(1000);
                                                     System.out.println(Thread.currentThread());
                                                 }
                                                 catch (InterruptedException e)
                                                 {
                                                     throw new RuntimeException(e);
                                                 }
                                             }).start();
        }
    }
}