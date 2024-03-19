package javatest;

import data.ARecord;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * <h2></h2>
 *
 * @Date 2024-02-26 14:18
 */
public class RecordTest
{
    @Test
    void test1()
    {
        Class<ARecord> record = ARecord.class;
        System.out.println(Arrays.toString(record.getDeclaredConstructors()));
        for (Constructor<?> constructor : record.getDeclaredConstructors())
        {
            System.out.println(constructor);
            for (Parameter parameter : constructor.getParameters())
            {
                System.out.println(parameter.getName());
            }
        }

        System.out.println();

        for (Field field : record.getDeclaredFields())
        {
            System.out.println(field);
        }
    }
}