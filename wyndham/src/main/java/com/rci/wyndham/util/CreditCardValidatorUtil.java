package com.rci.wyndham.util;

import com.rci.wyndham.model.BaseObject;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreditCardValidatorUtil extends BaseObject {

    // credit card pattern matching for different card types
    public String creditCardPattern = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35d{3})d{11})$";

    // All Visa card numbers start with a 4. New cards have 16 digits. Old cards have 13.
    public String visaCCPattern = "^4[0-9]{12}(?:[0-9]{3})?$";

    // All MasterCard numbers start with the numbers 51 through 55. All have 16 digits.
    public String mastercardCCPattern = "^5[1-5][0-9]{14}$";

    // American Express card numbers start with 34 or 37 and have 15 digits
    public String americanExpressCCPattern = "^3[47][0-9]{13}$";

    // Diners Club card numbers begin with 300 through 305, 36 or 38. All have 14 digits
    public String dinersClubCCPattern = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$";

    // ccv pattern (code on back of credit card)
    public String ccvPattern = "^[0-9]{3,4}";

    public String jcbCCPattern = "^(2131|1800|35d{3})d{11}$";

    /**
     * Validates the given credit card number using the given pattern.
     * @param creditCardNumber the credit card number
     * @param pattern the pattern
     * @return true if valid
     */
    public static boolean isValid(String creditCardNumber, String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(creditCardNumber);
        return matcher.find();
    }

    public static boolean isCreditCardValid(String creditCardNumber) {
        creditCardNumber = StringUtils.trim(creditCardNumber);
        Integer firstNumber = Integer.valueOf(StringUtils.substring(creditCardNumber, 0, 1));

        Pattern americanExpressPattern1 = Pattern.compile("^3\\d{2}[ \\-]?\\d{6}[ \\-]?\\d{5}$");
        Pattern americanExpressPattern2 = Pattern.compile("^3\\d{3}[ \\-]?\\d{6}[ \\-]?\\d{5}$");
        Pattern visaPattern = Pattern.compile("^4\\d{3}[ \\-]?\\d{4}[ \\-]?\\d{4}[ \\-]?\\d{4}$");
        Pattern mastercardPattern = Pattern.compile("^5\\d{3}[ \\-]?\\d{4}[ \\-]?\\d{4}[ \\-]?\\d{4}$");
        Pattern discoveryPattern = Pattern.compile("^6011[ \\-]?\\d{4}[ \\-]?\\d{4}[ \\-]?\\d{4}$");

        Matcher matcher = null;

        switch (firstNumber) {
            case 3:
                matcher = americanExpressPattern1.matcher(creditCardNumber);
                if (matcher.find()) {
                    return true; // American Express number is correct. Process the credit card.
                }

                matcher = americanExpressPattern2.matcher(creditCardNumber);
                if (matcher.find()) {
                    return true; // American Express number is correct. Process the credit card.
                }
                break;

            case 4:
                matcher = visaPattern.matcher(creditCardNumber);
                if (matcher.find()) {
                    return true; // Visa number is correct. Process the credit card.
                }
                break;

            case 5:
                matcher = mastercardPattern.matcher(creditCardNumber);
                if (matcher.find()) {
                    return true; // MasterCard number is correct. Process the credit card.
                }
                break;

            case 6:
                matcher = discoveryPattern.matcher(creditCardNumber);
                if (matcher.find()) {
                    return true; // Discover Card number is correct. Process the credit card.
                }
                break;

            default:
                // error
        }
        return false;
    }

    public String getCreditCardPattern() {
        return creditCardPattern;
    }

    public void setCreditCardPattern(String creditCardPattern) {
        this.creditCardPattern = creditCardPattern;
    }

    public String getVisaCCPattern() {
        return visaCCPattern;
    }

    public void setVisaCCPattern(String visaCCPattern) {
        this.visaCCPattern = visaCCPattern;
    }

    public String getMastercardCCPattern() {
        return mastercardCCPattern;
    }

    public void setMastercardCCPattern(String mastercardCCPattern) {
        this.mastercardCCPattern = mastercardCCPattern;
    }

    public String getAmericanExpressCCPattern() {
        return americanExpressCCPattern;
    }

    public void setAmericanExpressCCPattern(String americanExpressCCPattern) {
        this.americanExpressCCPattern = americanExpressCCPattern;
    }

    public String getDinersClubCCPattern() {
        return dinersClubCCPattern;
    }

    public void setDinersClubCCPattern(String dinersClubCCPattern) {
        this.dinersClubCCPattern = dinersClubCCPattern;
    }

    public String getCcvPattern() {
        return ccvPattern;
    }

    public void setCcvPattern(String ccvPattern) {
        this.ccvPattern = ccvPattern;
    }

    public String getJcbCCPattern() {
        return jcbCCPattern;
    }

    public void setJcbCCPattern(String jcbCCPattern) {
        this.jcbCCPattern = jcbCCPattern;
    }
}
