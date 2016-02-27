package json;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by wp on 2016-02-27.
 */
public class JsonParserTest {
    InputStream is = fileToInputStream("jsonTestFile.dat");
    JsonParser parser = new JsonParser();
    String result = parser.parse(is);
    String expected = "x";

    @Test
    public void testParser() {
        System.out.println("@Test parser(): " + result + " = " + expected);
        assertEquals(result, expected);
    }

    private InputStream fileToInputStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            InputStream input = new FileInputStream(file);
            return input;
        }catch (FileNotFoundException e){
            throw new RuntimeException("File not found");
        }
    }
}
