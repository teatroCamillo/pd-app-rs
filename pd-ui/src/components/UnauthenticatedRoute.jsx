import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AuthenticationService from "./utils/AuthenticationService";

/**
 * @description
 *  This component performs logout() method if client tries to go to address url which is dedicated for
 *  logged out. Note, it performs also for manual changing the url address. So we are safe.
 *
 * @param {*} props
 * @returns
 */
const UnauthenticatedRoute = (props) => {

    const targetUrl = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        if(AuthenticationService.isUserSignedIn()){
            AuthenticationService.logout();
            navigate(targetUrl);
        }
    })

    return {...props.children};
}

export default UnauthenticatedRoute;