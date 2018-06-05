package ru.junit.utils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RulesTestBase {
    private static Path tmp;
    @Rule
    public ExternalResource directoryRule = new ExternalResource() {
        @Override
        protected void before() throws IOException {
            tmp = Files.createTempDirectory("test");
            System.out.println("Create directory " + tmp);
        }

        @Override
        protected void after() {
            System.out.println("Delete directory " + tmp);
            if (tmp != null) tmp.toFile().deleteOnExit();
        }
    };

    public String getTmp() {
        return tmp.toString();
    }

    public void createSuccessFile(String path, String name) {
        String fullPath = getPath(path, name);
        File file = new File(fullPath);
        try {
            Assert.assertTrue("Can't create file " + fullPath, file.createNewFile());
            System.out.println("Create success file by path " + fullPath);
        } catch (IOException e) {
            Assert.fail("Error create file " + fullPath + "\n" + e);
        }
        System.out.println("Check file exist by path " + fullPath);
        Assert.assertTrue("File isn't exist", file.exists());
    }

    private String getPath(String path, String name) {
        return path + "\\" + name;
    }
}
