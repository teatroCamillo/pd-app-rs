package pdapp.server.util;

public class Consts {

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
     * The score that you receive on the Investment Risk Test can be interpreted as follows:
     * 18 or below = Low risk tolerance (i.e., conservative investor)
     * 19 to 22 = Below-average risk tolerance
     * 23 to 28 = Average/moderate risk tolerance
     * 29 to 32 = Above-average risk tolerance
     * 33 and above = High risk tolerance (i.e., aggressive investor)
     */

    public final static String LOW_RISK_TOLERANCE = "low";
    public final static String BELOW_AVERAGE_RISK_TOLERANCE = "below_average";
    public final static String AVERAGE_RISK_TOLERANCE = "average";
    public final static String ABOVE_AVERAGE_RISK_TOLERANCE = "above_average";
    public final static String HIGH_RISK_TOLERANCE = "high";
}
