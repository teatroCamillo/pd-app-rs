import { useState, useEffect } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const FormRiskComponent = () => {
  // questions
  const data = {
    questions: [
      {
        id: "q1",
        q: "1. When I pursue my passions, I like those moments when I'm balancing on the verge of risk.",
        name: "q1-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "q2",
        q: "2. I only take a risk when it is necessary to achieve my goal.",
        name: "q2-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "q3",
        q: "3. Sometimes I tempt fate unnecessarily.",
        name: "q3-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "q4",
        q: "4. When I have to take a risk, I carefully consider the possibility of failure.",
        name: "q4-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "q5",
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
        id: "q6",
        q: "6. Before making a risky decision, I always carefully weigh the pros and cons.",
        name: "q6-a",
        a1: "Truth",
        a2: "Rather true",
        a3: "Hard to say",
        a4: "Rather not true",
        a5: "Not true",
      },
      {
        id: "q7",
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
  const [answers, setAnswers] = useState([
    { q: "q1", value: "" },
    { q: "q2", value: "" },
    { q: "q3", value: "" },
    { q: "q4", value: "" },
    { q: "q5", value: "" },
    { q: "q6", value: "" },
    { q: "q7", value: "" },
  ]);

  // store last clicked radio button and then pass to answers
  const [selected, setSelected] = useState({});
  const handleChange = (e) => {
    setSelected(e.target);
  };

  // for update answers
  useEffect(() => {
    const newAns = answers.map((p) =>
      p.q === selected.id ? { ...p, value: selected.value } : p
    );
    setAnswers(newAns);
  }, [selected, answers]);

  // support method - to observe what is storing to answers
  const printCollect = () => {
    let ul = document.querySelector(".res");
    ul.innerHTML = "";
    answers.forEach((a) => {
      ul.innerHTML += `<li>${a.q} : ${a.value}</li>`;
    });
  };

  // for print updated collection of answers
  useEffect(() => {
    printCollect();
  }, [answers]);

  // nav to TestResult page
  //let navigateTest = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    //() => navigateTest('/test')
    console.log(answers);
  };

  return (
    <main>
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
                <p>
                  {selected.id} : {selected.value}
                </p>
                <ul className="res"></ul>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default FormRiskComponent;
