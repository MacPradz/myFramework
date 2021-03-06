package json;

import org.junit.Test;
import utils.StreamUtil;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by wp on 2016-02-27.
 */
public class JsonParserTest {
    StreamUtil su = new StreamUtil();
    InputStream is = su.fileToInputStream("jsonTestFile.dat");
    JsonParser parser = new JsonParser();
    String result = parser.parse(is);
    String expected = "x";

    @Test
    public void testParser() {
        System.out.println("@Test parser(): " + result + " = " + expected);
        assertEquals(result, expected);
    }
}
