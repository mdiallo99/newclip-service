package com.example.authenticationapp;

import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Photo;
import com.example.authenticationapp.proxyWS.ArticleProxy;
import com.example.authenticationapp.proxyWS.ObjectHandler;
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

//	@Test
//	void testArticles() throws IOException, ParseException {
//		JsonParserHelper.parseStaticsKits();
//	}

//	@Test
//	void objectHandlerTest(){
//		final SAXParserFactory factory = SAXParserFactory.newInstance();
//		try {
//			final SAXParser parser = factory.newSAXParser();
////			final ObjectHandler handler = new ObjectHandler(Json.createBuilderFactory(emptyMap()), new LinkedList<>());
//			final ObjectHandler handler = new ObjectHandler(Json.createBuilderFactory(emptyMap()), new LinkedList<>());
//			handler.getStack().add(handler);
//			parser.parse(new File("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\articles.xml"), handler);
//		}catch (ParserConfigurationException | SAXException | IOException e){
//			e.printStackTrace();
//		}
//
//	}
//	@Test
//	void articleSaxTest(){
//		SAXParserFactory factory = SAXParserFactory.newInstance();
//
//		try {
//			SAXParser parser = factory.newSAXParser();
//			ArticleProxy articleProxy = new ArticleProxy();
//			parser.parse(new File("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\articles.xml"), articleProxy);
//			List<Article> articles = articleProxy.getArticleList();
//			System.out.println(articles);
//		}catch (ParserConfigurationException | SAXException | IOException e){
//			e.printStackTrace();
//		}
//	}

//	@Test
//	void photoTest() throws IOException {
//		File file = new File("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\model\\sylobe\\images\\test2.png");
//		InputStream stream =  new FileInputStream(file);
//		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), MediaType.TEXT_HTML_VALUE, stream);
//		Photo photo = new Photo("test", new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
//		photo.setName("test");
//
////        File f = new File("/images/test2.png");
//
//
//
////        photo.setContent(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
//
//        assert(photo.getContent()!=null);
//
//        System.out.println("Content : "+photo.getContent());
////
//	}
//
//	@Test
//	void articleTest() throws IOException {
//		Article article = new Article("g", "b", "b", 10, "b", "b", "b", "g", "f");
//		assert article.getPhoto() != null;
//	}

}
