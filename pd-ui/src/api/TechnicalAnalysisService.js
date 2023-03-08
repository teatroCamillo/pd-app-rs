import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";

const API_URL = 'http://localhost:8080';

class TechnicalAnalysisService {

    runTechAnalysis = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/tech-analysis`);
    }

}

export default new TechnicalAnalysisService();