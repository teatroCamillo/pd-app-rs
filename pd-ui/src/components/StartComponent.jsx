import React from "react";
import AuthenticationService from "./utils/AuthenticationService";
import StepsAndResultComponent from "./StepsAndResultComponent";
import { Navigate } from "react-router-dom";
import { useState } from "react";

const StartComponent = () => {

    const [onOff, setOnOff] = useState(false);
    const [pair, setPair] = useState('');

    const processChoice = (event) => {
        setPair(event.target.value);
        setOnOff(true);
    }

    if('' !== AuthenticationService.getSignedInUsername()){
        return (
            <main>
                    {!onOff &&
                        <div className="d-flex flex-column start-container text-center justify-content-center align-items-center">
                            <h3 className="mb-3">Choose your pair</h3>
                            <div className="mb-3 list-group pairGroup">
                                <button type="button" onClick={processChoice} value="EUR/USD" className="list-group-item list-group-item-action">EUR/USD</button>
                                <button type="button" onClick={processChoice} value="GBP/CHF" className="list-group-item list-group-item-action disabled">GBP/CHF</button>
                                <button type="button" onClick={processChoice} value="USD/JPY" className="list-group-item list-group-item-action disabled">USD/JPY</button>
                                <button type="button" onClick={processChoice} value="EUR/GBP" className="list-group-item list-group-item-action disabled">EUR/GBP</button>
                            </div>
                        </div>
                    }
                    {onOff &&
                        <StepsAndResultComponent pair={pair}/>
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