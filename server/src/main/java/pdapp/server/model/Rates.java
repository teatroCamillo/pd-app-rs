
package pdapp.server.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
public class Rates {

    @JsonAnyGetter
    private Map<String, String> natMap;

    @JsonAnyGetter
    public Map<String, String> getNatMap() {
        return natMap;
    }
    @JsonAnyGetter
    public void setNatMap(Map<String, String> natMap) {
        this.natMap = natMap;
    }


    //    private Map<String, String> entry;
//
//    public Rates(Map<String, String> entry) {
//        this.entry = new HashMap<String,String>();
//    }
//
//
//    public Map<String, String> getEntry() {
//        return entry;
//    }
//
//    public void setEntry(Map<String, String> entry) {
//        this.entry = entry;
//    }
}
