package jruyi.util;

import data.SubClass;
import org.junit.jupiter.api.Test;

/**
 * <h2></h2>
 *
 * @Date 2024-02-23 20:33
 */
public class ReflectionUtilTest
{
    @Test
    void test1()
    {
        ReflectionUtil.getFieldsList(SubClass.class).forEach(f -> System.out.println(f.getDeclaringClass()));
    }
}