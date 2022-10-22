import axios from "axios";
import AuthenticationService from "../components/utils/AuthenticationService";

const API_URL = 'http://localhost:8080';

class FormsUtilService {

    getAllRiskQuestions = () => {
        return axios.get(`${API_URL}/get-all-risk-questions`)
    }

    getAllGamblingQuestions = () => {
        return axios.get(`${API_URL}/get-all-gambling-questions`)
    }

    hasUserCompletedRiskTest = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/has-completed-risk-test`);
    };

    hasUserCompletedGamblingTest = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/has-completed-gambling-test`);
    };

    sendRiskFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.post(`${API_URL}/${userId}/risk`, answers);
    };

    sendGamblingFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.post(`${API_URL}/${userId}/gambling`, answers);
    };

    updateRiskFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.put(`${API_URL}/${userId}/risk`, answers);
    };

    updateGamblingFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.put(`${API_URL}/${userId}/gambling`, answers);
    };
}

export default new FormsUtilService();

