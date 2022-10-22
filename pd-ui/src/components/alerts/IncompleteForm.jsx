import Alert from 'react-bootstrap/Alert';

const IncompleteForm = ({showIncompleteForm, setShowIncompleteForm}) => {

  if(showIncompleteForm){
    return (
      <Alert className='w-100' variant="danger" onClose={() => setShowIncompleteForm(false)} dismissible>
        <h6>Seems the form is incomplete! Please check it and try again!</h6>
      </Alert>
    )};
}

export default IncompleteForm;