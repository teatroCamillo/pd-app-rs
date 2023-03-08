import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";
import Constant from "../utils/Constant.js";

class FormsUtilService {

    getAllRiskQuestions = () => {
        return axios.get(`${Constant.API_URL}/get-all-risk-questions`)
    }

    getAllGamblingQuestions = () => {
        return axios.get(`${Constant.API_URL}/get-all-gambling-questions`)
    }

    hasUserCompletedRiskTest = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${Constant.API_URL}/${userId}/has-completed-risk-test`);
    };

    hasUserCompletedGamblingTest = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${Constant.API_URL}/${userId}/has-completed-gambling-test`);
    };

    sendRiskFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.post(`${Constant.API_URL}/${userId}/risk`, answers);
    };

    sendGamblingFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.post(`${Constant.API_URL}/${userId}/gambling`, answers);
    };

    updateRiskFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.put(`${Constant.API_URL}/${userId}/risk`, answers);
    };

    updateGamblingFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.put(`${Constant.API_URL}/${userId}/gambling`, answers);
    };
}

export default new FormsUtilService();

