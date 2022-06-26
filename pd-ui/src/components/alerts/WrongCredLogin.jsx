import Alert from 'react-bootstrap/Alert';


const WrongCredLogin = ({show, setShow}) => {

  if(show){
    return (
      <Alert className='w-50' variant="danger" onClose={() => setShow(false)} dismissible>
        <h6>Oh snap! You got an error! Wrong credentials, try again ;)</h6>
    </Alert>
    )};
}

export default WrongCredLogin