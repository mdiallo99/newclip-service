package com.example.authenticationapp;

import com.example.authenticationapp.utils.SylobRequests;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class AuthenticationAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testWS() throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, ParseException, KeyManagementException, TransformerConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

//		ArticleProxy proxy = new ArticleProxy();
//		parser.parse("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\articles.xml", proxy);
//		System.out.println(proxy.getContent().getArticles().size());
//
//		KitProxy proxyKit = new KitProxy();
//		parser.parse("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\kits.xml", proxyKit);
//		System.out.println(proxyKit.getContent().getKits().size());

		//System.out.println(proxyKit.getArray());

		System.out.println(SylobRequests.getArticlesFromSylob("C0000204"));

	}
}
