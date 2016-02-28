package xlsx;

import org.junit.Test;
import utils.StreamUtil;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by MacPradz on 2016-02-28.
 */
public class XlsxParserTest {
    StreamUtil su = new StreamUtil();
    InputStream is = su.fileToInputStream("xlsxTestFile.xlsx");
    XlsxParser parser = new XlsxParser();
    String result = parser.parse(is);
    String expected = "x";

    @Test
    public void testParser(){
        System.out.println("@Test parser(): " + result + " = " + expected);
        assertEquals(result, expected);
    }
}
