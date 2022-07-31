import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useNavigate } from "react-router-dom";
import WrongCredLogin from "./alerts/WrongCredLogin";

const Login = () => {
  const [username, setUsername] = useState("");
  const [pass, setPass] = useState("");

  const navigate = useNavigate();

  const handleSubmition = (e) => {
    const data = [username, pass];

    if (username === "test" && pass === "test") {
      navigate("/home");
    } else {
      e.preventDefault();
      setShowWrongCredLogin(true);
    }
    console.log(data);
  };

  const [showWrongCredLogin, setShowWrongCredLogin] = useState(false);

  return (
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
  );
};

export default Login;
