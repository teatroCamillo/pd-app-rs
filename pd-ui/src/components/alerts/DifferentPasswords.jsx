import Alert from 'react-bootstrap/Alert';

const DifferentPasswords = ({differentPasswords, setDifferentPasswords}) => {

  if(differentPasswords){
    return (
      <Alert className='w-100' variant="danger" onClose={() => setDifferentPasswords(false)} dismissible>
        <h6>Oh snap! We've detected different passwords in the registration form. Please check it and try again.</h6>
      </Alert>
    )
  };
}

export default DifferentPasswords;