package xml;

import org.junit.Test;
import utils.StreamUtil;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by wp on 2016-02-28.
 */
public class XmlParserTest {
    StreamUtil su = new StreamUtil();
    InputStream is = su.fileToInputStream("xmlTestFile.xml");
    XmlParser parser = new XmlParser();
    String result = parser.parse(is);
    String expected = "x";

    @Test
    public void testParser() {
        System.out.println("@Test parser(): " + result + " = " + expected);
        assertEquals(result, expected);
    }
}
