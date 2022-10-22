import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Container";

const FormSavedCorrectlyComponent = () => {

  return (
    <main>
      <Container className="h-100 pt-5">
        <Row className="justify-content-center">
          <h2 className="text-center">Great!</h2>
          <h3 className="text-center">We've successfully saved your form.</h3>
        </Row>
      </Container>
    </main>
  )
};

export default FormSavedCorrectlyComponent;