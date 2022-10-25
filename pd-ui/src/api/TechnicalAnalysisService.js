import axios from "axios";
import AuthenticationService from "../components/utils/AuthenticationService";

const API_URL = 'http://localhost:8080';

class TechnicalAnalysisService {

    getExchangeRateData = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/tech-analysis`);
    }

}

export default new TechnicalAnalysisService();