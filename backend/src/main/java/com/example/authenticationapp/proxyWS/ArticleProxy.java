package com.example.authenticationapp.proxyWS;

import com.example.authenticationapp.model.sylobe.Article;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ArticleProxy extends DefaultHandler {

    private final String LINE_RES_WS = "ligneResultatWS";
    private final String VALUE = "valeur";

    private ArticleDocumentContent content = null;
    private Article article = null;
    private StringBuilder builder = null;
    private int index;

    @Override
    public void characters(char[] ch, int start, int length) {
        if(builder == null){
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
                        switch (index){
                            case 1:
                                article.setArticleCode(builder.toString());
                                break;
                            case 2:
                                article.setNumber(builder.toString());
                                break;
                            case 3:
                                article.setIndex(builder.toString());
                                break;
                            case 4:
                                article.setQuantity(Double.parseDouble(builder.toString()));
                                break;
                            case 5:
                                article.setValidityDate(builder.toString());
                                break;
                            case 6:
                                article.setLabel(builder.toString());
                                break;
                            case 7:
                                article.setArticleStatus(builder.toString());
                                break;
                            case 8:
                                article.setCodeClient(builder.toString());
                            case 9:
                                article.setSocialReason(builder.toString());
                                break;
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
