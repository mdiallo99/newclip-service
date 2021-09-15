package com.example.authenticationapp.controller;

import com.example.authenticationapp.mailService.EmailService;
import com.example.authenticationapp.model.Address;
import com.example.authenticationapp.model.Company;
import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Kit;
import com.example.authenticationapp.model.sylobe.Voucher;
import com.example.authenticationapp.model.user.User;
import com.example.authenticationapp.services.AppServices;
import com.example.authenticationapp.utils.JsonParserHelper;
import com.example.authenticationapp.utils.PDFHelper;
import com.example.authenticationapp.utils.SylobRequests;
import com.example.authenticationapp.utils.URLEncoderHelper;
import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.authenticationapp.config.SwaggerConfig.BEARER_KEY;
import static com.example.authenticationapp.utils.Constants.*;
import static com.example.authenticationapp.utils.JsonParserHelper.*;
import static com.example.authenticationapp.utils.SylobRequests.*;

@RequiredArgsConstructor
@RestController
public class ProxyController {

    /**
     * this is the instance of UserService, it allows to do all requests in our database
     */
    private final AppServices appServices;
    private final EmailService mailService;


    /**
     * This function allows to add a ne user
     *
     * @param jsonObject a json object received by POST request from UI
     * @return the created user
     * @throws ParseException JSONObject parsing exception
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @PostMapping(PROXY_ADD_USER_ENDPOINT)
    public User saveUser(@RequestBody JSONObject jsonObject) throws ParseException {
        User user = parseAndCreateUser(jsonObject);
        return appServices.addUser(user);
    }

    /**
     * The request for userList
     *
     * @return User list
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(PROXY_USER_LIST_ENDPOINT)
    public List<User> getUsersList() {
        return appServices.getUsersList();
    }

    /**
     * The function which get a user by this email for profile filling
     *
     * @param email user's email
     * @return user
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(PROXY_USER_PROFILE_ENDPOINT)
    public User getProfile(@PathVariable String email) {
        return appServices.getProfile(email);
    }


    /**
     * The request for adding a new company
     *
     * @param jsonObject received by POST request from UI
     * @return the company
     * @throws ParseException
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @PostMapping(PROXY_ADD_COMPANY_ENDPOINT)
    public Company addCompany(@RequestBody JSONObject jsonObject) {
        Company company = parseAndCreateCompanyFromUI(jsonObject);
        return appServices.addCompany(company);
    }

    /**
     * get a specific company by its code
     *
     * @param code company code
     * @return the company
     */

    @GetMapping(PROXY_GET_COMPANY_BY_CODE_ENDPOINT)
    public Company getCompanyByCode(@PathVariable String code) {
        return appServices.findCompanyByCode(code);
    }

    /**
     * Address requests
     */
    /**
     * For getting address list
     *
     * @return Address list
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(PROXY_ADDRESS_LIST_ENDPOINT)
    public List<Address> getAddressLis() {
        return appServices.getAddressList();
    }

    /**
     * For adding new Address
     *
     * @param jsonObject received by POST request from UI
     * @return the new Address created
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @PostMapping(PROXY_ADD_ADDRESS_ENDPOINT)
    public Address addAddress(@RequestBody JSONObject jsonObject) {
        return appServices.addAddress(
                parseAndCreateAddress(jsonObject)
        );
    }

    /**
     * This function gets all available articles in the user hospital
     * @param principal connected user
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */

    @GetMapping(PROXY_SYLOB_ARTICLES_LIST_ENDPOINT)
    @ResponseBody
    public Set<Article> getDataFromSylob(Principal principal) throws NoSuchAlgorithmException,
                                                                    KeyManagementException,
                                                                     IOException,
                                                                    SAXException,
            ParserConfigurationException {

        User user = appServices.getProfile(principal.getName());

        final String CODE_CLIENT = user.getCompany().getCode();

        JSONArray jsonObject = getArticlesFromSylob(CODE_CLIENT);

        Set<Article> result = JsonParserHelper.parseAndCreateArticles(jsonObject);

        return appServices.saveSylobData(result);
    }


    /**
     * This function gets Newclip clients
     * @return List of clients (hospitals, etc....)
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    @GetMapping(PROXY_SYLOB_COMPANY_LIST_ENDPOINT)
    @ResponseBody
    public List<Company> saveCompanyListFromSylob() throws NoSuchAlgorithmException,
                                                            KeyManagementException,
                                                            ParserConfigurationException,
                                                            IOException,
                                                            SAXException {

        JSONArray companiesJson = getCompanyListFromSylob();

        List<Company> companies = parseAndCreateCompanyListFromSylob(companiesJson);

        return companies;
        // return appServices.saveCompanyListFromSylob(companies);
    }

    /**
     * This function gets a specific hospital by its social reason
     * @param socialReason
     * @return
     */
    @GetMapping(PROXY_GET_COMPANY_BY_SOCIAL_REASON_ENDPOINT)
    @ResponseBody
    public Company getCompanyBySocialReason(@PathVariable String socialReason) {
        return appServices.findCompanyBySocialReason(URLEncoderHelper.encodeValue(socialReason));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @DeleteMapping(PROXY_FIND_DELETE_ADDRESS_ENDPOINT)
    public void deleteAddress(@PathVariable String id) {
//        appServices.deleteAddress(id);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(PROXY_FIND_DELETE_ADDRESS_ENDPOINT)
    public Optional<Address> findAddress(@PathVariable String id) {
        return appServices.getAddress(id);
    }


    /**
     * this function gets available Kits for the user hospital (with limit = 1000)
     * @param principal connected user
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    @GetMapping(PROXY_SYLOB_KIT_LIST_ENDPOINT)
    @ResponseBody
    public Set<Kit> getKitListFromSylob(Principal principal) throws NoSuchAlgorithmException,
                                                                    KeyManagementException,
                                                                    IOException,
                                                                    ParserConfigurationException,
                                                                    SAXException {

        User user = appServices.getProfile(principal.getName());

        final String CODE_CLIENT = user.getCompany().getCode();

        JSONArray kitsJsonObject = SylobRequests.getKitListFromSylob(CODE_CLIENT);

        return (kitsJsonObject != null) ? JsonParserHelper.parseAndCreateSylobKits(kitsJsonObject) : null;
    }

    /**
     * this function gets all articles from a specific kit (by giving its label)
     * @param label kit label
     * @param principal
     * @return
     * @throws NoSuchAlgorithmException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws KeyManagementException
     * @throws SAXException
     */
    @GetMapping(PROXY_FIND_KIT_BY_LABEL_ENDPOINT)
    public Set<Article> getArticlesFromKit(@PathVariable String label, Principal principal) throws NoSuchAlgorithmException,
            ParserConfigurationException,
                                                                                                    IOException,
                                                                                                    KeyManagementException,
                                                                                                    SAXException {
        User user = appServices.getProfile(principal.getName());
        final String CODE_CLIENT = user.getCompany().getCode();

        JSONArray array = getArticlesFromKitRequest(CODE_CLIENT, label);

        return parseAndCreateArticles(array);
    }

    @DeleteMapping(PROXY_DELETE_SYLOB_ARTICLE_ENDPOINT)
    public void deleteSylobArticle(@PathVariable String articleCode) {
        if (articleCode != null) appServices.deleteArticleByCode(articleCode);
    }


    /**
     *  These two functions help to use local data (instead of sylob)
     */

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(PROXY_ADD_IMAGE_ENDPOINT)
    public String addImage() throws IOException {
        final File file = new File("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\model\\sylobe\\images\\ALTDP1.png");
        InputStream stream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), MediaType.IMAGE_PNG_VALUE, stream);
        return appServices.addImage("test", multipartFile);
    }

    @RequestMapping(value = PROXY_ADD_SYLOB_ARTICLE_ENDPOINT, method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_PDF_VALUE})
    public void createVoucher(@RequestBody Object object,
                              HttpServletResponse response,
                              Principal principal)
                              throws IOException,
                              ParseException,
                              DocumentException,
                              MessagingException {
        User editor = appServices.getProfile(principal.getName());

        response.setContentType("application/pdf");


        Voucher voucher = (object != null) ? appServices.addArticlesInVoucher(parseAndCreateArticlesForVoucher(object), editor) : null;

        PDFHelper helper = new PDFHelper(voucher);
        helper.export(response);

        mailService.sendMailWithAttachment("dialloakh@gmail.com", "Subject", "Body", "C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\voucher.pdf");

    }
}