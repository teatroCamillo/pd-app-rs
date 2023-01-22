import PersonalExaminationService from "../api/PersonalExaminationService";
import TechnicalAnalysisService from "../api/TechnicalAnalysisService";
import { useState } from "react"

const StepsAndResultComponent = () => {

    //prsonal data
    const [personalRespStatus, setPrsonalRespStatus] = useState(false);
    const [gambling, setGambling] = useState('');
    const [risk, setRisk] = useState('');

    const getPersonalResults = () => {
        PersonalExaminationService.getPersonalResult()
            .then((resp) => {
                if(resp.status === 200){
                    setPrsonalRespStatus(true)
                    setGambling(resp.data.gambling)
                    setRisk(resp.data.risk)
                }
            })
            .catch(error => console.log(error))
    }

    //tech data
    const [techRespStatus, setTechRespStatus] = useState(false);
    const [rsi14, setRsi14] = useState('');
    const [macd, setMacd] = useState('');
    const [closePrice, setClosePrice] = useState('');

    const getTechResults = () => {
        TechnicalAnalysisService.runTechAnalysis()
            .then((resp) => {
                if(resp.status === 200){
                    setTechRespStatus(true)
                    setRsi14(resp.data.actualRSI14)
                    setMacd(resp.data.macd)
                    setClosePrice(resp.data.closePrice)
                }
            })
            .catch(error => console.log(error))
    }

    //macro data



    return (
        <div className="d-flex start-container text-center justify-content-center align-items-center">
            <div className="left-right col-4 mx-2">
                <div className="start-personal bg-light text-dark rounded-4 mx-2 mb-2">
                    <h6>Personal Examination</h6>
                    {personalRespStatus &&
                        <ul className="text-start">
                            <li>Risk: {risk}</li>
                            <li>Gambling: {gambling}</li>
                        </ul>
                    }
                    {!personalRespStatus &&
                        <button
                            className="btn btn-primary"
                            type="button"
                            onClick={getPersonalResults}
                        >
                            Check
                        </button>
                    }
                </div>
                <div className="start-tech bg-light text-dark rounded-4 mx-2 mb-2">
                    <h6>Technical Analysis</h6>
                    {techRespStatus &&
                        <ul className="text-start">
                            <li>RSI 14: {rsi14}</li>
                            <li>MACD: {macd}</li>
                            <li>Close price: {closePrice}</li>
                        </ul>
                    }
                    {!techRespStatus &&
                        <button
                            className="btn btn-primary"
                            type="button"
                            onClick={getTechResults}
                        >
                            Check
                        </button>
                    }
                </div>
                <div className="start-macro bg-light text-dark rounded-4 mx-2">
                    <h6>Macrodata Analysis</h6>
                </div>
            </div>
            <div className="left-right col-5 bg-light text-dark rounded-4 mx-2">
                <h4>Outcome</h4>
            </div>
        </div>
    );

}

export default StepsAndResultComponent;