import AuthenticationService from "./utils/AuthenticationService";
import { Navigate } from "react-router-dom";

const AuthenticatedRoute = (props) => {

    if(AuthenticationService.isUserSignedIn()){
        console.log(props.children);
       return {...props.children};
    }
    else{
        return <Navigate to="/signin" />
    }
}

export default AuthenticatedRoute;