
package pdapp.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "start_date",
    "base",
    "success",
    "end_date",
    "timeseries",
    "rates"
})
@Generated("jsonschema2pojo")
@ToString
public class TechData {

    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("base")
    private String base;
    @JsonProperty("success")
    private String success;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("timeseries")
    private Boolean timeseries;

    @JsonProperty("rates")
    private Rates rates;

    @JsonProperty("rates")
    public Rates getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(Rates rates) {
        this.rates = rates;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("success")
    public String getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(String success) {
        this.success = success;
    }

    @JsonProperty("end_date")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("timeseries")
    public Boolean getTimeseries() {
        return timeseries;
    }

    @JsonProperty("timeseries")
    public void setTimeseries(Boolean timeseries) {
        this.timeseries = timeseries;
    }

}
