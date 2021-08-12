import {keycloak} from "../../App";
import {KEYCLOAK_CONFIG} from "../../constants/Constants";

/**
 * Checks if the authenticated user is admin
 * @param keycloak Keycloak instance
 * @returns {boolean}
 */
const isAdmin = (keycloak) => {
    return keycloak && keycloak.tokenParsed && keycloak.tokenParsed.resource_access[KEYCLOAK_CONFIG.CLIENT_ID].roles.includes(KEYCLOAK_CONFIG.ADMIN);
}

const isUser = (keycloak) => {
    return keycloak && keycloak.tokenParsed && keycloak.tokenParsed.resource_access[KEYCLOAK_CONFIG.CLIENT_ID].roles.includes(KEYCLOAK_CONFIG.USER);
}

const getUserEmail = () => {
    return keycloak.authenticated && keycloak.tokenParsed && keycloak.tokenParsed.preferred_username
}

const handleLogError = (error) => {

    switch (error) {
        case error.response:
            console.log("Error Response: "+error.response.data);
            break;
        case error.request:
            console.log("Error Request: "+error.request.data);
            break;
        default:
            console.log("Error Message: "+error.message);
    }
}

export const helpers = {
    isAdmin,
    isUser,
    handleLogError,
    getUserEmail
}