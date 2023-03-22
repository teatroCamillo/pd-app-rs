import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";
import Constant from "../utils/Constant.js";

class OutcomeService {

    getOutcome = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${Constant.API_URL}/${userId}/get-outcome`);
    };
}

export default new OutcomeService();