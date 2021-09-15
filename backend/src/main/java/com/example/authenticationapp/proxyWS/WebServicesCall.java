package com.example.authenticationapp.proxyWS;

import org.json.simple.JSONArray;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

public class WebServicesCall {
    /**
     * This class helps us to call all our proxies for parsing
     */

    /**
     * This function call the ArticleProxy
     * @param xmlString xml data (received from sylob)
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static JSONArray testArticleWS(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        ArticleProxy proxy = new ArticleProxy();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new InputSource(new StringReader(xmlString)), proxy);

        return proxy.getJsonArray();
    }

    /**
     * This function call the KitProxy
     * @param xmlString xml data (received from sylob)
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static JSONArray testKitWS(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        KitProxy proxy = new KitProxy();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new InputSource(new StringReader(xmlString)), proxy);

        return proxy.getArray();
    }

    /**
     * This function call the CompanyProxy
     * @param xmlString xml data (received from sylob)
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static JSONArray testCompanyWS(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        CompanyProxy proxy = new CompanyProxy();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new InputSource(new StringReader(xmlString)), proxy);

        return proxy.getArray();
    }
}
