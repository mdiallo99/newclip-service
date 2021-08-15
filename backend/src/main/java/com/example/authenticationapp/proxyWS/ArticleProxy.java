package com.example.authenticationapp.proxyWS;

import com.example.authenticationapp.model.sylobe.Article;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ArticleProxy extends DefaultHandler {

    private final String LINE_RES_WS = "ligneResultatWS";
    private final String VALUE = "valeur";

    private ArticleDocumentContent content = null;
    private Article article = null;
    private StringBuilder builder;
    private int index;

    @Override
    public void characters(char[] ch, int start, int length) {
        if(article == null){
            builder = new StringBuilder();
        }else {
            builder.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() {
        content = new ArticleDocumentContent();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
            switch (qName){
                case LINE_RES_WS:
                    article = new Article();
                    index = 0;
                    break;
                case VALUE:
                    builder  = new StringBuilder();
                    index ++;
                    break;
            }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
            switch (qName){
                case LINE_RES_WS:
                    this.content.addArticle(article);
                    break;

                case VALUE:
                    if(index == 1){
                        article.setArticleCode(builder.toString());
                    }
                    if(index == 2){
                        article.setNumber(builder.toString());
                    }
                    if(index == 3){
                        article.setIndex(builder.toString());
                    }
                    if(index == 4){
                        article.setQuantity(Double.parseDouble(builder.toString()));
                    }
                    if(index == 5){
                        article.setValidityDate(builder.toString());
                    }
                    if(index == 6){
                        article.setLabel(builder.toString());
                    }
                    if(index == 7){
                        article.setArticleStatus(builder.toString());
                    }
                    if(index == 8){
                        article.setCodeClient(builder.toString());
                    }
                    if(index == 9){
                        article.setSocialReason(builder.toString());
                    }

                    break;
            }
    }

    public ArticleDocumentContent getContent() {
        return content;
    }

    public Article getArticle() {
        return article;
    }
}
