import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import FormsUtilService from "../api/FormsUtilService";
import FormSendError from "./alerts/FormSendError";
import IncompleteForm from "./alerts/IncompleteForm";

const FormRiskComponent = (props) => {
  // questions
  const data = {
    questions: [
      {
        id: "a1",
        q: "1. When I pursue my passions, I like those moments when I'm balancing on the verge of risk.",
        name: "q1-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "a2",
        q: "2. I only take a risk when it is necessary to achieve my goal.",
        name: "q2-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "a3",
        q: "3. Sometimes I tempt fate unnecessarily.",
        name: "q3-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "a4",
        q: "4. When I have to take a risk, I carefully consider the possibility of failure.",
        name: "q4-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "a5",
        q:
          "5. I am attracted to various dangerous activities, e.g. traversing lonely, unknown places, even when I do not know what can happen to me there.",
        name: "q5-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "a6",
        q: "6. Before making a risky decision, I always carefully weigh the pros and cons.",
        name: "q6-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "a7",
        q: "7. Sometimes I take the risk to feel the adrenaline because it makes me feel like I'm really alive.",
        name: "q7-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
    ],
  };

  // colect answers
  const [a1, setA1] = useState('');
  const [a2, setA2] = useState('');
  const [a3, setA3] = useState('');
  const [a4, setA4] = useState('');
  const [a5, setA5] = useState('');
  const [a6, setA6] = useState('');
  const [a7, setA7] = useState('');
  const [showFormSendError, setShowFormSendError] = useState(false);
  const [showIncompleteForm, setShowIncompleteForm] = useState(false);

  const handleChange = (e) => {

    if('a1' === e.target.id){
      setA1(e.target.value);
    }
    if('a2' === e.target.id){
      setA2(e.target.value);
    }
    if('a3' === e.target.id){
      setA3(e.target.value);
    }
    if('a4' === e.target.id){
      setA4(e.target.value);
    }
    if('a5' === e.target.id){
      setA5(e.target.value);
    }
    if('a6' === e.target.id){
      setA6(e.target.value);
    }
    if('a7' === e.target.id){
      setA7(e.target.value);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let isComplete = true;
    let jsonAnswers = {a1, a2, a3, a4, a5, a6, a7}

    for(let e in jsonAnswers){
      if(jsonAnswers[e].length === 0){
        isComplete = false;
      }
    }

    if(isComplete){
      FormsUtilService.sendRiskFormResults(jsonAnswers)
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
            <h2>Risk Test</h2>
            <Form onSubmit={handleSubmit}>
              {data.questions.map((question) => {
                return (
                  <Form.Group
                    as={Row}
                    className="mb-5"
                    key={question.id}
                    controlId={question.id}
                  >
                    <Form.Label as="legend">{question.q}</Form.Label>
                    <Col>
                      <Form.Check
                        type="radio"
                        label={question.a1}
                        name={question.name}
                        value={question.a1}
                        onChange={handleChange}
                      />
                      <Form.Check
                        type="radio"
                        label={question.a2}
                        name={question.name}
                        value={question.a2}
                        onChange={handleChange}
                      />
                      <Form.Check
                        type="radio"
                        label={question.a3}
                        name={question.name}
                        value={question.a3}
                        onChange={handleChange}
                      />
                      <Form.Check
                        type="radio"
                        label={question.a4}
                        name={question.name}
                        value={question.a4}
                        onChange={handleChange}
                      />
                      <Form.Check
                        type="radio"
                        label={question.a5}
                        name={question.name}
                        value={question.a5}
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

export default FormRiskComponent;
