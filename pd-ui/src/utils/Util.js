
class Util {

    isEmpty = inputObject => {
        return Object.keys(inputObject).length === 0;
    };

    setColorForOutcomeScore = inputObject => {
        let colorNr = '#fc0303';
        if(inputObject > 29) colorNr = '#ebfc03';
        if(inputObject > 69) colorNr = '#26cc08';
        return colorNr;
    };

    isFormComlete = (input, size) => {
        let isComplete = true;
        if(Object.keys(input).length !== size) isComplete = false;
        return isComplete;
    };
}

export default new Util();