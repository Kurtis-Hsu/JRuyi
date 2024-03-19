package jruyi.core;

import org.junit.jupiter.api.Test;

/**
 * <h2></h2>
 *
 * @Date 2024-01-23 11:59
 */
public class DataTest
{
    @Test
    void test1()
    {
        var str = Dat.of("test");
        str.set("replaced");

        System.out.println(str);
    }

    @Test
    void test2()
    {
        var data = Dat.of(1);

        System.out.println(data);
    }
}