package ru.junit;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.junit.utils.DataSource;
import ru.junit.utils.RulesTestBase;
import ru.junit.utils.UniversalDataProviders;

import java.util.Random;

import static ru.junit.utils.DataSource.Type.RESOURCE;

/**
 * Примеры
 * https://github.com/barancev/junit_samples
 */
@RunWith(DataProviderRunner.class)
public class RulesTest extends RulesTestBase {
    @DataProvider
    public static Object[][] fileName() {
        return new Object[][]{
                {"test"},
                {"test.txt"},
                {"_test"},
                {"абвгд"},
                {"АБВГД DJGKLS"},
                {String.valueOf(new Random().nextInt())},
        };
    }

    @Test
    @UseDataProvider(value = "fileName")
    public void testDataProvider1(String fileName) {
        createSuccessFile(getTmp(), fileName);
    }

    @Test
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(value = "/user.data", type = RESOURCE)
    public void testDataProvider2(String fileName) {
        createSuccessFile(getTmp(), fileName);
    }
}
