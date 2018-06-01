package ru.junit.utils;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestBase {
    public String getTmp() {
        return tmp.toString();
    }

    private static Path tmp;

    @BeforeClass
    public static void setUp() throws IOException {
        tmp = Files.createTempDirectory("test");
        System.out.println("Create directory " + tmp);
    }

    @AfterClass
    public static void tearDown() {
        if (tmp != null) tmp.toFile().deleteOnExit();
        System.out.println("Delete directory " + tmp);
    }

    public void createNegativeFile(String path, String name) throws IOException {
        String fullPath = getPath(path, name);
        File file = new File(fullPath);
        Assert.assertFalse("File is create", file.createNewFile());
        Assert.assertTrue("File exist", file.exists());
    }

    public void createSuccessFile(String path, String name) {
        String fullPath = getPath(path, name);
        File file = new File(fullPath);
        try {
            Assert.assertTrue("Can't create file " + fullPath, file.createNewFile());
            System.out.println("Create file " + fullPath);
        } catch (IOException e) {
            Assert.fail("Error create file " + fullPath + "\n" + e);
        }
        Assert.assertTrue("File isn't exist", file.exists());
        System.out.println("File exist" + fullPath);
    }

    private String getPath(String path, String name) {
        return path + "\\" + name;
    }
}
