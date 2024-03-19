package jruyi.util;

import org.junit.jupiter.api.Test;

/**
 * <h2></h2>
 *
 * @Date 2024-02-26 12:44
 */
public class IdTest
{
    @Test
    void test1()
    {
        var id = IdBuilder.simpleId();
        System.out.println(id.build());
        System.out.println(id.build());
        System.out.println(id.build());
        System.out.println(id.build());
        System.out.println(id.build());
    }
}