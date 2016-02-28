package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.StreamUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MacPradz on 2016-02-28.
 */
public class XmlParser {
    private static final String MAIN_TAG = "PowerStation";
    private static final String ID_TAG = "stationID";

    public static void main(String[] args) {
        StreamUtil su = new StreamUtil();
        InputStream is = su.fileToInputStream("xmlTestFile");

    }

    public String parse(InputStream is)  {
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(is);

            Element rootElement = doc.getDocumentElement();
            System.out.println("Root element :" + rootElement.getNodeName());
            System.out.println("recordedTime : " + rootElement.getAttribute("recordedTime"));

            NodeList nList = doc.getElementsByTagName(MAIN_TAG);
            System.out.println("----------------------------");
            for ( int nodeNo = 0; nodeNo < nList.getLength(); nodeNo++ ) {
                Node nNode = nList.item(nodeNo);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if ( nNode.getNodeType() == Node.ELEMENT_NODE ) {
                    Element powerStationElement = (Element) nNode;
                    System.out.println("stationID : " + powerStationElement.getAttribute(ID_TAG));
                    System.out.println("substation : " + powerStationElement.getAttribute("substation"));
                    System.out.println("stationName : " + powerStationElement.getAttribute("stationName"));
                    System.out.println("load : " + powerStationElement.getAttribute("load"));
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "x";
    }
}
