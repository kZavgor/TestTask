package testTask.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

public class WebElementsParcer {

    public static Map<String, String[][]> getLocatorsFromXML(String fileName){
        Map<String, String[][]> locators = new HashMap<String, String[][]>();

        Document dom = parseXmlFile(fileName);



        Element rootElements = dom.getDocumentElement();



        NodeList nl = rootElements.getElementsByTagName("Elements");



        if(nl != null && nl.getLength() > 0) {



            for(int nodeIndex = 0 ; nodeIndex < nl.getLength(); nodeIndex++) {

                Element element = (Element)nl.item(nodeIndex);

                System.out.println(element.getAttribute("name"));
                System.out.println(element.getAttribute("type"));
                System.out.println(element.getFirstChild().getNodeValue());
            }
        }

        return locators;
    }


    private static Document parseXmlFile(String fileName){
        Document dom = null;

        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse(fileName);



        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            return dom;
        }
    }

}
