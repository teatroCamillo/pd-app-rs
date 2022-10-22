import Alert from 'react-bootstrap/Alert';

const FormSendError = ({showFormSendError, setShowFormSendError}) => {

  if(showFormSendError){
    return (
      <Alert className='w-100' variant="danger" onClose={() => setShowFormSendError(false)} dismissible>
        <h6>Oh snap! Something went wrong! Please, try again later!</h6>
      </Alert>
    )};
}

export default FormSendError