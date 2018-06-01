package ru.junit;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.junit.utils.AllCategories;
import ru.junit.utils.TestBase;

import java.io.IOException;

/**
 * Примеры
 * https://github.com/barancev/junit_samples
 */

public class Task3Test extends TestBase implements AllCategories {
    @Test
    @Category(PositiveTests.class)
    public void createFileSuccess2() {
        createSuccessFile(getTmp(), "temp.txt");
    }

    @Test
    @Category(PositiveTests.class)
    public void createFileSuccess1() {
        createSuccessFile(getTmp(), "temp");
    }

    @Test(expected = IOException.class)
    @Category(NegativeTests.class)
    public void createFileNoDir() throws IOException {
        createNegativeFile(getTmp() + "123", "temp1");
    }

    @Test(expected = IOException.class)
    @Category(NegativeTests.class)
    public void createFileDirisNull() throws IOException {
        createNegativeFile(null, "temp1");
    }

    @Test
    @Category(NegativeTests.class)
    public void createFileNameEmptyName() throws IOException {
        createNegativeFile(getTmp(), "");
    }
}
