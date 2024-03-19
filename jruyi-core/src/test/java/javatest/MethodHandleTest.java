package javatest;

import data.Data;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;

public class MethodHandleTest
{
    @Test
    void test() throws Throwable
    {
        var lookup = MethodHandles.lookup();
        var handle = lookup.findGetter(Data.class, "a", int.class);

        System.out.println(handle.invoke(new Data()));
    }
}