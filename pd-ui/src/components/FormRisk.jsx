import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Footer from './Footer';
import Header from './Header';

const FormRisk = () => {

  // questions
  const data = {
    questions: [
      {
        id: "q1",
        q: "1. Gdy realizuję swoje pasje lubię te chwile, gdy balansuję na granicy ryzyka.",
        name: "q1-a",
        a1: "Prawda",
        a2: "Raczej prawda",
        a3: "Trudno powiedzieć",
        a4: "Raczej nieprawda",
        a5: "Nieprawda",
      },
      {
        id: "q2",
        q: "2. Podejmuję ryzyko tylko wtedy, gdy jest to konieczne dla osiągnięcia celu.",
        name: "q2-a",
        a1: "Prawda",
        a2: "Raczej prawda",
        a3: "Trudno powiedzieć",
        a4: "Raczej nieprawda",
        a5: "Nieprawda",
      },
      {
        id: "q3",
        q: "3. Czasami niepotrzebnie kuszę los.",
        name: "q3-a",
        a1: "Prawda",
        a2: "Raczej prawda",
        a3: "Trudno powiedzieć",
        a4: "Raczej nieprawda",
        a5: "Nieprawda",
      },
      {
        id: "q4",
        q: "4. Gdy muszę zaryzykować dokładnie rozważam możliwość porażki.",
        name: "q4-a",
        a1: "Prawda",
        a2: "Raczej prawda",
        a3: "Trudno powiedzieć",
        a4: "Raczej nieprawda",
        a5: "Nieprawda",
      },
      {
        id: "q5",
        q: "5. Pociągają mnie różne niebezpieczne "+
        "działania np. przemierzanie odludnych, "+
        "nieznanych miejsc, nawet, gdy "+
        "nie wiem, co mnie tam może spotkać.",
        name: "q5-a",
        a1: "Prawda",
        a2: "Raczej prawda",
        a3: "Trudno powiedzieć",
        a4: "Raczej nieprawda",
        a5: "Nieprawda",
      },
      {
        id: "q6",
        q: "6. Przed podjęciem decyzji ryzykownej zawsze dokładnie rozważam wszystkie za i przeciw.",
        name: "q6-a",
        a1: "Prawda",
        a2: "Raczej prawda",
        a3: "Trudno powiedzieć",
        a4: "Raczej nieprawda",
        a5: "Nieprawda",
      },
      {
        id: "q7",
        q: "7. Czasami ryzykuję po to, aby poczuć „adrenalinę”, bo to ona sprawia, że wtedy czuję, że naprawdę żyję.",
        name: "q7-a",
        a1: "Prawda",
        a2: "Raczej prawda",
        a3: "Trudno powiedzieć",
        a4: "Raczej nieprawda",
        a5: "Nieprawda",
      }
    ]
  };

  // colect answers
  const [answers, setAnswers] = useState([
    {q: 'q1', value: ''},
    {q: 'q2', value: ''},
    {q: 'q3', value: ''},
    {q: 'q4', value: ''},
    {q: 'q5', value: ''},
    {q: 'q6', value: ''},
    {q: 'q7', value: ''},
  ]);

  // store last clicked radio button and then pass to answers
  const [selected, setSelected] = useState({});
  const handleChange = (e) => {
    setSelected(e.target);
  };

  // for update answers
  useEffect(() => {
    const newAns = answers.map(p =>
        p.q === selected.id
          ? { ...p, value: selected.value } : p
    );
    setAnswers(newAns);
  }, [selected]);

  // support method - to observe what is storing to answers
  const printCollect = () => {
    let ul = document.querySelector(".res");
    ul.innerHTML = "";
    answers
      .forEach((a) => {
        ul.innerHTML += `<li>${a.q} : ${a.value}</li>`;
      })
  };

  // for print updated collection of answers
  useEffect(() => {
    printCollect();
  }, [answers])

  // nav to TestResult page
  //let navigateTest = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    //() => navigateTest('/test')
    console.log(answers);
  }

  return (
    <Container className='h-100'>
      <Header/>
      <Row className="justify-content-md-center">
        <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="6">
          <Form onSubmit={handleSubmit}>
            {data.questions.map( question => {
              return (
                <Form.Group as={Row} className="mb-5" key={question.id} controlId={question.id} >
                  <Form.Label as="legend">
                    {question.q}
                  </Form.Label>
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
              )
            })}

            <div className="justify-content-center">
              <Button className='align-middle' variant="primary" type="submit">
                Zatwierdź
              </Button>
              <p>{selected.id} : {selected.value}</p>
              <ul className="res"></ul>
            </div>
          </Form>
        </Col>
      </Row>
      <Footer/>
    </Container>
  )
};

export default FormRisk;