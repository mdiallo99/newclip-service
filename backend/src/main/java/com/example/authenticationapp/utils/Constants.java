package com.example.authenticationapp.utils;

public class Constants {

    /**
     * Roles
     */
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";


    /**
     * For User
     */
    public static final String USER_LIST_ENDPOINT = "/api/user/list";
    public static final String USER_PROFILE_ENDPOINT = "/api/user/{email}";
    public static final String ADD_USER_ENDPOINT = "/api/user/add";
    /**
     * For Company
     */
    public static final String COMPANY_LIST_ENDPOINT = "/api/company/list";
    public static final String ADD_COMPANY_ENDPOINT = "/api/company/add";
    public static final String GET_COMPANY_BY_CODE_ENDPOINT = "/api/company/{code}";
    public static final String GET_COMPANY_BY_SOCIAL_REASON_ENDPOINT = "/api/company/name/{socialReason}";
    /**
     * For Address
     */
    public static final String ADDRESS_LIST_ENDPOINT = "/api/address/list";
    public static final String ADD_ADDRESS_ENDPOINT = "/api/address/add";
    public static final String DELETE_ADDRESS_ENDPOINT = "/api/address";
    public static final String FIND_DELETE_ADDRESS_ENDPOINT = "/api/address/{id}";

    /**
     * Sylob
     */

    public static final String SYLOB_URL = "https://srv-sylob2:8443/rest/query/WS_REGUL_LOT_EN_STOCK/resultat?";
    public static final String SYLOB_BACKEND_ENDPOINT = "/api/sylob/data";
    public static final String SYLOB_DATA_ENDPOINT = "/api/sylob/result";
    public static final String SYLOB_COMPANY_LIST = "https://srv-sylob2:8443/rest/query/WS_CLIENT_LIST/resultat?";
    public static final String SYLOB_COMPANY_LIST_ENDPOINT = "/api/sylob/companyList";

    public static final String ADD_SYLOB_ARTICLE_ENDPOINT = "/api/sylob/article/add";
    public static final String DELETE_SYLOB_ARTICLE_ENDPOINT = "/api/sylob/article/{articleCode}";

    public static final String SYLOB_KIT_LIST_URL = "https://srv-sylob2:8443/rest/query/WS_DEPOT_PRET_PAR_CLIENT/resultat?";
    public static final String SYLOB_KIT_LIST_ENDPOINT = "/api/sylob/kitList";
    public static final String FIND_KIT_BY_LABEL_ENDPOINT = "/api/sylob/kit/{label}";

    public static final String ADD_IMAGE_ENDPOINT = "/api/image/add";

    /**
     * For the swagger doc
     */
    public static final String SWAGGER_CONFIG_URL = "/swagger-ui.html";
    public static final String SWAGGER_UI_URL = "/swagger-ui/**";
    public static final String SWAGGER_DOC_URL = "/v3/api-docs";
    public static final String SWAGGER_DOCS_URL = "/v3/api-docs/**";

    /**
     * Requests params
     */
    public static final int LIMIT = 1000;
    public static final String CLIENT = "25";
    public static final String LOT = "25";
    public static final String KIT = "25";
    public static final String CODE_ARTICLE = "25";
    public static final String INDICE = "25";


}
