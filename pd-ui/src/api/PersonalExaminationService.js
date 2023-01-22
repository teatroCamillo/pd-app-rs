import axios from "axios";
import AuthenticationService from "../components/utils/AuthenticationService";

const API_URL = 'http://localhost:8080';

class PersonalExaminationService {

    getPersonalResult = () => {
        const userId = AuthenticationService.getSignedInUserId();
        return axios.get(`${API_URL}/${userId}/get-personal-result`);
    };

}

export default new PersonalExaminationService();