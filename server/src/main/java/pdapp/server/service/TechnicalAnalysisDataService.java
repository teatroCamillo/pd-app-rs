package pdapp.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TechnicalAnalysisDataService {

    private final ObjectMapper om;

    public TechnicalAnalysisDataService() {
        this.om = new ObjectMapper();
    }

    public BarSeries createBarSeriesFromRatesMap(TreeMap<LocalDate, String> sortedRatesMap){
        BarSeries series = new BaseBarSeriesBuilder().withName("mySeries").build();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        //ZonedDateTime zdt = LocalDate.parse("2022-04-26", dateTimeFormatter).atStartOfDay(ZoneId.of("UTC"));
        //log.info("zone date time: " + zdt);

        for(Map.Entry entry : sortedRatesMap.entrySet()){
            ZonedDateTime zdt = LocalDate.parse(entry.getKey().toString(), dateTimeFormatter).atStartOfDay(ZoneId.of("UTC"));
            series.addBar(zdt, entry.getValue().toString(), entry.getValue().toString(),entry.getValue().toString(),entry.getValue().toString());
        }

        return series;
    }

    public TreeMap<LocalDate, String> getSortedRatesMapForEURUSD(Map<String,Object> response) throws JsonProcessingException {
        // take value from map from rates key and by Gson convert to string and then to new Map
        Gson gson = new Gson();
        String ent = gson.toJson(response.get("rates"), LinkedHashMap.class);
        Map<String, Object> rats = om.readValue(ent, new TypeReference<HashMap<String, Object>>() {});

        //mapping to date for sorting purpose by date
        Map<LocalDate, String> newRats = rats.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                e -> LocalDate.parse(e.getKey(), DateTimeFormatter.ISO_LOCAL_DATE),
                                e -> StringUtils.substringBetween(e.getValue().toString(), "USD=",",")));

        return new TreeMap<>(newRats);
    }

}
