package com.microsoft.cosmic.visualizer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {

    public static String readContent(Path path) throws IOException {
        try {
            String content = Files.readString(path);
            return content;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

}
