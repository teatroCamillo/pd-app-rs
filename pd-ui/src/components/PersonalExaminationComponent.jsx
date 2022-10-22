import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import React, { useState, useEffect } from "react";
import FormsUtilService from "../api/FormsUtilService";
import { Link } from "react-router-dom";


const PersonalExaminationComponent = () => {

  const [hasCompletedRiskTest, setHasCompletedRiskTest] = useState(false);
  const [hasCompletedGamblingTest, setHasCompletedGamblingTest] = useState(false);
  const [riskQuestions, setRiskQuestions] = useState([])
  const [gamblingQuestions, setGamblingQuestions] = useState([])

  useEffect(() => {
    FormsUtilService.hasUserCompletedRiskTest()
      .then((response) => {
        if(response.status === 200){
          setHasCompletedRiskTest(response.data.hasCompletedRiskTest);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    FormsUtilService.hasUserCompletedGamblingTest()
      .then((response) => {
        if(response.status === 200){
          setHasCompletedGamblingTest(response.data.hasCompletedGamblingTest);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  useEffect(() => {
    FormsUtilService.getAllRiskQuestions()
      .then((response) => {
        if(response.status === 200){
          setRiskQuestions(response.data);
        }
      })
      .catch((error) => {
        console.log(error);
      })
  }, [])

  useEffect(() => {
    FormsUtilService.getAllGamblingQuestions()
      .then((response) => {
        if(response.status === 200){
          setGamblingQuestions(response.data);
        }
      })
      .catch((error) => {
        console.log(error);
      })
  }, [])

  return (
    <main>
      <Container className="h-100">
        <Row className="justify-content-md-center">
          <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="8">
            <div className="row text-center mb-4">
              <h1>Your personal tests</h1>
            </div>
            <div className="row">
              <div className="col-sm-6">
                {hasCompletedRiskTest &&
                <div className="card border-success border border-4">
                  {/*Image <a href="https://pixabay.com/pl/users/tumisu-148124/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=4757854"> Tumisu</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=4757854"> Pixabay</a> */}
                  <img src="./images/personal-ex/risk0.jpg" className="card-img-top" alt="..." />
                  <div className="card-body">
                    <h5 className="card-title">Risk</h5>
                    <Link to="/personal-ex/risk" state={{ hasCompletedRiskTest : hasCompletedRiskTest, riskQuestions : riskQuestions}} ><Button variant="primary">Take Test</Button></Link>
                  </div>
                </div>}
                {!hasCompletedRiskTest &&
                <div className="card border-danger border border-4">
                  {/*Image <a href="https://pixabay.com/pl/users/tumisu-148124/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=4757854"> Tumisu</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=4757854"> Pixabay</a> */}
                  <img src="./images/personal-ex/risk0.jpg" className="card-img-top" alt="..." />
                  <div className="card-body">
                    <h5 className="card-title">Risk</h5>
                    <Link to="/personal-ex/risk" state={{ hasCompletedRiskTest : hasCompletedRiskTest, riskQuestions : riskQuestions}} ><Button variant="primary">Take Test</Button></Link>
                  </div>
                </div>}
              </div>

              <div className="col-sm-6">
                {hasCompletedGamblingTest &&
                <div className="card border-success border border-4">
                  {/*Image <a href="https://pixabay.com/pl/users/stokpic-692575/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=602976"> stokpic</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=602976"> Pixabay</a> */}
                  <img src="./images/personal-ex/gambling0.jpg" className="card-img-top" alt="..." />
                  <div className="card-body">
                    <h5 className="card-title">Gambling</h5>
                    <Link to="/personal-ex/gambling" state={{ hasCompletedGamblingTest : hasCompletedGamblingTest, gamblingQuestions : gamblingQuestions}} ><Button variant="primary">Take Test</Button></Link>
                  </div>
                </div>}
                {!hasCompletedGamblingTest &&
                <div className="card border-danger border border-4">
                  {/*Image <a href="https://pixabay.com/pl/users/stokpic-692575/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=602976"> stokpic</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=602976"> Pixabay</a> */}
                  <img src="./images/personal-ex/gambling0.jpg" className="card-img-top" alt="..." />
                  <div className="card-body">
                    <h5 className="card-title">Gambling</h5>
                    <Link to="/personal-ex/gambling" state={{ hasCompletedGamblingTest : hasCompletedGamblingTest, gamblingQuestions : gamblingQuestions}} ><Button variant="primary">Take Test</Button></Link>
                  </div>
                </div>}
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default PersonalExaminationComponent;
