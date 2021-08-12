import React from "react";

const dev = {
     url: {
            KEYCLOAK_BASE_URL: "http://localhost:8080",
            API_BASE_URL: "http://localhost:8000"
        }
}

export const config = dev;

/**
 * Keycloak config
 */
const REALM = "newclip-services";
const CLIENT_ID = "authentication-app";
const ADMIN = "ADMIN";
const USER = "USER";

export const KEYCLOAK_CONFIG = {
    REALM,
    CLIENT_ID,
    ADMIN,
    USER
}

/**
 * All endpoints
 */
const USER_LIST_ENDPOINT = "/api/user/list";
const USER_PROFILE_ENDPOINT = "/api/user/";
const ADD_USER_ENDPOINT = "/api/user/add";
const COMPANY_LIST_ENDPOINT= "/api/company/list";
const ADD_COMPANY_ENDPOINT= "/api/company/add";
const ADDRESS_LIST_ENDPOINT= "/api/address/list";
const ADD_ADDRESS_ENDPOINT= "/api/address/add";
const GET_COMPANY_BY_CODE_ENDPOINT = "/api/company/";
const SYLOB_ENDPOINT = "/api/sylob/data";
const SYLOB_DATA_ENDPOINT = "/api/sylob/result";
const ADD_SYLOB_ARTICLE_ENDPOINT = "/api/sylob/article/add";
const SYLOB_KIT_LIST_ENDPOINT = "/api/sylob/kitList";
const FIND_KIT_BY_LABEL_ENDPOINT = "/api/sylob/kit/"


export const APP_ENDPOINTS = {
    USER_PROFILE_ENDPOINT,
    USER_LIST_ENDPOINT,
    COMPANY_LIST_ENDPOINT,
    ADD_COMPANY_ENDPOINT,
    ADDRESS_LIST_ENDPOINT,
    ADD_ADDRESS_ENDPOINT,
    GET_COMPANY_BY_CODE_ENDPOINT,
    ADD_USER_ENDPOINT,
    SYLOB_ENDPOINT,
    SYLOB_DATA_ENDPOINT,
    ADD_SYLOB_ARTICLE_ENDPOINT,
    SYLOB_KIT_LIST_ENDPOINT,
    FIND_KIT_BY_LABEL_ENDPOINT
}