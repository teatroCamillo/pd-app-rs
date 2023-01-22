import React from "react";
import AuthenticationService from "./utils/AuthenticationService";
import StepsAndResultComponent from "./StepsAndResultComponent";
import { Navigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import { useState } from "react";

const StartComponent = () => {

    const [onOff, setOnOff] = useState(false);

    if('' !== AuthenticationService.getSignedInUsername()){
        return (
            <main>
                    {!onOff &&
                        <div className="d-flex start-container text-center justify-content-center align-items-center">
                            <Button
                                className="btnStart fw-bold fs-3 rounded-circle"
                                variant="primary"
                                type="button"
                                onClick={setOnOff}
                            >
                                START
                            </Button>
                        </div>
                    }
                    {onOff &&
                        <StepsAndResultComponent/>
                    }
            </main>
        );
    }
    else{
        AuthenticationService.logout();
        return <Navigate to="/signout" />
    }
  };

  export default StartComponent;