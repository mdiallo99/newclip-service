import axios from "axios";
import {config} from "../../constants/Constants";

/**
 * Axios inxtance for requests between the Front and the Back
 */
const instance = axios.create({
    baseURL: config.url.API_BASE_URL
});

instance.interceptors.response.use(response => {
    return response;
}, (error) => {
    if (error.response.status === 404) {
        return {status: error.response.status};
    }
    return Promise.reject(error.response);
});

export default instance