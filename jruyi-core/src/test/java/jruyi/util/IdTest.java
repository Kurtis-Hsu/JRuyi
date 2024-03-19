package jruyi.util;

import org.junit.jupiter.api.Test;

public class IdTest
{
    @Test
    void test1()
    {
        var id = IdGenerator.simpleIncrementId();
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
    }

    @Test
    void test2()
    {
        var id = IdGenerator.randomUUID();
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
    }

    @Test
    void test3()
    {
        var id = IdGenerator.randomUUIDString();
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
        System.out.println(id.newId());
    }
}