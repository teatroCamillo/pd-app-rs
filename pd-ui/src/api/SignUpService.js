import axios from "axios";
import Constant from "../utils/Constant.js";

class SignUpService {
    signUp = (user) => {
       return axios.post(`${Constant.API_URL}/signup`,
            {
                "username": user.username,
                "password": user.password,
                "mail": user.mail,
                "firstName": user.firstName,
                "lastName": user.lastName
            });
    };
}

export default new SignUpService();