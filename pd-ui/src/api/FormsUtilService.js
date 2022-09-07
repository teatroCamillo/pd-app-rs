import axios from "axios";
import AuthenticationService from "../components/utils/AuthenticationService";

class FormsUtilService {

    sendRiskFormResults = (answers) => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.post(`http://localhost:8080/${userId}/risk`, answers);
    };

}

export default new FormsUtilService();

