import axios from "axios";

class FormsUtilService {

    sendRiskFormResults = (answers) => {
        return axios.post("http://localhost:8080/risk", answers);
    };

}

export default new FormsUtilService();

