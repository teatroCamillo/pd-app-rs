package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.BooleanRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;
import org.ta4j.core.rules.StopGainRule;
import org.ta4j.core.rules.StopLossRule;
import pdapp.server.model.CoreBar;

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

    public Map<String, String> strategy(List<CoreBar> input){
        Map<String, String> resultMap = new HashMap<>();

        //Prepare data
        TreeMap<LocalDate, CoreBar> barSortedByDateMap = getSortedRatesMapForEURUSD(input);
        BarSeries series = createBarSeriesFromRatesMap(barSortedByDateMap);
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        resultMap.put(CURRENT_PRICE, closePrice.getValue(series.getEndIndex()).toString());

        //RSI
        RSIIndicator rsi = new RSIIndicator(closePrice, 14);
        String rsiResult = getLastIndicatorValueAndSubstring(rsi);
        resultMap.put(RSI_14, rsiResult);
        int rsi14Points = os.scoreRSI14(rsiResult);
        resultMap.put(RSI_14_POINTS, String.valueOf(rsi14Points));

        //MACD - 26 & 12 by default
        MACDIndicator macd = new MACDIndicator(closePrice);
        String macdResult = getLastIndicatorValueAndSubstring(macd);
        resultMap.put(MACD, macdResult);
        int macdPoints = os.scoreMACD(macdResult);
        resultMap.put(MACD_POINTS, String.valueOf(macdPoints));

        //EMA9 - for MACD - Signal Line
        EMAIndicator ema9 = new EMAIndicator(macd, 9);

        //Rules
        Rule buyingRule = new CrossedUpIndicatorRule(macd, ema9)
                .and(new BooleanRule(Double.parseDouble(rsiResult) < SELL_OUT_LINE_70_RSI));

        //Temp rule - required for BarSeriesManager.run(buyRule, sellRule)
        Rule sellingRule = new StopLossRule(closePrice, series.numOf(3))
                .or(new StopGainRule(closePrice, series.numOf(2)));

        //Strategy & results
        BarSeriesManager seriesManager = new BarSeriesManager(series);
        TradingRecord tradingRecord = seriesManager.run(new BaseStrategy(buyingRule, sellingRule));

        //If both match points max 20
        int diff = series.getEndIndex() - tradingRecord.getLastEntry().getIndex();
        int strategyPoints = 0;
        if(diff <= 1) strategyPoints = 20;

        //Scoring
        int techPoints = Math.min(rsi14Points + macdPoints + strategyPoints, 20);
        resultMap.put(STRATEGY_MET, strategyPoints > 0 ? "true" : "false");
        resultMap.put(STRATEGY_MET_POINTS, String.valueOf(strategyPoints));
        resultMap.put(TECH_POINTS, String.valueOf(techPoints));
        return resultMap;
    }

    private BarSeries createBarSeriesFromRatesMap(TreeMap<LocalDate, CoreBar> sortedRatesMap){
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

    private TreeMap<LocalDate, CoreBar> getSortedRatesMapForEURUSD(List<CoreBar> response){
        TreeMap<LocalDate, CoreBar> rats = new TreeMap<>();
        for (CoreBar coreBar : response) {
            rats.put(LocalDate.parse(coreBar.getDate(), DateTimeFormatter.ofPattern(DATE_MDY_PATTERN)), coreBar);
        }
        return rats;
    }

    private String getLastIndicatorValueAndSubstring(Indicator indicator){
        String indicatorValue = String.valueOf(indicator.getValue(indicator.getBarSeries().getEndIndex()));
        int floatPointIndex = indicatorValue.indexOf(".");
        return indicatorValue.substring(0, floatPointIndex + 5);
    }
}
