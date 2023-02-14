package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import pdapp.server.model.TechData;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static pdapp.server.util.Constant.*;

@Service
@Slf4j
public class TechnicalAnalysisDataService extends DataService {

    public TechnicalAnalysisDataService(OutcomeService os) {
        super(os);
    }

    /**
     * Assumptions
     * 1. Game for growth
     * 2. RSI 14 (daily interval) is lower than or equal 30.
     * 3. Don't focus on details or single bar
     * 4. Short-term transaction (few hours - max 4 days)
     * 5. MACD below 0 level
     * 6. Thanks the platform trader doesn't see a chart
     */

    public Map<String, String> strategy(List<TechData> input){
        Map<String, String> resultMap = new HashMap<>();

        // prepare data
        TreeMap<LocalDate, TechData> tm = getSortedRatesMapForEURUSD(input);
        //log.info("print getSorted");
        //tm.forEach((k,v) -> log.info(k.toString() + " : " + v.toString()));

        // create barSeries
        BarSeries series = createBarSeriesFromRatesMap(tm);
        //int count = series.getBarCount();
        //for(int i=0; i < count; i++) log.info(series.getBar(i).toString());

        // ClosePriceIndicator
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        resultMap.put(CLOSE_PRICE, closePrice.getValue(series.getEndIndex()).toString());

        resultMap.put(ACTUAL_RSI_14, rsi14(closePrice));
        resultMap.put(MACD, macd(closePrice));

        return resultMap;
    }

    private BarSeries createBarSeriesFromRatesMap(TreeMap<LocalDate, TechData> sortedRatesMap){
        BarSeries series = new BaseBarSeriesBuilder().withName("mySeries").build();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        for(LocalDate entry : sortedRatesMap.keySet()){
            ZonedDateTime zdt = LocalDate.parse(entry.toString(), dateTimeFormatter).atStartOfDay(ZoneId.of("UTC"));
            series.addBar(zdt,
                    sortedRatesMap.get(entry).getOpen(),
                    sortedRatesMap.get(entry).getHigh(),
                    sortedRatesMap.get(entry).getLow(),
                    sortedRatesMap.get(entry).getPrice());
        }
        return series;
    }

    private TreeMap<LocalDate, TechData> getSortedRatesMapForEURUSD(List<TechData> response){

        TreeMap<LocalDate, TechData> rats = new TreeMap<>();

        for (TechData techData : response) {
            rats.put(LocalDate.parse(techData.getDate(), DateTimeFormatter.ofPattern(DATE_MDY_PATTERN)), techData);
        }
        return rats;
    }

    private String rsi14(ClosePriceIndicator closePrice){
        RSIIndicator rsi = new RSIIndicator(closePrice, 14);
//        for(int i = 0; i < rsi.getBarSeries().getBarCount(); i++){
//            log.info("RSI-14: " + " index: " + i + " : "  + rsi.getValue(i) + " : " + rsi.getBarSeries().getBar(i));
//        }
        return getLastIndicatorValueAndSubstring(rsi);
    }

    private String macd(ClosePriceIndicator closePrice){
        MACDIndicator macd = new MACDIndicator(closePrice);
//        for(int i = 0; i < macd.getBarSeries().getBarCount(); i++){
//            log.info("macd: " + " index: " + i + " : "  + macd.getValue(i) + " : " + macd.getBarSeries().getBar(i));
//        }
        return getLastIndicatorValueAndSubstring(macd);
    }

    private String getLastIndicatorValueAndSubstring(Indicator indicator){
        String indicatorValue = String.valueOf(indicator.getValue(indicator.getBarSeries().getEndIndex()));
        int floatPointIndex = indicatorValue.indexOf(".");
        return indicatorValue.substring(0, floatPointIndex + 5);
    }
}
