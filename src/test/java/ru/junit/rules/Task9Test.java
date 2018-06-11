package ru.junit.rules;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * Реализуйте правило, которое автоматически несколько перезапускает тесты,
 * помеченные аннотацией @Unstable, до первого успешного запуска.
 * Количество попыток должно указываться в аннотации:
 *
 * @Test
 * @Unstable(3) public void unstableTest() { ... }
 */
public class Task9Test {
    private static int attempt = 1;
    @Rule
    public RunRepeatRule rule = new RunRepeatRule();

    @Unstable(attempt = 4)
    @Test
    public void unstableTest() {
        if (attempt < 3) {
            Assert.fail("Failed on " + (attempt++) + " attempt");
            attempt++;
        }
    }
}
