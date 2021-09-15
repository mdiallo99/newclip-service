import instance from "axios";
import {APP_ENDPOINTS} from "../../constants/Constants";
import {helpers} from "./Helpers";
import {userToken} from "../../App";

/**
 * Manages the Bearer token for authentication
 * @param token
 * @returns {`Bearer ${string}`}
 */
const bearerAuth = (token) => {
    return `Bearer ${token}`
}

/**
 * Gets a user by his token
 * @param token user token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getUserMe = (token) => {
    return instance.get(APP_ENDPOINTS.USER_PROFILE_ENDPOINT, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Load the user profile
 * @param token
 * @param user
 * @returns {Promise<AxiosResponse<any>>}
 */
const saveUserMe = (token, user) => {

    return instance.post(APP_ENDPOINTS.USER_PROFILE_ENDPOINT +'me', user, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Make a reauest for getting the list of users
 * @param token user token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getUserList = (token) => {

    return instance.get(APP_ENDPOINTS.USER_LIST_ENDPOINT, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Adds a new User
 * @param user json object
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const addUser = (user, token) => {
    return instance.post(APP_ENDPOINTS.ADD_USER_ENDPOINT, user, {
            headers: {
                'Content-type': 'application/json',
                'Authorization': bearerAuth(token)
            }
    })
}

/**
 * Gets user by email request
 * @param email
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getUserByEmail = (email, token) => {
    return instance.get(APP_ENDPOINTS.USER_PROFILE_ENDPOINT+email, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Request for getting available articles in the User Company
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getSylobData = async (token) => {
    const request =  await APP_API.getUserByEmail(helpers.getUserEmail(), userToken)
    const companyCode = await request.data.company.code;

    return instance.get(APP_ENDPOINTS.SYLOB_ENDPOINT, {
        headers: {
                 'Access-Control-Allow-Origin':"*",
                 'Authorization': bearerAuth(token)
        }
    })
}

/**
 * Returns the list of Companies
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getCompanyList = (token) => {
    return instance.get(APP_ENDPOINTS.COMPANY_LIST_ENDPOINT, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Adds new company
 * @param company
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const saveCompany = (company, token) => {
    return instance.post(APP_ENDPOINTS.ADD_COMPANY_ENDPOINT, company, {
        headers: {'Content-type': 'application/json',
                  'Authorization': bearerAuth(token)}
                }
                )
}

/***
 * Gets a specific company by its code
 * @param companyCode
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getCompanyByCode = (companyCode, token) => {
    return instance.get(APP_ENDPOINTS.GET_COMPANY_BY_CODE_ENDPOINT+companyCode, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Gets the list of addresses
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getAddressList = (token) => {
    return instance.get(APP_ENDPOINTS.ADDRESS_LIST_ENDPOINT, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Adds the Address
 * @param address
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const saveAddress = (address, token) => {
    return instance.post(
        APP_ENDPOINTS.ADD_ADDRESS_ENDPOINT, address, {
            headers: { 'Content-type': 'application/json',
                        'Authorization': bearerAuth(token),
                     }
        }
    )
}

/**
 * Load the list of articles
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const loadArticleList = (token) => {
    return instance.get(APP_ENDPOINTS.SYLOB_DATA_ENDPOINT, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Create a new Article from Sylob
 * @param sylobObject
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const addArticlesInVouchar = (articles, token) => {
    return instance.post(APP_ENDPOINTS.ADD_SYLOB_ARTICLE_ENDPOINT, articles, {
            headers: { 'Content-type': 'application/json',
            'Authorization': bearerAuth(token),
            }
         }
         )
}

/**
 * Gets available kits in the User's Company
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getKitsListFromSylob = async (token) => {
    // const request =  await APP_API.getUserByEmail(helpers.getUserEmail(), userToken)
    // const companyCode = await request.data.company.code;

    return instance.get(APP_ENDPOINTS.SYLOB_KIT_LIST_ENDPOINT, {
        headers: {
            'Access-Control-Allow-Origin': "*",
            'Authorization': bearerAuth(token)
        }
    })
}

/**
 * Gets a specific Kit by its label (label is a key in Sylob)
 * @param label
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
const getKitByLabel = (label, token) => {

    return instance.get(APP_ENDPOINTS.FIND_KIT_BY_LABEL_ENDPOINT+label, {
        headers: {'Authorization': bearerAuth(token)}
    })
}

/**
 * Exportation
 * @type {{getCompanyList: (function(*=): Promise<AxiosResponse<*>>), getKitByLabel: (function(*, *=): Promise<AxiosResponse<*>>), getSylobData: (function(*=): Promise<AxiosResponse<any>>), getAddressList: (function(*=): Promise<AxiosResponse<*>>), addUser: (function(*=, *=): Promise<AxiosResponse<*>>), getKitsListFromSylob: (function(*=): Promise<AxiosResponse<any>>), getUserList: (function(*=): Promise<AxiosResponse<*>>), getUserByEmail: (function(*, *=): Promise<AxiosResponse<*>>), saveCompany: (function(*=, *=): Promise<AxiosResponse<*>>), saveAddress: (function(*=, *=): Promise<AxiosResponse<*>>), addArticlesInVouchar: (function(*=, *=): Promise<AxiosResponse<*>>), getCompanyByCode: (function(*, *=): Promise<AxiosResponse<*>>), getUserMe: (function(*=): Promise<AxiosResponse<*>>), saveUserMe: (function(*=, *=): Promise<AxiosResponse<*>>), loadArticleList: (function(*=): Promise<AxiosResponse<*>>)}}
 */
export const APP_API = {
    getUserMe,
    saveUserMe,
    getUserList,
    getCompanyList,
    getAddressList,
    saveAddress,
    saveCompany,
    getCompanyByCode,
    addUser,
    getUserByEmail,
    getSylobData,
    loadArticleList,
    addArticlesInVouchar,
    getKitsListFromSylob,
    getKitByLabel
}