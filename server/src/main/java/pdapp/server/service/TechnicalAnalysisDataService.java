package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
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

        BarSeries series = createBarSeriesFromRatesMap(tm);

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        resultMap.put(CLOSE_PRICE, closePrice.getValue(series.getEndIndex()).toString());

        String rsiResult = rsi14(closePrice);
        resultMap.put(ACTUAL_RSI_14, rsiResult);
        int rsi14Points = os.scoreRSI14(rsiResult);
        resultMap.put(RSI_14_POINTS, String.valueOf(rsi14Points));

        String macdResult = macd(closePrice);
        resultMap.put(MACD, macdResult);
        int macdPoints = os.scoreMACD(macdResult);
        resultMap.put(MACD_POINTS, String.valueOf(macdPoints));

        resultMap.put(TECH_POINTS, String.valueOf(rsi14Points + macdPoints));

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
        return getLastIndicatorValueAndSubstring(rsi);
    }

    private String macd(ClosePriceIndicator closePrice){
        MACDIndicator macd = new MACDIndicator(closePrice);
        return getLastIndicatorValueAndSubstring(macd);
    }

    private String getLastIndicatorValueAndSubstring(Indicator indicator){
        String indicatorValue = String.valueOf(indicator.getValue(indicator.getBarSeries().getEndIndex()));
        int floatPointIndex = indicatorValue.indexOf(".");
        return indicatorValue.substring(0, floatPointIndex + 5);
    }
}
