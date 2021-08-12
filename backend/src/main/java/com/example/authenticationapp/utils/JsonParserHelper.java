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

    public static List<Company> parseAndCreateCompanyListFromSylob(JSONObject jsonObject) {
        List<Company> companyList = new ArrayList<>();

        JSONArray lineResult = (JSONArray) jsonObject.get("ligneResultatWS");

        Iterator<JSONObject> iterator = lineResult.iterator();

        while (iterator.hasNext()) {
            JSONArray jsonArray = (JSONArray) iterator.next().get("valeur");
            companyList.add(new Company(
                            jsonArray.get(0).toString(),
                            jsonArray.get(1).toString(),
                            jsonArray.get(2).toString(),
                            jsonArray.get(3).toString(),
                            jsonArray.get(4).toString(),
                            jsonArray.get(5).toString(),
                            jsonArray.get(6).toString(),
                            jsonArray.get(7).toString(),
                            jsonArray.get(8).toString()
                    )
            );
        }

        return companyList;
    }

    /**
     * Parse an User json object and create an instance
     *
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

    public static Set<Article> parseAndCreateArticleListFromSylob(JSONObject jsonObject) throws IOException {
        Set<Article> objectList = new HashSet<>();
        if(jsonObject != null){
            JSONArray lineResult = (JSONArray) jsonObject.get("ligneResultatWS");

            Iterator<JSONObject> iterator = lineResult.iterator();

            while (iterator.hasNext()) {
                JSONArray jsonArray = (JSONArray) iterator.next().get("valeur");

                objectList.add(
                        new Article(
                                    jsonArray.get(0).toString(),
                                    jsonArray.get(1).toString(),
                                    jsonArray.get(2).toString(),
                                    Double.parseDouble(jsonArray.get(3).toString()),
                                    jsonArray.get(4).toString(),
                                    jsonArray.get(5).toString(),
                                    jsonArray.get(6).toString(),
                                    jsonArray.get(7).toString(),
                                    jsonArray.get(8).toString(),
                                    jsonArray.get(9).toString()
                         )
                );
            }

            return objectList;
        }
       return  null;
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

    public static Set<Article> parseAndCreateArticlesForVoucher(Object jsonObject) throws IOException, ParseException {
        Set<Article> articles = new HashSet<>();
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(Json.pretty(jsonObject));

        Iterator<JSONObject> iterator = array.iterator();

        while (iterator.hasNext()){
            JSONObject object = iterator.next();
            articles.add(new Article(  object.get("articleCode").toString(),
                                    object.get("number").toString(),
                                    object.get("index").toString(),
                                    Double.parseDouble(object.get("quantity").toString()),
                                    object.get("validityDate").toString(),
                                    object.get("label").toString(),
                                    object.get("articleStatus").toString(),
                                    object.get("codeClient").toString(),
                                    object.get("socialReason").toString(),
                                    object.get("name").toString()
                               )
            );
            System.out.println(object);
        }
        return articles;
    }

    public static Set<Kit> parseAndCreateSylobKits(JSONObject jsonObject) {
        Set<Kit> kitsList = new HashSet<>();
        JSONArray lineResult = (JSONArray) jsonObject.get("ligneResultatWS");

        Iterator<JSONObject> iterator = lineResult.iterator();

        while (iterator.hasNext()) {
            JSONArray jsonArray = (JSONArray) iterator.next().get("valeur");
            kitsList.add(
                    new Kit(
                            jsonArray.get(0).toString(),
                            jsonArray.get(1).toString(),
                            jsonArray.get(2).toString(),
                            jsonArray.get(3).toString()
                    )
            );
        }

        return kitsList;
    }

    public static Set<Article> parseStaticsArticles() throws IOException, ParseException {

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

        System.out.println(kits);
        return kits;
    }
}
