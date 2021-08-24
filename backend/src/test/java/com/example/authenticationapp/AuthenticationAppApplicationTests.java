package com.example.authenticationapp;

import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Photo;
import com.example.authenticationapp.proxyWS.ArticleProxy;
import com.example.authenticationapp.proxyWS.KitProxy;
import com.example.authenticationapp.utils.JsonParserHelper;
import com.example.authenticationapp.utils.SylobRequests;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.json.Json;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;

@SpringBootTest
class AuthenticationAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testWS() throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

//		ArticleProxy proxy = new ArticleProxy();
//		parser.parse("C:\\Users\\mdiallo\\Desktop\\newclip-service\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\articles.xml", proxy);
//		System.out.println(proxy.getContent().getArticles().size());

		KitProxy proxy = new KitProxy();
		parser.parse("C:\\Users\\mdiallo\\Desktop\\newclip-service\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\kits.xml", proxy);
		System.out.println(proxy.getContent().getKits().size());

	}

}
