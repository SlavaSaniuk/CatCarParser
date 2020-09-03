package by.bsac.utils;

import by.bsac.annotations.any.Utility;
import by.bsac.assertions.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Utility
public class FilesUtilities {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(FilesUtilities.class);

    public static File createFile(String a_file_path) throws IOException {
        Path file_path = Paths.get(a_file_path); // Get real file path;
        Path created = Files.createFile(file_path); // Create new file under path;
        return created.toFile();
    }

    public static File createFile(String a_file_path, String a_file_name) throws IOException {
        Asserts.notNull(a_file_path, "a_file_path");
        Asserts.notNull(a_file_name, "a_file_name");

        return FilesUtilities.createFile(FilesUtilities.concatPath(a_file_path, a_file_name));
    }

    public static String concatPath(String a_first, String a_second) {
        return a_first + FileSystems.getDefault().getSeparator() +a_second;
    }
}
