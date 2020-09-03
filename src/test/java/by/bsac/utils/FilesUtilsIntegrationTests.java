package by.bsac.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class FilesUtilsIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(FilesUtilsIntegrationTests.class);

    @Test
    void createFile_newFile_shouldReturnFileInstance() {

        String file_name = "test.txt";
        String file_path = "/home/slava";

        String full_path = file_path + FileSystems.getDefault().getSeparator() + file_name;

        File created = null;
        try {
            created = FilesUtilities.createFile(full_path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(created);
        Assertions.assertTrue(created.exists());

    }
}
