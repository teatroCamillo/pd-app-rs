import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import { Formik, Form, Field } from "formik";
import util from "./utils/Util";
import SignUpService from "../api/SignUpService";

const SignUpComponent = (props) => {

  const FIELD_SEPARATOR = ':';
  const [mail, setMail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');

  const handleSubmitionSignUp = () => {

    let user = {};
    if(password === repeatPassword) user = {username, password};

    if(!util.isEmpty(user)){
    SignUpService.signUp(user)
      .then((resp) => {
        if(resp.status === 200){
          props.navigate('/signin');
        }
      })
      .catch((error) => {
        console.log(error);
      });
    }
  }

  return (
    <main>
      <Container className="h-100 pt-5">
        <Row className="justify-content-center">
          <h2 className="text-center">Let's Sign Up</h2>
        </Row>
        <Row className="justify-content-md-center mt-4">
          <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="6">
            <Formik
                initialValues={{username, password}}
                onSubmit={handleSubmitionSignUp}
                enableReinitialize={true}
              >
                <Form>
                  <fieldset className="form-group">
                    <label>Username</label>
                    <Field className="form-control" type="text" name="username" onChange={(e) => setUsername(e.target.value)} />
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Password</label>
                    <Field className="form-control" type="password" name="password" onChange={(e) => setPassword(e.target.value)}/>
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Repeat Password</label>
                    <Field className="form-control" type="password" name="repeatPassword" onChange={(e) => setRepeatPassword(e.target.value)}/>
                  </fieldset>

                  <div className="text-center">
                    <Button variant="success" type="submit">Sign In</Button>
                  </div>
                </Form>
              </Formik>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default SignUpComponent;
