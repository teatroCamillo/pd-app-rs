import Alert from 'react-bootstrap/Alert';

const LackOfPersonalExaminationError = ({lackOfExamination, setLackOfExamination}) => {

  if(lackOfExamination){
    return (
      <Alert className='w-100' variant="danger" onClose={() => setLackOfExamination(false)} dismissible>
        <h6>Oh snap! We've noticed that you haven't completed the personal forms. Please, do it first!</h6>
      </Alert>
    )};
}

export default LackOfPersonalExaminationError;