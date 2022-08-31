import { useState, useEffect } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const FormGamblingComponent = () => {


  return (
    <main>
      <Container className="h-100">
        <Row className="justify-content-md-center">
          <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="6">
            <h2>Gambling Test</h2>
            
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default FormGamblingComponent;
