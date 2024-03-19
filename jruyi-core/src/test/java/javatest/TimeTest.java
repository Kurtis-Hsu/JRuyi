package javatest;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <h2></h2>
 *
 * @Date 2024-02-26 15:50
 */
@SuppressWarnings("unused")
public class TimeTest
{
    @Test
    void test1()
    {
        Duration.between(Instant.ofEpochMilli(1), LocalDateTime.now());
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
    }
}