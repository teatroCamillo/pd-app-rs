import React from "react";
import Container from "react-bootstrap/Container";
import { useParams } from "react-router-dom";
import AuthenticationService from "./utils/AuthenticationService";
import { Navigate } from "react-router-dom";
const StartComponent = () => {

    const {username} = useParams();

    if(username === AuthenticationService.getSignedInUsername()){
        return (
            <Container className="text-center">
                Start {username}
            </Container>
        );
    }
    else{
        AuthenticationService.logout();
        return <Navigate to="/signout" />
    }


  };

  export default StartComponent;