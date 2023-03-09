import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";
import Constant from "../utils/Constant.js";

class TechnicalAnalysisService {

    runTechAnalysis = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${Constant.API_URL}/${userId}/tech-analysis`);
    }
}

export default new TechnicalAnalysisService();