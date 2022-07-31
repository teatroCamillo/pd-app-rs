import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

const Registration = () => {
  return (
    <Container className="h-100">
      <Row className="justify-content-md-center">
        <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="6">
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Email address</Form.Label>
              <Form.Control type="email" required />
            </Form.Group>

            {/*<Form.Group className="mb-3" as={Col} md="4" controlId="validationCustom01">
              <Form.Label>First name</Form.Label>
              <Form.Control
                required
                type="text"
              />
              <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            </Form.Group>

            <Form.Group className="mb-3" as={Col} md="4" controlId="validationCustom02">
              <Form.Label>Last name</Form.Label>
              <Form.Control
                required
                type="text"
              />
              <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            </Form.Group>*/}

            <Form.Group
              className="mb-3"
              as={Col}
              md="4"
              controlId="validationCustomUsername"
            >
              <Form.Label>Username</Form.Label>
              <Form.Control type="text" required />
              <Form.Control.Feedback type="invalid">
                Please choose a username.
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>New Password</Form.Label>
              <Form.Control type="password" required />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Repeat Password</Form.Label>
              <Form.Control type="password" required />
            </Form.Group>

            <Button variant="primary" type="submit">
              Sign up
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default Registration;
