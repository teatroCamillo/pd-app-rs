import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";
import Constant from "../utils/Constant.js";

class MacroAnalysisService {

    runMacroAnalysis = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${Constant.API_URL}/${userId}/macro-analysis`);
    }

}

export default new MacroAnalysisService();