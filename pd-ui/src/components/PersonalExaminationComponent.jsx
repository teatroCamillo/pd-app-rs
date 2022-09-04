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
              successes at life and many many more. Note that it doesn't matter whether they are
              positive or negative. If they appear, they distort the investor's perception.</p>

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

              <p>Remember that the green border card means the test is completed and the red is
              not. But if you have already completed the form, you can always do it again. We recommend
              repeating each test every six months.</p>

              <div className="row">
                <div className="col-sm-6">
                  <div className="card border-danger border border-4">
                    {/*Obraz <a href="https://pixabay.com/pl/users/tumisu-148124/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=4757854"> Tumisu</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=4757854"> Pixabay</a> */}
                    <img src="./images/personal-ex/risk0.jpg" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Risk</h5>
                      <Button variant="primary" href="/personal-ex/risk">Take Test</Button>
                    </div>
                  </div>
                </div>
                <div className="col-sm-6">
                  <div className="card border-danger border border-4">
                    {/*Obraz <a href="https://pixabay.com/pl/users/stokpic-692575/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=602976"> stokpic</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=602976"> Pixabay</a> */}
                    <img src="./images/personal-ex/gambling0.jpg" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Gambling</h5>
                      <Button variant="primary" href="/personal-ex/gambling">Take Test</Button>
                    </div>
                  </div>
                </div>
              </div>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default PersonalExaminationComponent;
