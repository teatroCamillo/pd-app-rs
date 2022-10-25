import React from "react";
import Container from "react-bootstrap/Container";
import AuthenticationService from "./utils/AuthenticationService";
import { Navigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import TechnicalAnalysisService from "../api/TechnicalAnalysisService";

const StartComponent = () => {

    const handleGetDataButton = () => {
        TechnicalAnalysisService.getExchangeRateData()
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        })
    }

    if('' !== AuthenticationService.getSignedInUsername()){
        return (
            <main>
                <Container className="h-100 text-center">
                    <h2>Start</h2>
                    <Button
                        variant="primary"
                        type="button"
                        onClick={handleGetDataButton}
                    >
                        Get Data from Service
                    </Button>


                </Container>
            </main>
        );
    }
    else{
        AuthenticationService.logout();
        return <Navigate to="/signout" />
    }
  };

  export default StartComponent;