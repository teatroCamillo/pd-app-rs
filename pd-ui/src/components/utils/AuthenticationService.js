import axios from 'axios';

export const USER_NAME_SESSION_ATTRIBUTE_KEY = 'authenticatedUser';

class AuthenticationService {

    signIn = (user) =>{
        return axios.post("http://localhost:8080/api/v1/auth/login", {
                "username": user.username,
                "password": user.password
        });
    };

    registerSuccessfulLogin(username){
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_KEY, username);
    }

    logout(){
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_KEY);
    }

    isUserSignedIn(){
        if(sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_KEY) === null) return false;
        return true;
    }

    getSignedInUsername(){
        let username = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_KEY);
        if(username === null) return '';
        return username;
    }
}

export default new AuthenticationService();