import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import { GraphUpArrow } from "react-bootstrap-icons";

const HeaderComponent = () => {
  return (
    <header>
      <Navbar variant="dark">
        <Container>
          <Navbar.Brand href="/">
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
          <Nav className="me-auto">
            <Nav.Link href="/">Home</Nav.Link>
            <Nav.Link href="/gettingStarted">Getting Started</Nav.Link>
            <Nav.Link href="/aboutApp">About App</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link href="/login">Sign In</Nav.Link>
            <Nav.Link href="/signup">Sign Up</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      {/*<Navbar
            style={{
            borderBottom: "solid 1px",
            paddingBottom: "1rem",
            }}
            >
                <Container className="justify-content-center">
                    {location.pathname === "/" ?
                    <nav>
                        <ul>
                            <CustomLink to="/login" >Login</CustomLink>
                            <CustomLink to="/signup" >Sign up</CustomLink>
                        </ul>
                    </nav>
                        :
                    <nav>
                        <ul>
                            <CustomLink to="/" >Root</CustomLink>
                            <CustomLink to="/home" >Home</CustomLink>
                            <CustomLink to="/risk" >FormRisk</CustomLink>
                        </ul>

                    </nav>}
                </Container>
            </Navbar>*/}
    </header>
  );
};

export default HeaderComponent;
