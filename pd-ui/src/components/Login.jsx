import { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Footer from './Footer';
import Header from './Header';
import { useNavigate } from 'react-router-dom';
import WrongCredLogin from './alerts/WrongCredLogin';

const Login = () => {

  const [mail, setMail] = useState('');
  const [pass, setPass] = useState('');

  const navigate = useNavigate();

  const handleSub = (e) => {

    const data = [mail, pass];

    {/*fetch('http://destination/', {
      method: 'POST',
      headers: {'Content-Type' : 'application/json'},
      body: JSON.stringify(data)
    }).then(() => console.log("data sent"))*/}

    if(mail === 'test@gmail.com' && pass === 'test'){
      navigate("/home");
    }
    else {
      e.preventDefault();
      setShow(true);
      //console.log("Wrong credentials")
    }
    console.log(data);
  }

  const [show, setShow] = useState(false);

  return (
    <Container className='h-100'>
      <Header/>
      <Row className='justify-content-center'>
        <WrongCredLogin show={show} setShow={setShow} />
      </Row>
      <Row className="justify-content-center">
        <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="4">
          <Form onSubmit={handleSub}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control type="email" placeholder="Enter email"
                            value={mail} onChange={(e) => setMail(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control type="password" placeholder="Password"
                          value={pass} onChange={(e) => setPass(e.target.value)} />
            </Form.Group>

            <Button variant="primary" type="submit" >
              Sign in
            </Button>
          </Form>
        </Col>
      </Row>
      <Footer/>
    </Container>
  )
}

export default Login