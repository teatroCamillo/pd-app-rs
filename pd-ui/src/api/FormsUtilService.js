import axios from "axios";
import AuthenticationService from "../components/utils/AuthenticationService";

const API_URL = 'http://localhost:8080';

class FormsUtilService {

    hasUserCompletedRiskTest = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/has-completed-risk-test`);
    };

    sendRiskFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.post(`${API_URL}/${userId}/risk`, answers);
    };

    updateRiskFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.put(`${API_URL}/${userId}/risk`, answers);
    };
}

export default new FormsUtilService();

