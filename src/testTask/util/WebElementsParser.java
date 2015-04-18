package testTask.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.HashMap;

public class WebElementsParser {

    public static HashMap<String, String[]> parse(String fileName) {

        HashMap<String, String[]> webElementsData = new HashMap<>();

        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();

            DefaultHandler defaultHandler = new DefaultHandler() {
                String type;
                String name;
                boolean bWebelement = false;

                public void startElement(String uri, String localName,String qName,Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("WEBELEMENT")) {
                        bWebelement = true;
                        type = attributes.getValue("type");
                        name = attributes.getValue("name");
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {}

                public void characters(char ch[], int start, int length) throws SAXException {
                    //todo make more pretty
                    if (bWebelement) {
                        String[] elementData = new String[2];
                        elementData[0]=type;
                        elementData[1] = new String(ch, start, length);
                        webElementsData.put(name, elementData);
                        bWebelement = false;
                    }
                }
            };

        saxParser.parse(fileName, defaultHandler);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return webElementsData;
    }
}
