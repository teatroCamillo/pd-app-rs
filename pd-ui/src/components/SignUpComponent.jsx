import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useState } from "react";

const SignUpComponent = () => {

  const FIELD_SEPARATOR = ':';
  const [mail, setMail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');

  return (
    <main>
      <Container className="h-100 pt-5">
        <Row className="justify-content-center">
          <h2 className="text-center">Let's Sign Up</h2>
        </Row>
        <Row className="justify-content-md-center mt-4">
          <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="6">
            <Form>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>Email address</Form.Label>
                <Form.Control
                  type="email"
                  required
                  onChange={(e) => setMail(e.target.value)} />
              </Form.Group>

              <Form.Group
                className="mb-3"
                as={Col}
                md="4"
                controlId="validationCustomUsername"
              >
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  required
                  onChange={(e) => setUsername(e.target.value)} />
                <Form.Control.Feedback type="invalid">
                  Please choose a username.
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>New Password</Form.Label>
                <Form.Control
                  type="password"
                  required
                  onChange={(e) => setPassword(e.target.value)} />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPasswordRepeat">
                <Form.Label>Repeat Password</Form.Label>
                <Form.Control
                  type="password"
                  required
                  onChange={(e) => setRepeatPassword(e.target.value)} />
              </Form.Group>

              <div className="text-center">
                <Button variant="success" type="submit">Sign up</Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default SignUpComponent;
