import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

const PersonalExaminationComponent = () => {

  return (
    <main>
      <Container className="h-100">
        <Row className="justify-content-md-center">
          <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="8">
              <h2>The first step - your mind</h2>
              <p>One of the most essential thing in sucessfull investing is investor's mindset.
              We need to know that the investor gambles until he becomes aware of his positive
              and negative emotions that significantly affect the decisions he makes.</p>

              <p>Therefor here we are going to take control on our emotions like greed,
              fear, haste, uncertainty, revenge, bad mood, emotions at work, emotions at home, happy,
              successes at life and many many more.</p>

              <p><strong>You'll ask, ok but how?</strong></p>

              <p>Well, we prepared two forms which create your profil.
              And based on this profile the algorithm will relieve you from the rush of emotions.
              Thanks to this, the suggestion of a financial operation will not be distorted by
              random and often unfavorable emotions.</p>

              <p>Remember, you will only receive a faithfully reproduced suggestion of what should be done
              in a given situation on the market as if you were completely free from emotions. <strong>BUT
                the final decision is always up to you.</strong></p>

              <p>We encourage you to test a few dry trades on different days so that your emotions may differ.
              And comparing the decisions received from the bot with your own.</p>

              <p>So now please fulfill two below forms related with risk and gambling.
              This forms are required. Otherwise the system won't work.</p>

              <div>
                <Button variant="danger" href="/personal-ex/risk">Risk Test</Button>
                <Button variant="danger" href="/personal-ex/gambling">Gambling Test</Button>
              </div>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default PersonalExaminationComponent;
