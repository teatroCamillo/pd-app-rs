import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import WrongCredLogin from "./alerts/WrongCredLogin";
import { Link } from "react-router-dom";

import AuthenticationService from "./utils/AuthenticationService";

import { Formik, Form, Field } from "formik";

const SignInComponent = (props) => {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showWrongCredLogin, setShowWrongCredLogin] = useState(false);

  const handleSubmition = () => {

    const user = {username, password};

    AuthenticationService.signIn(user)
    .then(resp =>{
      if(resp.status === 200) {
        //Here the e.preventDefault() solves the warning: form submission canceled because the form is not connected.
        //Check it again when connected a server.
        //e.preventDefault();

        AuthenticationService.registerSuccessfulLogin(username);
        props.navigate(`/start/${username}`);
      }
    })
    .catch((error) => {
      setShowWrongCredLogin(true);
      console.log(error);
    });
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
            <Formik
              initialValues={{username, password}}
              onSubmit={handleSubmition}
              enableReinitialize={true}
            >
              <Form>
                <fieldset className="form-group">
                  <label>Username</label>
                  <Field className="form-control" type="text" required name="username" onChange={(e) => setUsername(e.target.value)} />
                </fieldset>
                <fieldset className="form-group">
                  <label>Password</label>
                  <Field className="form-control" type="password" required name="pass" onChange={(e) => setPassword(e.target.value)}/>
                </fieldset>

                <div className="text-center mt-3">
                  <Button variant="success" type="submit">Sign In</Button>
                </div>
                <div className="forgotten-password text-center">
                  <span>Have you forgotten your password? Click <Link to="/forgotten-password">here</Link>.</span>
                </div>
              </Form>
            </Formik>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default SignInComponent;
