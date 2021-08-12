package com.example.authenticationapp.utils;

import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;

import javax.json.Json;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import static com.example.authenticationapp.utils.Constants.*;


public class SylobRequests {


    private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
    };

    public static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException {
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext.getInstance("SSL").init(null, null, null);
    }

    private SylobRequests() {
        throw new UnsupportedOperationException("Do not instantiate libraries.");
    }

    public static JSONObject getArticlesFromSylob(final String CODE_CLIENT)
            throws NoSuchAlgorithmException,
            KeyManagementException, ParseException {

        SylobRequests.turnOffSslChecking();


        String username = "CWS@@Cochise@@uu";
        String password = "iG4unXbyY9c6mWB6BL4g";
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
        String result = null;
        try {

            String query = "limite=" + LIMIT + "&CODE_CLIENT=" + CODE_CLIENT + "&CLIENT=%" + CLIENT + "&LOT=%" + LOT + "&KIT=" + KIT + "&CODE_ARTICLE=%" + CODE_ARTICLE + "&INDICE=%" + INDICE;
            URL url = new URL(SYLOB_URL + query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                result = output;
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        JSONParser parser = new JSONParser();

        return (result != null) ? (JSONObject) parser.parse(result) : null;
    }

    public static JSONObject getArticlesFromKit(final String CODE_CLIENT, final String KIT_LABEL)
            throws NoSuchAlgorithmException,
            KeyManagementException, ParseException {


        SylobRequests.turnOffSslChecking();
        String username = "CWS@@Cochise@@uu";
        String password = "iG4unXbyY9c6mWB6BL4g";
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
        String result = null;
        try {

            String query = "limite=" + LIMIT + "&CODE_CLIENT=" + CODE_CLIENT + "&CLIENT=%" + CLIENT + "&LOT=%" + LOT + "&KIT=" + URLEncoderHelper.encodeValue(KIT_LABEL) + "&CODE_ARTICLE=%" + CODE_ARTICLE + "&INDICE=%" + INDICE;
            URL url = new URL(SYLOB_URL + query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                result = output;
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        JSONParser parser = new JSONParser();

        return (result != null) ? (JSONObject) parser.parse(result) : null;
    }

    public static JSONObject getCompanyListFromSylb()
            throws NoSuchAlgorithmException,
            KeyManagementException, ParseException {

        SylobRequests.turnOffSslChecking();
        String username = "CWS@@Cochise@@uu";
        String password = "iG4unXbyY9c6mWB6BL4g";
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
        String result = null;
        try {
            URL url = new URL(SYLOB_COMPANY_LIST + "limite=" + LIMIT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                result = output;
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(result);
    }

    public static JSONObject getKitListFromSylob(final String CODE_CLIENT)
            throws NoSuchAlgorithmException,
            KeyManagementException, ParseException {

        SylobRequests.turnOffSslChecking();
        String username = "CWS@@Cochise@@uu";
        String password = "iG4unXbyY9c6mWB6BL4g";
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
        String result = null;
        try {
            URL url = new URL(SYLOB_KIT_LIST_URL + "limite=" + LIMIT + "&CODE_CLIENT=" + CODE_CLIENT + "&CLIENT=%" + CLIENT + "&KIT=%" + KIT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                result = output;
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        JSONParser parser = new JSONParser();

        return (result != null)?(JSONObject) parser.parse(result): null;
    }


    /**
     * Statics data
     */

    public static JSONObject parseXmlLocalArticles(){
        final String ARTICLES = "C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\articles.json";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(new FileReader(ARTICLES));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject parseXmlLocalKits(){
        final String KITS = "C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\kits.json";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(new FileReader(KITS));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}