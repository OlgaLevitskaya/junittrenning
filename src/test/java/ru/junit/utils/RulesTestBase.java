package ru.junit.utils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class RulesTestBase {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    public void createSuccessFile(String name) {
        File file = null;
        try {
            file = tempFolder.newFile(name);
            System.out.println("Create success file by name " + name);
        } catch (IOException e) {
            Assert.fail("Error create file by name " + name + "\n" + e);
        }
        System.out.println("Expect file \"" + name + "\" exist");
        assertTrue("File isn't exist", file.exists());
    }
}
