import PersonalExaminationService from "../api/PersonalExaminationService";
import TechnicalAnalysisService from "../api/TechnicalAnalysisService";
import MacroAnalysisService from "../api/MacroAnalysisService";
import OutcomeService from "../api/OutcomeService";
import Util from "../components/utils/Util";
import { useState } from "react";
import Accordion from 'react-bootstrap/Accordion';

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
    const [strategyMet, setStrategyMet] = useState('false');

    const getTechResults = () => {
        TechnicalAnalysisService.runTechAnalysis()
            .then((resp) => {
                if(resp.status === 200){
                    console.log(resp)
                    setTechRespStatus(true)
                    setRsi14(resp.data.rsi14)
                    setMacd(resp.data.macd)
                    setStrategyMet(resp.data.strategyMet)
                }
            })
            .catch(error => console.log(error))
    }

    //macro data
    const [macroRespStatus, setMacroRespStatus] = useState(false);
    const [eaGdpGrowth, setEaGdpGrowth] = useState('');
    const [usGdpGrowth, setUsGdpGrowth] = useState('');
    const [eaInf, setEaInf] = useState('');
    const [usInf, setUsInf] = useState('');

    const getMacroResults = () => {
        MacroAnalysisService.runMacroAnalysis()
            .then((resp) => {
                if(resp.status === 200){
                    console.log(resp)
                    setMacroRespStatus(true)
                    setEaGdpGrowth(resp.data.eaGdpGrowthLatestQ)
                    setUsGdpGrowth(resp.data.usGdpGrowthLatestQ)
                    setEaInf(resp.data.eaInf)
                    setUsInf(resp.data.usInf)
                }
            })
            .catch(error => console.log(error))
    }

    //outcome data
    const [outcomeResp, setOutcomeResp] = useState(false);
    const [score, setScore] = useState('');
    const [desc, setDesc] = useState('');
    const [currentPrice, setCurrentPrice] = useState('');
    const [takeProfit, setTakeProfit] = useState('');
    const [stopLoss, setStopLoss] = useState('');
    const [maxAmountToInvest, setMaxAmountToInvest] = useState('');

    const getOutcome = () => {
        OutcomeService.getOutcome()
            .then(resp => {
                if(resp.status === 200){
                    setOutcomeResp(true);
                    setScore(resp.data.score);
                    setDesc(resp.data.description);
                    setCurrentPrice(resp.data.currentPrice);
                    setTakeProfit(resp.data.takeProfit);
                    setStopLoss(resp.data.stopLoss);
                    setMaxAmountToInvest(resp.data.maxAmountToInvest);
                }
            })
            .catch(error => console.log(error))
    }

    return (
        <div className="d-flex start-container text-center justify-content-center align-items-center">
            <div className="left-right col-4 mx-2">
                <Accordion alwaysOpen>
                    <Accordion.Item eventKey="0" className="bg-light text-dark mx-2 mb-2">
                        <Accordion.Header><h6>Personal Examination</h6></Accordion.Header>
                            <Accordion.Body>
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
                            </Accordion.Body>
                    </Accordion.Item>
                    <Accordion.Item eventKey="1" className="bg-light text-dark mx-2 mb-2">
                        <Accordion.Header><h6>Technical Analysis</h6></Accordion.Header>
                        <Accordion.Body>
                            {techRespStatus &&
                                <ul className="text-start">
                                    <li>RSI 14: {rsi14}</li>
                                    <li>MACD: {macd}</li>
                                    <li>Strategy met: {strategyMet}</li>
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
                        </Accordion.Body>
                    </Accordion.Item>
                    <Accordion.Item eventKey="2" className="bg-light text-dark mx-2 mb-2">
                        <Accordion.Header><h6>Macrodata Analysis</h6></Accordion.Header>
                        <Accordion.Body>
                            {macroRespStatus &&
                                <ul className="text-start">
                                    <li>EA GDP growth: {eaGdpGrowth}</li>
                                    <li>US GDP growth: {usGdpGrowth}</li>
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
                        </Accordion.Body>
                    </Accordion.Item>
                </Accordion>
            </div>
            <div className="d-flex flex-column left-right col-5 bg-light text-dark rounded-4 mx-2">
                <div className="d-flex justify-content-center mt-2">
                    <h2>Outcome for {pair}</h2>
                </div>
                {outcomeResp &&
                    <div className="mt-3 mx-3">
                        <h5>Regard to actual data and your predispositions,</h5>
                        <h5>the chances of success for long transaction are around</h5>
                        <h1 style={{color: Util.setColorForOutcomeScore(score)}} >{score}%</h1>
                        <div className="d-flex flex-column justify-content-end align-items-start">
                            <h5>Recommendation</h5>
                            <h6>Description: </h6>
                            <p>{desc}</p>
                            <h6>Current price: {currentPrice}</h6>
                            {score >= 50 &&
                                <div className="d-flex flex-column align-items-start">
                                    <h6>Take Profit: {takeProfit}</h6>
                                    <h6>Stop Loss: {stopLoss}</h6>
                                    <h6>Max amount to invest: {maxAmountToInvest}</h6>
                                </div>
                            }
                        </div>
                    </div>
                }
                {!outcomeResp &&
                    <div className="d-flex flex-column justify-content-center align-items-center flex-grow-1">
                        <h6>First of all, gather data and then recap</h6>
                        <span className="material-symbols-outlined">query_stats</span>
                        <button
                            className="btn btn-primary mt-3"
                            type="button"
                            onClick={getOutcome}
                            disabled={!macroRespStatus}
                        >
                            Recap
                        </button>
                    </div>
                }
            </div>
        </div>
    );

}

export default StepsAndResultComponent;