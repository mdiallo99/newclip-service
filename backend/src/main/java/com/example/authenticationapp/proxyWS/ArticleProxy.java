package com.example.authenticationapp.proxyWS;

import com.example.authenticationapp.model.sylobe.Article;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleProxy extends DefaultHandler {

        private List<Article> articleList = null;
        private Article article = null;
        private StringBuilder data = null;


    public List<Article> getArticleList() {
        return articleList;
    }

    boolean bAttribute = false;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("Start Element");
        if(qName.equalsIgnoreCase("ligneResultatWS")){
            article = new Article();
            if(articleList == null){
                articleList = new ArrayList<>();
            }

//        }else{
//            int i = 0;
//            while (qName.equals("valeur") && i<9){
//                System.out.println("v: "+qName.lines());
//                i++;
//            }
        }else{
            bAttribute = true;
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if(bAttribute){
            System.out.println(data.toString());
        }
        System.out.println("End element ");
    }

    @Override
    public void characters(char ch[], int start, int length) {
        data.append(new String(ch, start, length));
    }
}
