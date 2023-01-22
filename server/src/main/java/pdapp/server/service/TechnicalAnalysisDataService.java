package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
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

@Service
@Slf4j
public class TechnicalAnalysisDataService {

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

        // create barSeries
        BarSeries series = createBarSeriesFromRatesMap(tm);

        // ClosePriceIndicator
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        resultMap.put("closePrice", closePrice.getValue(series.getEndIndex()).toString());


        // RSI-14 *******************************************************************************************
        RSIIndicator rsi = new RSIIndicator(closePrice, 14);
//        for(int i = 0; i < rsi.getBarSeries().getBarCount(); i++){
//            log.info("RSI-14: " + " index: " + i + " : "  + rsi.getValue(i) + " : " + rsi.getBarSeries().getBar(i));
//        }
        String rsiValue = String.valueOf(rsi.getValue(rsi.getBarSeries().getEndIndex()));
        int floatPointIndex = rsiValue.indexOf(".");
        rsiValue = rsiValue.substring(0, floatPointIndex + 5);
        resultMap.put("actualRSI14", rsiValue);
        // *******************************************************************************************

        // MACD ************************************************************************
        MACDIndicator macd = new MACDIndicator(closePrice);
//        for(int i = 0; i < macd.getBarSeries().getBarCount(); i++){
//            log.info("macd: " + " index: " + i + " : "  + macd.getValue(i) + " : " + macd.getBarSeries().getBar(i));
//        }
        String macdValue = String.valueOf(macd.getValue(macd.getBarSeries().getEndIndex()));
        floatPointIndex = macdValue.indexOf(".");
        macdValue = macdValue.substring(0, floatPointIndex + 5);
        resultMap.put("macd", macdValue);
        // ************************************************************************

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
            rats.put(LocalDate.parse(techData.getDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")), techData);
        }
        return rats;
    }

}
