
export const USER_NAME_SESSION_ATTRIBUTE_KEY = 'authenticatedUser';

class AuthenticationService {

    registerSuccessfulLogin(username){
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_KEY, username);
    }

    logout(){
        console.log('logout');
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