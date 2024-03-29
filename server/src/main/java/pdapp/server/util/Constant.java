package pdapp.server.util;

public class Constant {

    /**
     * A positive answer to at least 7 questions indicates a high risk of addiction
     * to gambling and is an indication for consultation with an addiction therapist.
     *
     * Scoring:
     * highly addicted: at least 7
     * moderately addicted: at least 4 and less than 7
     * not addicted: less than 4
     */
    public final static String HIGHLY_ADDICTED = "highly addicted";
    public final static String MODERATELY_ADDICTED = "moderately addicted";
    public final static String NOT_ADDICTED = "not addicted";

    /**
     * The score that is received on the Investment Risk Test can be interpreted as follows:
     * 18 or below = Low risk tolerance (i.e., conservative investor)
     * 19 to 22 = Below-average risk tolerance
     * 23 to 28 = Average/moderate risk tolerance
     * 29 to 32 = Above-average risk tolerance
     * 33 and above = High risk tolerance (i.e., aggressive investor)
     */
    public final static String LOW_RISK_TOLERANCE = "low";
    public final static String BELOW_AVERAGE_RISK_TOLERANCE = "below average";
    public final static String AVERAGE_RISK_TOLERANCE = "average";
    public final static String ABOVE_AVERAGE_RISK_TOLERANCE = "above average";
    public final static String HIGH_RISK_TOLERANCE = "high";

    /**
     * Personal
     */
    public final static String PERSONAL_POINTS = "personalPoints";
    public final static String GAMBLING_RESULT = "gamblingResult";
    public final static String RISK_RESULT = "riskResult";

    /**
     * Tech
     */
    public final static String CURRENT_PRICE = "currentPrice";
    public final static String TECH_POINTS = "techPoints";
    public final static String STRATEGY_MET = "strategyMet";
    public final static String STRATEGY_MET_POINTS = "strategyMetPoints";
    public final static String RSI_14 = "rsi14";
    public final static String RSI_14_POINTS = "rsi14Points";
    public final static String MACD = "macd";
    public final static String MACD_POINTS = "macdPoints";
    public final static Double SELL_OUT_LINE_70_RSI = 70.0D;

    /**
     * Macro
     */
    public final static String MACRO_POINTS = "macroPoints";
    public final static String GDP_POINTS = "gdpPoints";
    public final static String INF_POINTS = "infPoints";
    public final static String EA_INF = "eaInf";
    public final static String US_INF = "usInf";
    public final static String EA_GDP_GROWTH_LATEST_Q = "eaGdpGrowthLatestQ";
    public final static String US_GDP_GROWTH_LATEST_Q = "usGdpGrowthLatestQ";
    public final static String SCORE = "score";

    /**
     * Recommendations
     */
    public final static String REC_DESCRIPTION = "description";
    public final static String TAKE_PROFIT = "takeProfit";
    public final static String STOP_LOSS = "stopLoss";
    public final static String MAX_AMOUNT_TO_INVEST = "maxAmountToInvest";

    /**
     * Date & datetime
     */
    public final static String DATE_MDY_PATTERN = "MM/dd/yyyy";
    public final static String DATE_TIME = "dateTime";
}
