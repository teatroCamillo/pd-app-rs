import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";

const API_URL = 'http://localhost:8080';

class OutcomeService {

    getOutcome = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/get-outcome`);
    };

}

export default new OutcomeService();