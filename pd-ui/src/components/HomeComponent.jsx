import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";

const Root = () => {
  return (
    <main>
      <Container className="my-5 text-center">
        <Row>
          <h1>Boost your revenue trading on the forex</h1>
          <h1>with Relax & Earn</h1>
          <h3 className="mt-5">Simple to choose, simple to use.</h3>
        </Row>
        <Row className="mt-5">
          <Col className="bg-light text-dark rounded-4 mx-2">
            <h4 className="mt-3">Traditional way</h4>
            <Row className="align-items-center mt-5">
              <div className="w-50 h-50 d-inline">
                <img
                  className="w-100 h-100 border border-4 border-dark rounded-4"
                  src="./images/front-page/fail-analize.jpg"
                  alt="fail-analize.jpg"
                />
              </div>
              <div className="w-50 h-50 d-inline">
                <span className="front-page-steps-desc">Tough analysis</span>
              </div>
            </Row>
            <Row className="align-items-center mt-3">
              <div className="w-50 h-50 d-inline">
                <span className="front-page-steps-desc">
                  Fight with emotions
                </span>
              </div>
              <div className="w-50 h-50 d-inline">
                <img
                  className="w-100 h-100 border border-4 border-dark rounded-4"
                  src="./images/front-page/fail-emotions.jpg"
                  alt="fail-emotions.jpg"
                />
              </div>
            </Row>
            <Row className="align-items-center mt-3">
              <div className="w-50 h-50 d-inline">
                <img
                  className="w-100 h-100 border border-4 border-dark rounded-4"
                  src="./images/front-page/fail-lost.jpg"
                  alt="fail-lost.jpg"
                />
              </div>
              <div className="w-50 h-50 d-inline">
                <span className="front-page-steps-desc">Losing money</span>
              </div>
            </Row>
            <Row className="justify-content-center my-4">
              <div>
                <span className="front-page-steps-desc">Leave this page</span>
              </div>
              <div className="w-50 mt-2">
                <Button variant="danger" href="https://www.google.com/">Failure</Button>
              </div>
            </Row>
          </Col>
          <Col className="bg-light text-dark rounded-4 mx-2">
            <h4 className="mt-3">With R&E</h4>
            <Row className="justify-content-evenly align-items-center mt-5">
              <div className="w-50 h-50 d-inline">
                <img
                  className="w-100 h-100 border border-4 border-dark rounded-4"
                  src="./images/front-page/win-signup.jpg"
                  alt="win-signup.jpg"
                />
              </div>
              <div className="w-50 h-50 d-inline">
                <span className="front-page-steps-desc">Sign up</span>
              </div>
            </Row>
            <Row className="justify-content-evenly align-items-center mt-3">
              <div className="w-50 h-50 d-inline">
                <span className="front-page-steps-desc">Use</span>
              </div>
              <div className="w-50 h-50 d-inline">
                <img
                  className="w-100 h-100 border border-4 border-dark rounded-4"
                  src="./images/front-page/win-use.jpg"
                  alt="win-use.jpg"
                />
              </div>
            </Row>
            <Row className="justify-content-evenly align-items-center mt-3">
              <div className="w-50 h-50 d-inline">
                <img
                  className="w-100 h-100 border border-4 border-dark rounded-4"
                  src="./images/front-page/win-earn.jpg"
                  alt="win-earn.jpg"
                />
              </div>
              <div className="w-50 h-50 d-inline">
                <span className="front-page-steps-desc">Relax & Earn</span>
              </div>
            </Row>
            <Row className="justify-content-center  my-4">
              <div>
                <span className="front-page-steps-desc">Sign Up right now</span>
              </div>
              <div className="w-50  mt-2">
                <Link to="/signup">
                  <Button variant="success">Success</Button>
                </Link>
              </div>
            </Row>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default Root;
