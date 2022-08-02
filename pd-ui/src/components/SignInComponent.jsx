import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useNavigate } from "react-router-dom";
import WrongCredLogin from "./alerts/WrongCredLogin";

import AuthenticationService from "./utils/AuthenticationService";

const SignInComponent = () => {
  const [username, setUsername] = useState("");
  const [pass, setPass] = useState("");

  const navigate = useNavigate();

  const handleSubmition = (e) => {

    if ((username === "test" && pass === "123") || (username === "batman" && pass === "456")) {
      //Here the e.preventDefault() solves the warning: form submission canceled because the form is not connected.
      //Check it again when connected a server.
      e.preventDefault();

      AuthenticationService.registerSuccessfulLogin(username);
      navigate(`/start/${username}`);
    } else {
      e.preventDefault();
      setShowWrongCredLogin(true);
    }
  };

  const [showWrongCredLogin, setShowWrongCredLogin] = useState(false);

  return (
    <main>
      <Container className="h-100">
        <Row className="justify-content-center">
          <WrongCredLogin
            showWrongCredLogin={showWrongCredLogin}
            setShowWrongCredLogin={setShowWrongCredLogin}
          />
        </Row>
        <Row className="justify-content-center">
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

              <Button variant="primary" type="submit">
                Sign in
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default SignInComponent;
