import React from "react";
import Container from "react-bootstrap/Container";
import AuthenticationService from "./utils/AuthenticationService";
import { Navigate } from "react-router-dom";

const StartComponent = () => {

    if('' !== AuthenticationService.getSignedInUsername()){
        return (
            <Container className="text-center">
                Start
            </Container>
        );
    }
    else{
        AuthenticationService.logout();
        return <Navigate to="/signout" />
    }
  };

  export default StartComponent;