import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import { Formik, Form, Field } from "formik";
import util from "../utils/Util";
import SignUpService from "../api/SignUpService";
import DifferentPasswords from "../components/alerts/DifferentPasswords";

const SignUpComponent = (props) => {

  const [mail, setMail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [differentPasswords, setDifferentPasswords] = useState(false);

  const handleSubmitionSignUp = () => {
    if(password !== repeatPassword) setDifferentPasswords(true);
    else{
      let user = {username, password, mail, firstName, lastName};
      if(!util.isEmpty(user)){
        SignUpService.signUp(user)
          .then(resp => {
            if(resp.status === 200) props.navigate('/signin');
          })
          .catch(error => console.log(error));
      }
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
            <div className="position-absolute start-50 translate-middle">
              <DifferentPasswords
                    differentPasswords={differentPasswords}
                    setDifferentPasswords={setDifferentPasswords}
                />
            </div>
            <Formik
                initialValues={{username, password, mail, firstName, lastName}}
                onSubmit={handleSubmitionSignUp}
                enableReinitialize={true}
              >
                <Form>
                  <fieldset className="form-group">
                    <label>Email</label>
                    <Field className="form-control" type="email" required name="mail" onChange={(e) => setMail(e.target.value)} />
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Username</label>
                    <Field className="form-control" type="text" required name="username" onChange={(e) => setUsername(e.target.value)} />
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Password</label>
                    <Field className="form-control" type="password" required name="password" onChange={(e) => setPassword(e.target.value)}/>
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Repeat Password</label>
                    <Field className="form-control" type="password" required name="repeatPassword" onChange={(e) => setRepeatPassword(e.target.value)}/>
                  </fieldset>
                  <fieldset className="form-group">
                    <label>First name</label>
                    <Field className="form-control" type="text" required name="firstName" onChange={(e) => setFirstName(e.target.value)} />
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Last name</label>
                    <Field className="form-control" type="text" required name="lastName" onChange={(e) => setLastName(e.target.value)} />
                  </fieldset>

                  <div className="text-center mt-3">
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
