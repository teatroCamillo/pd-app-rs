import axios from 'axios';

export const USER_NAME_SESSION_ATTRIBUTE_KEY = 'authenticatedUser';
export const USER_ID_SESSION_ATTRIBUTE_KEY = 'authenticatedUserId';

class AuthenticationService {

    signIn = (user) =>{
        return axios.post("http://localhost:8080/api/v1/auth/login", {
                "username": user.username,
                "password": user.password
        });
    };

    registerSuccessfulLogin(username, userId){
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_KEY, username);
        sessionStorage.setItem(USER_ID_SESSION_ATTRIBUTE_KEY, userId);
    }

    logout(){
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_KEY);
        sessionStorage.removeItem(USER_ID_SESSION_ATTRIBUTE_KEY);
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

    getSignedInUserId(){
        let id = sessionStorage.getItem(USER_ID_SESSION_ATTRIBUTE_KEY);
        if(id === null) return '';
        return id;
    }
}

export default new AuthenticationService();