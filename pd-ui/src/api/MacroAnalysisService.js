import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";

const API_URL = 'http://localhost:8080';

class MacroAnalysisService {

    runMacroAnalysis = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/macro-analysis`);
    }

}

export default new MacroAnalysisService();