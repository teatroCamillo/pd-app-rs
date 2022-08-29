import axios from "axios";


class SignUpService {

    signUp = (user) => {
       return axios.post("http://localhost:8080/signup",
            {
                "username": user.username,
                "password": user.password,
                "mail": user.mail,
                "firstName": user.firstName,
                "lastName": user.lastName,
                "age": user.age
            });
    };

}

export default new SignUpService();