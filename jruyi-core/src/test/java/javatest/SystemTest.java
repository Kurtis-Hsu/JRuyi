package javatest;

import org.junit.jupiter.api.Test;

/**
 * <h2></h2>
 *
 * @Date 2024-03-15 12:26
 */
public class SystemTest
{
    @Test
    void printSystemProperties()
    {
        System.getProperties().forEach((k, v) -> System.out.println(STR."\{k} --- \{v}"));
    }
    @Test
    void printSystemEnv()
    {
        System.getenv().forEach((k, v) -> System.out.println(STR."\{k} --- \{v}"));
    }
}