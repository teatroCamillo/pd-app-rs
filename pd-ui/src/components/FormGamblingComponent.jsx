import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import FormSendError from "./alerts/FormSendError";
import IncompleteForm from "./alerts/IncompleteForm";
import FormsUtilService from "../api/FormsUtilService";
import { useLocation } from "react-router-dom";
import Util from "../utils/Util";
import Constant from "../utils/Constant.js";

const FormGamblingComponent = (props) => {

  const location = useLocation()
  const { hasCompletedGamblingTest, gamblingQuestions } = location.state;

  const data = {
    questions: gamblingQuestions
  };

  const [formDetails, setFormDetails] = useState({})
  const [showFormSendError, setShowFormSendError] = useState(false);
  const [showIncompleteForm, setShowIncompleteForm] = useState(false);

  const handleChange = (e) => {
    const {name, value} = e.target;
    setFormDetails((prev) => {
      return {...prev, [name] : value };
    })
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let numberOfQuestions = Object.keys(gamblingQuestions).length;
    if(Util.isFormComlete(formDetails, numberOfQuestions)){
      let answers = Constant.ANSWERS_TEXT;
      let formDetailsValueArray = { answers : Object.values(formDetails)};
      FormsUtilService.sendGamblingFormResults(formDetailsValueArray, hasCompletedGamblingTest)
        .then((response) => {
          if(response.status === 200){
            props.navigate('/form-saved');
          }
        })
        .catch((error) => {
          setShowFormSendError(true);
          console.log(error);
          window.scrollTo(0, 0);
        });
    }
    else{
      setShowIncompleteForm(true);
      window.scrollTo(0, 0);
    }
  };

  return (
    <main>
      <div className="position-absolute start-50 translate-middle">
        <FormSendError
            showFormSendError={showFormSendError}
            setShowFormSendError={setShowFormSendError}
        />
        <IncompleteForm
            showIncompleteForm={showIncompleteForm}
            setShowIncompleteForm={setShowIncompleteForm}
        />
      </div>
      <Container className="h-100">
        <Row className="justify-content-md-center">
          <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="6">
            <h2>Gambling Test</h2>
            <Form onSubmit={handleSubmit}>
              {data.questions.map((question) => {
                return (
                  <Form.Group
                    as={Row}
                    className="mb-5"
                    key={question.id}
                    controlId={question.id}
                  >
                    <Form.Label as="legend">{question.question}</Form.Label>
                    <Col>
                      <Form.Check
                        type="radio"
                        label={question.a1}
                        name={question.id}
                        value={question.a1}
                        onChange={handleChange}
                      />
                      <Form.Check
                        type="radio"
                        label={question.a2}
                        name={question.id}
                        value={question.a2}
                        onChange={handleChange}
                      />
                    </Col>
                  </Form.Group>
                );
              })}
              <div className="justify-content-center">
                <Button
                  className="align-middle"
                  variant="primary"
                  type="submit"
                >
                  Confirm
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default FormGamblingComponent;
