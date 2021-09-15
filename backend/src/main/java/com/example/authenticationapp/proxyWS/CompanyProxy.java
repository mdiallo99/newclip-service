package com.example.authenticationapp.proxyWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class CompanyProxy extends DefaultHandler {

    /**
     * This class helps us to parse the received from sylob request to JSON
     * The request which returns COMPANIES (HOSPITALS)
     */

    private final String LINE_RES_WS = "ligneResultatWS";
    private final String VALUE = "valeur";

    private StringBuilder builder = null;
    private int index;
    private final JSONArray array = new JSONArray();
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
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case LINE_RES_WS:
                object = new JSONObject();
                index = 0;
                break;
            case VALUE:
                builder = new StringBuilder();
                index++;
                break;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case LINE_RES_WS:
                this.array.add(object);
                break;

            case VALUE:
                switch (index) {
                    case 1:
                        object.put("code", builder.toString());
                        break;
                    case 2:
                        object.put("socialReason", builder.toString());
                        break;
                    case 3:
                        object.put("clientType", builder.toString());
                        break;
                    case 4:
                        object.put("label", builder.toString());
                        break;
                    case 5:
                        object.put("countryCode", builder.toString());
                        break;
                    case 6:
                        object.put("countryName", builder.toString());
                        break;
                    case 7:
                        object.put("zipCode", builder.toString());
                        break;
                    case 8:
                        object.put("city", builder.toString());
                        break;
                    case 9:
                        object.put("recipient", builder.toString());
                        break;
                }

                break;
        }
    }

    public JSONArray getArray() {
        return array;
    }
}
