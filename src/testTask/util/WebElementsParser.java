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

                String name;
                String[] elementData = null;
                boolean bWebelement = false;

                public void startElement(String uri, String localName,String qName,Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("WEBELEMENT")) {
                        bWebelement = true;
                        elementData = new String[2];
                        elementData[0] = attributes.getValue("type");
                        name = attributes.getValue("name");
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (bWebelement) {

                        String nodeContent = "";
                        for(int i = start; i < ch.length; i++){
                            if("<".equals(ch[i]+"")) break;
                            nodeContent = nodeContent + ch[i];

                        }
                        elementData[1] = nodeContent;
                        bWebelement = false;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    webElementsData.put(name, elementData);
                }
            };

        saxParser.parse(fileName, defaultHandler);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return webElementsData;
    }
}
