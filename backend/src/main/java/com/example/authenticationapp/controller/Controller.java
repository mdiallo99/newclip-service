package com.example.authenticationapp.controller;

import com.example.authenticationapp.model.Address;
import com.example.authenticationapp.model.Company;
import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Kit;
import com.example.authenticationapp.utils.PDFHelper;
import com.example.authenticationapp.model.sylobe.Voucher;
import com.example.authenticationapp.model.user.User;
import com.example.authenticationapp.services.AppServices;
import com.example.authenticationapp.utils.JsonParserHelper;
import com.example.authenticationapp.utils.SylobRequests;
import com.example.authenticationapp.utils.URLEncoderHelper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.authenticationapp.config.SwaggerConfig.BEARER_KEY;
import static com.example.authenticationapp.utils.Constants.*;
import static com.example.authenticationapp.utils.JsonParserHelper.*;
import static com.example.authenticationapp.utils.SylobRequests.*;

@RequiredArgsConstructor
@RestController
public class Controller {

    /**
     * this is the instance of UserService, it allows to do all requests in our database
     */
    private final AppServices appServices;
    @Context
    private SecurityContext securityContext;
//    @Autowired
//    private JavaMailSender mailSender;

    /**
     * This function allows to add a ne user
     * @param jsonObject a json object received by POST request from UI
     * @return the created user
     * @throws ParseException JSONObject parsing exception
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @PostMapping(ADD_USER_ENDPOINT)
    public User saveUser(@Valid @RequestBody JSONObject jsonObject) throws ParseException {
        User user = parseAndCreateUser(jsonObject);
        return appServices.addUser(user);
    }

    /**
     * The request for userList
     * @return User list
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(USER_LIST_ENDPOINT)
    public List<User> getUsersList(){
        return appServices.getUsersList();
    }

    /**
     * The function which get a user by this email for profile filling
     * @param email user's email
     * @return user
     */
    @Operation (security = {@SecurityRequirement( name = BEARER_KEY)})
    @GetMapping(USER_PROFILE_ENDPOINT)
    public User getProfile(@Valid @PathVariable String email){
        return appServices.getProfile(email);
    }

    /**
     * Company requests
     */
    /**
     * The request for company list
     * @return
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(COMPANY_LIST_ENDPOINT)
    public List<Company> getCompanyLis(){
        return appServices.getCompanyList();
    }

    /**
     * The request for adding a new company
     * @param jsonObject received by POST request from UI
     * @return the company
     * @throws ParseException
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @PostMapping(ADD_COMPANY_ENDPOINT)
    public Company addCompany(@Valid @RequestBody JSONObject jsonObject) {
        Company company = parseAndCreateCompanyFromUI(jsonObject);
        return appServices.addCompany(company);
    }

    /**
     * get a specific company by its code
     * @param code company code
     * @return the company
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(GET_COMPANY_BY_CODE_ENDPOINT)
    public Company getCompanyByCode(@Valid @PathVariable String code){
        return appServices.findCompanyByCode(code);
    }

    /**
     * Address requests
     */
    /**
     * For getting address list
     * @return Address list
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(ADDRESS_LIST_ENDPOINT)
    public List<Address> getAddressLis(){
        return appServices.getAddressList();
    }

    /**
     * For adding new Address
     * @param jsonObject  received by POST request from UI
     * @return the new Address created
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @PostMapping(ADD_ADDRESS_ENDPOINT)
    public Address addAddress(@Valid @RequestBody JSONObject jsonObject) {
        return appServices.addAddress(
                parseAndCreateAddress(jsonObject)
        );
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(SYLOB_BACKEND_ENDPOINT)
    @ResponseBody
    public Set<Article> getDataFromSylob(Principal principal)
            throws NoSuchAlgorithmException,
            KeyManagementException, ParseException, IOException {

        User user = appServices.getProfile(principal.getName());

        final String CODE_CLIENT = user.getCompany().getCode();

        JSONObject jsonObject =  getArticlesFromSylob(CODE_CLIENT);

        Set<Article> result = JsonParserHelper.parseAndCreateArticleListFromSylob(jsonObject);

        if(result !=null ){
            appServices.saveSylobData(result);

            return result;
        }
        return  null;
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(SYLOB_COMPANY_LIST_ENDPOINT)
    @ResponseBody
    public List<Company> saveCompanyListFromSylob() throws NoSuchAlgorithmException, ParseException, KeyManagementException {

        JSONObject companiesJson = getCompanyListFromSylb();

        List<Company> companies = parseAndCreateCompanyListFromSylob(companiesJson);

        return appServices.saveCompanyListFromSylob(companies);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(GET_COMPANY_BY_SOCIAL_REASON_ENDPOINT)
    @ResponseBody
    public Company getCompanyBySocialReason(@PathVariable String socialReason){
        return appServices.findCompanyBySocialReason(URLEncoderHelper.encodeValue(socialReason));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @DeleteMapping(FIND_DELETE_ADDRESS_ENDPOINT)
    public void deleteAddress(@PathVariable String id){
        System.out.println(appServices.getAddress(id));
//        appServices.deleteAddress(id);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(FIND_DELETE_ADDRESS_ENDPOINT)
    public Optional<Address> findAddress(@PathVariable String id){
        return appServices.getAddress(id);
    }


    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(SYLOB_KIT_LIST_ENDPOINT)
    @ResponseBody
    public List<Kit> getKitListFromSylob(Principal principal) throws NoSuchAlgorithmException, KeyManagementException, ParseException, IOException {


//        User user = appServices.getProfile(principal.getName());
//
//        final String CODE_CLIENT = user.getCompany().getCode();
//
//        JSONObject kitsJsonObject = SylobRequests.getKitListFromSylob(CODE_CLIENT);
//
//        Set<Kit> kits = kitsJsonObject!=null ? JsonParserHelper.parseAndCreateSylobKits(kitsJsonObject): null;
//
//        kits.stream().map(kit -> {
//            JSONObject jsonObject = null;
//            try {
//                jsonObject = getArticlesFromKit(CODE_CLIENT, kit.getLabel());
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (KeyManagementException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            Set<Article> result = null;
//            try {
//                result = JsonParserHelper.parseAndCreateArticleListFromSylob(jsonObject);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            kit.setArticles(result);
//            return null;
//        }).collect(Collectors.toSet());

        return appServices.saveKitsFromSylob(JsonParserHelper.parseStaticsKits());
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @DeleteMapping(DELETE_SYLOB_ARTICLE_ENDPOINT)
    public void deleteSylobArticle(@PathVariable String articleCode){
        if(articleCode != null) appServices.deleteArticleByCode(articleCode);
    }


    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(ADD_IMAGE_ENDPOINT)
    public String addImage() throws IOException {
        final File file = new File("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\model\\sylobe\\images\\ALTDP1.png");
       InputStream stream =  new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), MediaType.IMAGE_PNG_VALUE, stream);
        return appServices.addImage("test", multipartFile);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @RequestMapping(value = ADD_SYLOB_ARTICLE_ENDPOINT, method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_PDF_VALUE})
    public Document addArticlesInVoucher(@RequestBody Object object, HttpServletResponse response) throws IOException, ParseException, DocumentException, NoSuchAlgorithmException, KeyManagementException {
      // SylobRequests.turnOnSslChecking();

        Voucher voucher = (object != null) ? appServices.addArticlesInVoucher(parseAndCreateArticlesForVoucher(object)): null;
//        ByteArrayInputStream result = (voucher != null) ? PDFHelper.voucherReport(voucher) : null;
        var headers = new HttpHeaders();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=voucher_" + currentDateTime + ".pdf";

        headers.add(headerKey, headerValue);

        PDFHelper helper = new PDFHelper(voucher);
//
//        Document document = helper.export(response);
//        System.out.println(document.toString());
//        helper.sendEmail();
//        URL classURL  = this.getClass().getResource("com.sun.mail.util.TraceInputStream");
//        System.out.println("resource : "+classURL.getFile());
        helper.email();


//        String from = "mdiallo@gmail.com";
//        String to = "dialloakh@gmail.com";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject("This is a plain text email");
//        message.setText("Hello guys! This is a plain text email.");
//
//        mailSender.send(message);

        return  null;

//       return (result != null) ? ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(result))
//                : null;

//        InputStreamResource streamResource = new InputStreamResource(new FileInputStream(String.valueOf(resource)));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(resource.getBody().contentLength())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(streamResource);
    }
}