package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by MacPradz on 2016-02-28.
 */
public class StreamUtil {

    public InputStream fileToInputStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            InputStream input = new FileInputStream(file);
            return input;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }
}
