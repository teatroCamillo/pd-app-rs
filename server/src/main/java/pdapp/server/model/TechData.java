
package pdapp.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TechData {

    @JsonProperty("Date")
    private String date;
    @JsonProperty("Price")
    private Double price;
    @JsonProperty("Open")
    private Double open;
    @JsonProperty("High")
    private Double high;
    @JsonProperty("Low")
    private Double low;

}
