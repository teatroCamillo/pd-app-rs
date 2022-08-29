import axios from "axios";


class SignUpService {

    signUp = (user) => {
       return axios.post("http://localhost:8080/signup",
            {
                "username": user.username,
                "password": user.password
            });
    };

}

export default new SignUpService();