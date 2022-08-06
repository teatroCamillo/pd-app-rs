import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import WrongCredLogin from "./alerts/WrongCredLogin";
import { Link } from "react-router-dom";

import AuthenticationService from "./utils/AuthenticationService";

const SignInComponent = (props) => {

  const [username, setUsername] = useState("");
  const [pass, setPass] = useState("");
  const [showWrongCredLogin, setShowWrongCredLogin] = useState(false);

  const handleSubmition = (e) => {
    if ((username === "test" && pass === "123") || (username === "batman" && pass === "456")) {
      //Here the e.preventDefault() solves the warning: form submission canceled because the form is not connected.
      //Check it again when connected a server.
      e.preventDefault();

      AuthenticationService.registerSuccessfulLogin(username);
      props.navigate(`/start/${username}`);
    } else {
      e.preventDefault();
      setShowWrongCredLogin(true);
    }
  };

  return (
    <main>
      <div className="position-absolute start-50 translate-middle">
        <WrongCredLogin
              showWrongCredLogin={showWrongCredLogin}
              setShowWrongCredLogin={setShowWrongCredLogin}
            />
      </div>
      <Container className="h-100 pt-5">
        <Row className="justify-content-center">
          <h2 className="text-center">Let's Sign In</h2>
        </Row>
        <Row className="justify-content-center mt-4">
          <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="4">
            <Form onSubmit={handleSubmition}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Password"
                  value={pass}
                  onChange={(e) => setPass(e.target.value)}
                />
              </Form.Group>

              <div className="text-center">
                <Button variant="success" type="submit">Sign In</Button>
              </div>
              <div className="forgotten-password text-center">
                <span>Have you forgotten your password? Click <Link to="/forgotten-password">here</Link>.</span>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default SignInComponent;
