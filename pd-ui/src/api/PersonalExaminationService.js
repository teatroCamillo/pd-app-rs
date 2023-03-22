import axios from "axios";
import AuthenticationService from "../utils/AuthenticationService";
import Constant from "../utils/Constant.js";

class PersonalExaminationService {
    getPersonalResult = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${Constant.API_URL}/${userId}/get-personal-result`);
    };
}

export default new PersonalExaminationService();