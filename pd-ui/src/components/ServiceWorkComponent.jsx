import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";

const ServiceWorkComponent = () => {

    return (
        <main>
        <Container className="h-100 pt-5">
          <Row className="justify-content-center">
            <h2 className="text-center">We're sorry.</h2>
            <h4 className="text-center">This page is temporarily unavailable.</h4>
            <h4 className="text-center">Our engineers are working quickly to resolve the issue.</h4>
          </Row>

        </Container>
      </main>
    )

}

export default ServiceWorkComponent;

