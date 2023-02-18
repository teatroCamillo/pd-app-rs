import PersonalExaminationService from "../api/PersonalExaminationService";
import TechnicalAnalysisService from "../api/TechnicalAnalysisService";
import MacroAnalysisService from "../api/MacroAnalysisService";
import OutcomeService from "../api/OutcomeService";
import Util from "../components/utils/Util";
import { useState } from "react"

const StepsAndResultComponent = (props) => {

    const pair = props.pair;
    //prsonal data
    const [personalRespStatus, setPrsonalRespStatus] = useState(false);
    const [gambling, setGambling] = useState('');
    const [risk, setRisk] = useState('');

    const getPersonalResults = () => {
        PersonalExaminationService.getPersonalResult()
            .then((resp) => {
                if(resp.status === 200){
                    setPrsonalRespStatus(true)
                    setGambling(resp.data.gamblingResult)
                    setRisk(resp.data.riskResult)
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
    const [macroRespStatus, setMacroRespStatus] = useState(false);
    const [gdpGrowth, setGdpGrowth] = useState('');
    const [eaInf, setEaInf] = useState('');
    const [usInf, setUsInf] = useState('');

    const getMacroResults = () => {
        MacroAnalysisService.runMacroAnalysis()
            .then((resp) => {
                if(resp.status === 200){
                    setMacroRespStatus(true)
                    setGdpGrowth(resp.data.gdpGrowthLatestQ)
                    setEaInf(resp.data.eaInf)
                    setUsInf(resp.data.usInf)
                }
            })
            .catch(error => console.log(error))
    }

    //outcome data
    const [outcomeResp, setOutcomeResp] = useState(false);
    const [score, setScore] = useState('');

    const getOutcome = () => {
        OutcomeService.getOutcome()
            .then(resp => {
                if(resp.status === 200){
                    setOutcomeResp(true)
                    setScore(resp.data.score)
                }
            })
            .catch(error => console.log(error))
    }

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
                            disabled={!personalRespStatus}
                        >
                            Check
                        </button>
                    }
                </div>
                <div className="start-macro bg-light text-dark rounded-4 mx-2">
                    <h6>Macrodata Analysis</h6>
                    {macroRespStatus &&
                        <ul className="text-start">
                            <li>GDP growth: {gdpGrowth}</li>
                            <li>EA inflation: {eaInf}</li>
                            <li>US inflation: {usInf}</li>
                        </ul>
                    }
                    {!macroRespStatus &&
                        <button
                            className="btn btn-primary"
                            type="button"
                            onClick={getMacroResults}
                            disabled={!techRespStatus}
                        >
                            Check
                        </button>
                    }
                </div>
            </div>
            <div className="left-right col-5 bg-light text-dark rounded-4 mx-2">
                <h2>Outcome for {pair}</h2>
                {outcomeResp &&
                    <div>
                        <h4>Regard to actual data and your predispositions.</h4>
                        <h4>The chances of success are around</h4>
                        <h1 style={{color: Util.setColorForOutcomeScore(score)}} >{score}%</h1>
                    </div>
                }
                {!outcomeResp &&
                    <button
                        className="btn btn-primary"
                        type="button"
                        onClick={getOutcome}
                        disabled={!macroRespStatus}
                    >
                        Recap
                    </button>
                }
            </div>
        </div>
    );

}

export default StepsAndResultComponent;