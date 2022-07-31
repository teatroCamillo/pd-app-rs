import Alert from 'react-bootstrap/Alert';


const WrongCredLogin = ({showWrongCredLogin, setShowWrongCredLogin}) => {

  if(showWrongCredLogin){
    return (
      <Alert className='w-50' variant="danger" onClose={() => setShowWrongCredLogin(false)} dismissible>
        <h6>Oh snap! You got an error! Wrong credentials, try again ;)</h6>
      </Alert>
    )};
}

export default WrongCredLogin