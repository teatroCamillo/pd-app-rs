import Alert from 'react-bootstrap/Alert';

const WrongCredLogin = ({showWrongCredLogin, setShowWrongCredLogin}) => {

  if(showWrongCredLogin){
    return (
      <Alert className='w-100' variant="danger" onClose={() => setShowWrongCredLogin(false)} dismissible>
        <h6>Oh snap! Something went wrong! Maybe incorrect credentials, please try again ;)</h6>
      </Alert>
    )};
}

export default WrongCredLogin