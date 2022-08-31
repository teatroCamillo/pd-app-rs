import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import { GraphUpArrow } from "react-bootstrap-icons";
import { LinkContainer } from "react-router-bootstrap";

import AuthenticationService from "./utils/AuthenticationService";

const HeaderComponent = () => {

  let isUserSignedIn = AuthenticationService.isUserSignedIn();

  return (
    <header>
      <Navbar variant="dark">
        <Container>
          {!isUserSignedIn && <LinkContainer to="/">
            <Navbar.Brand>
              <Container>
                <Row>
                  <Col>
                    <GraphUpArrow size={52} />{" "}
                  </Col>
                  <Col>
                    <h1>R&E</h1>
                  </Col>
                </Row>
              </Container>
            </Navbar.Brand>
          </LinkContainer>}
          {isUserSignedIn && <LinkContainer to={`/start`}>
            <Navbar.Brand>
              <Container>
                <Row>
                  <Col>
                    <GraphUpArrow size={52} />{" "}
                  </Col>
                  <Col>
                    <h1>R&E</h1>
                  </Col>
                </Row>
              </Container>
            </Navbar.Brand>
          </LinkContainer>}
          <Nav className="me-auto">
            {!isUserSignedIn &&
              <LinkContainer to="/">
                <Nav.Link>Home</Nav.Link>
              </LinkContainer>}
            {isUserSignedIn &&
              <LinkContainer to={`/start`}>
                <Nav.Link>Start</Nav.Link>
              </LinkContainer>}
            <LinkContainer to="/getting-started">
              <Nav.Link>Getting Started</Nav.Link>
            </LinkContainer>
            <LinkContainer to="/about-app">
              <Nav.Link>About App</Nav.Link>
            </LinkContainer>
            {isUserSignedIn &&
              <LinkContainer to="/risk">
                <Nav.Link>Risk</Nav.Link>
              </LinkContainer>}
          </Nav>
          <Nav>
            {!isUserSignedIn &&
              <LinkContainer to="/signin">
                <Nav.Link>Sign In</Nav.Link>
              </LinkContainer>}
            {isUserSignedIn &&
              <LinkContainer to="/signout" onClick={AuthenticationService.logout}>
                <Nav.Link>Sign Out</Nav.Link>
              </LinkContainer>}
            {!isUserSignedIn &&
              <LinkContainer to="/signup">
                <Nav.Link>Sign Up</Nav.Link>
              </LinkContainer>}
          </Nav>
        </Container>
      </Navbar>
    </header>
  );
};

export default HeaderComponent;
