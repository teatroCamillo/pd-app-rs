import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
const GettingStartedComponent = () => {
    return (
        <main>
            <Container className="h-100">
                <Row className="justify-content-md-center">
                    <Col className="bg-light text-dark rounded-4 p-4 shadow-lg" md="8">
                        <h3>Let's start</h3>
                        <br></br>
                        <p>Hello there!<br></br>
                            In this short tutorial, I will describe how to use this website.
                            The system is devided into 3 precise steps in which you will have to
                            take action. Each of them will be discussed in detail. I invite you to read.
                        </p>

                        <h4>The first step - your mind</h4>
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

                        <p>So now please select <strong>Personal Examination</strong> from the top menu <strong>(you must be logged in)</strong> and fill out two risk and gambling forms.
                        This forms are required. Otherwise the system won't work.</p>

                        <p>Remember that the green border card means the test is completed and the red is
                        not. But if you have already completed the form, you can always do it again. We recommend
                        repeating each test every six months.</p>

                        <h4>Second step - tech analysis</h4>
                        <p>Here the algorithm takes the controel and perfoms implemented the technical analise.
                        In shortcut, it fetchs the actual data and uses on them the certain technical indicatiors.
                        Like RSI, MACD or others.
                        Then the results are calculated and the probalility is set base on them.</p>

                        <p>Please remember that indicatior are not perfect tools and don't garantee any success on the
                        market. These are only helper which work on historical data and can more or less point the direction
                        of thingking about certain transaction.</p>

                        <h4>Third step - macro analysis</h4>
                        <p>Personal research and technical analysis are not everything. We also need to examin the macroeconomic factors too.
                        Like GDP, inflation, unemployment and others.</p>

                        <p>Here is the part resposible for that. The service
                        automaticly downloads the most important data and processes at the transaction angle.</p>

                        <h4>Last thing</h4>
                        <p>When the all steps are fulfilled. Last thing is to recap whole analisis. This is the point
                            where service summarize everything and suggests the best action to take.</p>

                        <p>The rest depends on you, good luck!</p>
                    </Col>
                </Row>
            </Container>
        </main>
    );
  };

  export default GettingStartedComponent;