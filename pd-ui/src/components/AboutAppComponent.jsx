import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const AboutAppComponent = () => {
    return (
        <main>
            <Container className="h-100">
                <Row className="justify-content-md-center">
                    <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="8">
                        <h3>Purpose of the application</h3>
                        <br></br>
                        <p>Each investor, both beginners and experienced,
                            faces the same problems when trading. Unfortunately,
                            the vast majority of investors in the Forex market experience
                            losses. As the Polish Financial Supervision Authority (KNF) presents
                            in its decision
                            (<a href="https://dziennikurzedowy.knf.gov.pl/DU_KNF/2019/27/akt.pdf" target="_blank" rel="noreferrer">remote</a> or <a href="./docs/decyzja-knf-z-1-8-2019.pdf" target="_blank" rel="noreferrer">static</a>),
                            approximately 80% of retail accounts recorded
                            losses from 2017 to 2019.</p>
                        <p>This app is the response to these uninviting statistics.</p>
                        <p>The main goals of the service are:</p>
                        <ul>
                            <li>relieving the investor of emotions</li>
                            <li>technical analysis</li>
                            <li>fundamental analysis</li>
                            <li>estimating the probability of a profitable transaction on the basis of the above steps</li>
                        </ul>
                    </Col>
                </Row>
            </Container>
        </main>
    );
  };

  export default AboutAppComponent;