package com.example.authenticationapp.utils;


import com.example.authenticationapp.model.Address;
import com.example.authenticationapp.model.Company;
import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Kit;
import com.example.authenticationapp.model.sylobe.Photo;
import com.example.authenticationapp.model.user.Admin;
import com.example.authenticationapp.model.user.Client;
import com.example.authenticationapp.model.user.User;

import io.swagger.v3.core.util.Json;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class JsonParserHelper {

    /**
     * This class helps us to parse all our json objects
     */

    /**
     * Parse an Address json object and create an instance
     *
     * @param jsonObject Address json object
     * @return an instance of Address
     */
    public static Address parseAndCreateAddress(JSONObject jsonObject) {

        String streetNumber = jsonObject.get("streetNumber").toString();
        String streetName = jsonObject.get("streetName").toString();
        String postalCode = jsonObject.get("postalCode").toString();
        String town = jsonObject.get("town").toString();
        String country = jsonObject.get("country").toString();

        return new Address(streetNumber, streetName, postalCode, town, country);
    }

    /**
     * Parse an Company json object and create an instance
     *
     * @param jsonObject Company json object
     * @return an instance of Company
     */
    public static Company parseAndCreateCompanyFromUI(JSONObject jsonObject) {
        String code = jsonObject.get("code").toString();
        String socialReason = jsonObject.get("socialReason").toString();
        String clientType = jsonObject.get("clientType").toString();
        String label = jsonObject.get("label").toString();
        String countryCode = jsonObject.get("countryCode").toString();
        String countryName = jsonObject.get("countryName").toString();
        String zipCode = jsonObject.get("zipCode").toString();
        String city = jsonObject.get("city").toString();
        String recipient = jsonObject.get("recipient").toString();

        return new Company(code, socialReason, clientType, label, countryCode, countryName, zipCode, city, recipient);
    }

    /**
     * This function parses a json array and creates our Company object list
     * @param array companies
     * @return
     */
    public static List<Company> parseAndCreateCompanyListFromSylob(JSONArray array) {
        List<Company> companyList = new ArrayList<>();

        Iterator<JSONObject> iterator = array.iterator();

        while (iterator.hasNext()){
            JSONObject company = iterator.next();
            companyList.add(
              new Company(
                      company.get("code").toString(),
                      company.get("socialReason").toString(),
                      company.get("clientType").toString(),
                      company.get("label").toString(),
                      company.get("countryCode").toString(),
                      company.get("countryName").toString(),
                      company.get("zipCode").toString(),
                      company.get("city").toString(),
                      company.get("recipient").toString()
              )
            );
        }
        return companyList;
    }

    /**
     * Parse a json User object and create an instance
     * @param jsonObject User json object
     * @return an instance of User
     */
    public static User parseAndCreateUser(JSONObject jsonObject) throws ParseException {
        String firstName = jsonObject.get("firstName").toString();
        String lastName = jsonObject.get("lastName").toString();
        String email = jsonObject.get("email").toString();
        String password = jsonObject.get("password").toString();

        Object object = new JSONParser().parse(jsonObject.toString());
        JSONObject jo = (JSONObject) object;
        JSONObject jsonCompany = ((JSONObject) jo.get("company"));

        String roles = jsonObject.get("roles").toString();

        Company company = parseAndCreateCompanyFromUI(jsonCompany);

        return roles.contains(Constants.ADMIN)
                ? new Admin(firstName, lastName, email, password, company)
                : new Client(firstName, lastName, email, password, company);
    }

    /**
     * This function parses a json array and creates our Article object list
     * @param array articles
     * @return
     * @throws IOException
     */
        public static Set<Article> parseAndCreateArticles(JSONArray array) throws IOException {
        Set<Article> objectList = new HashSet<>();
        Iterator<JSONObject> iterator = array.iterator();
        while(iterator.hasNext()){
            JSONObject article = iterator.next();
            objectList.add(new Article(
                    article.get("articleCode").toString(),
                    article.get("number").toString(),
                    article.get("index").toString(),
                    Double.parseDouble(article.get("quantity").toString()),
                    article.get("validityDate").toString(),
                    article.get("label").toString(),
                    article.get("articleStatus").toString(),
                    article.get("codeClient").toString(),
                    article.get("socialReason").toString(),
                    article.get("name").toString()
            ));
        }

        return  objectList;
    }

    /**
     * This function parse a jSon object, which contains a list of articles.
     * These articles are selected by the use for making a regularization voucher
     * @param jsonObject
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static Set<Article> parseAndCreateArticlesForVoucher(Object jsonObject) throws IOException, ParseException {
        Set<Article> articles = new HashSet<>();
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(Json.pretty(jsonObject));

        Iterator<JSONObject> iterator = array.iterator();

        while (iterator.hasNext()){

            JSONObject object = iterator.next();
            Object quantity = object.get("neededQuantity");
            String quantityStr = (quantity!=null) ? quantity.toString() : "1";
            articles.add(new Article(object.get("articleCode").toString(),
                                    object.get("number").toString(),
                                    object.get("index").toString(),
                                    Double.parseDouble(quantityStr),
                                    object.get("validityDate").toString(),
                                    object.get("label").toString(),
                                    object.get("articleStatus").toString(),
                                    object.get("codeClient").toString(),
                                    object.get("socialReason").toString(),
                                    object.get("name").toString()
                               )
            );
        }
        return articles;
    }


    /**
     * This function parses a json array and creates our Kit object lis
     * @param array
     * @return List of Kit
     */
    public static Set<Kit> parseAndCreateSylobKits(JSONArray array) {
        Set<Kit> kitsList = new HashSet<>();

        Iterator<JSONObject> iterator = array.iterator();

        while (iterator.hasNext()){
            JSONObject kit = iterator.next();
            kitsList.add(new Kit(
                    kit.get("label").toString(),
                    kit.get("kitStatus").toString(),
                    kit.get("clientCode").toString(),
                    kit.get("socialReason").toString()
            ));
        }

        return kitsList;
    }


    /**
     * These other functions are used for working with local data (instead of sylob)
     * @return
     * @throws IOException
     */
    public static Set<Article> parseStaticsArticles() throws IOException {

        Set<Article> articles = new HashSet<>();
        JSONObject object = SylobRequests.parseXmlLocalArticles();

        JSONObject resultatQueryWS = (object != null) ? (JSONObject) object.get("resultatQueryWS"): null;
       JSONArray array = (JSONArray) resultatQueryWS.get("ligneResultatWS");

      Iterator<JSONObject> iterator = array.iterator();
      int i = 0;
      while (iterator.hasNext() && i<20){
          JSONArray jsonArticle = (JSONArray) iterator.next().get("valeur");
          articles.add(new Article(
                                    jsonArticle.get(0).toString(),
                  jsonArticle.get(1).toString(),
                  jsonArticle.get(2).toString(),
                  Double.parseDouble(jsonArticle.get(3).toString()),
                  jsonArticle.get(4).toString(),
                  jsonArticle.get(5).toString(),
                  jsonArticle.get(6).toString(),
                  jsonArticle.get(7).toString(),
                  jsonArticle.get(8).toString(),
                  null
          ));
          i++;
      }

      return articles;
    }

    public static Set<Kit> parseStaticsKits() throws IOException, ParseException {

        Set<Kit> kits = new HashSet<>();
        JSONObject object = SylobRequests.parseXmlLocalKits();

        JSONObject jsonKits = (object != null) ? (JSONObject) object.get("resultatQueryWS"): null;
        JSONArray array = (JSONArray) jsonKits.get("ligneResultatWS");

        Iterator<JSONObject> iterator = array.iterator();

        while (iterator.hasNext()){
            JSONArray jsonKit = (JSONArray) iterator.next().get("valeur");

            Kit kit = new Kit( jsonKit.get(0).toString(),
                    jsonKit.get(1).toString(),
                    jsonKit.get(2).toString(),
                    jsonKit.get(2).toString()
            );
            kit.setArticles(parseStaticsArticles());
            kits.add(kit);
        }

        return kits;
    }

    private static Photo createPhoto() throws IOException {
        final File file = new File("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\model\\sylobe\\images\\ALTDP1.png");
        InputStream stream =  new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), MediaType.IMAGE_PNG_VALUE, stream);

        Photo photo = new Photo();
        photo.setName("test");
        photo.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));

        return photo;
    }
}
