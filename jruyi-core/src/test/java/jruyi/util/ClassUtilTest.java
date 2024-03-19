package jruyi.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * <h2></h2>
 *
 * @Date 2024-01-30 16:26
 */
@SuppressWarnings("all")
public class ClassUtilTest
{
    @Test
    void test1()
    {
        assert ClassUtil.isPresent("cn.jruyi.lang.D");
    }

    @Test
    void test2()
    {
        try
        {
            Class.forName("cn.jruyi.Clazz", true, this.getClass().getClassLoader());
            System.out.println();
        }
        catch (ClassNotFoundException _) {}
    }

    @Test
    void test3()
    {
        System.out.println(int.class.getName());
        System.out.println(byte[].class.getName());
        System.out.println(short[].class.getName());
        System.out.println(int[].class.getName());
        System.out.println(long[].class.getName());
        System.out.println(float[].class.getName());
        System.out.println(double[].class.getName());
        System.out.println(char[].class.getName());
        System.out.println(boolean[].class.getName());
        System.out.println(int[][].class.getName());
        System.out.println(Object.class.getName());
        System.out.println(Object[].class.getName());
        System.out.println(Object[][].class.getName());
    }

    @Test
    void test4()
    {
        System.out.println(void.class.isPrimitive());
    }

    @Test
    void test5() throws ClassNotFoundException
    {
        Class<?> cls = Class.forName("[Ljava.lang.Object;");
        Object o = Array.newInstance(cls, 10);
        System.out.println(Arrays.toString((Object[]) o));
    }

    @Test
    void test6()
    {
        Class<?> cls = ClassUtil.forName("cn.jruyi.util.Type", false, this.getClass().getClassLoader());
        System.out.println(cls.getName());
    }
}

class Type
{
    static
    {
        System.out.println("initialized");
    }
}