package com.example.authenticationapp.proxyWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ArticleProxy extends DefaultHandler {


    /**
     * This class helps us to parse the received from sylob request to JSON
     * The request which returns ARTICLES
     */

    private final String LINE_RES_WS = "ligneResultatWS";
    private final String VALUE = "valeur";

    private StringBuilder builder = null;
    private int index;
    private final JSONArray jsonArray = new JSONArray();
    private JSONObject object = null;

    @Override
    public void characters(char[] ch, int start, int length) {
        if(builder == null){
            builder = new StringBuilder();
        }else {
            builder.append(ch, start, length);
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
            switch (qName){
                case LINE_RES_WS:
                    object = new JSONObject();
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
                    jsonArray.add(object);
                    break;

                case VALUE:
                        switch (index){
                            case 1:
                                object.put("articleCode", builder.toString());
                                break;
                            case 2:
                                object.put("number", builder.toString());
                                break;
                            case 3:
                                object.put("index", builder.toString());
                                break;
                            case 4:
                                object.put("quantity", builder.toString());
                                break;
                            case 5:
                                object.put("validityDate", builder.toString());
                                break;
                            case 6:
                                object.put("label", builder.toString());
                                break;
                            case 7:
                                object.put("articleStatus", builder.toString());
                                break;
                            case 8:
                                object.put("codeClient", builder.toString());
                            case 9:
                                object.put("socialReason", builder.toString());
                                break;
                            case 10:
                                object.put("name", builder.toString());
                                break;
                        }
                    break;
            }
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }
}
