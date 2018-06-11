package ru.junit.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.junit.utils.TestBase;

import java.util.Random;

import static ru.junit.dataprovider.DataSource.Type.FILE;
import static ru.junit.dataprovider.DataSource.Type.RESOURCE;

/**
 * Практическое задание №10
 * <p>
 * Переделайте имеющиеся тесты так, чтобы для загрузки данных из файла (или ресурса) использовался
 * универсальный data provider, а название файла (или ресурса), из которого нужно загружать данные,
 * указывалось в аннотации тестового метода.
 * <p>
 * Используйте для этого https://github.com/TNG/junit-dataprovider, в последней версии уже есть возможность передачи
 * информации о тестовом методе в data provider.
 * Примеры
 * https://github.com/barancev/junit_samples
 */
@RunWith(DataProviderRunner.class)
public class DataProvider2Test extends TestBase {
    @DataProvider
    public static Object[][] data() {
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
    @DataProvider("data")
    public void testDataProvider(String fileName) {
        createSuccessFile(getTmp(), fileName);
    }

    @Test
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(value = "/junittrenning/src/test/java/ru/junit/dataprovider/file.data", type = FILE)
    public void testDataProviderFile(String fileName) {
        createSuccessFile(getTmp(), fileName);
    }

    @Test
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(value = "/user.data", type = RESOURCE)
    public void testDataProviderResource(String fileName) {
        createSuccessFile(getTmp(), fileName);
    }
}
