import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A program that will accept an amount and convert it to the
 * appropriate string representation.
 * 
 * Example:
 * 
 * Convert 2523.04
 * 
 * to "Two thousand five hundred twenty-three and 04/100
 * dollars"
 * 
 * @author Praveen Chavali
 *
 */
public class DollarAmountToString {

    // A map holding all our int to string mappings
    private static Map<Integer, String> numToString = new HashMap<Integer, String>();
    static {
        numToString.put(1, "One");
        numToString.put(2, "Two");
        numToString.put(3, "Three");
        numToString.put(4, "Four");
        numToString.put(5, "Five");
        numToString.put(6, "Six");
        numToString.put(7, "Seven");
        numToString.put(8, "Eight");
        numToString.put(9, "Nine");
        numToString.put(10, "Ten");
        numToString.put(11, "Eleven");
        numToString.put(12, "Twelve");
        numToString.put(13, "Thirteen");
        numToString.put(14, "Fourteen");
        numToString.put(15, "Fifteen");
        numToString.put(16, "Sixteen");
        numToString.put(17, "Seventeen");
        numToString.put(18, "Eighteen");
        numToString.put(19, "Nineteen");
        numToString.put(20, "Twenty");
        numToString.put(30, "Thirty");
        numToString.put(40, "Forty");
        numToString.put(50, "Fifty");
        numToString.put(60, "Sixty");
        numToString.put(70, "Seventy");
        numToString.put(80, "Eigthy");
        numToString.put(90, "Ninety");
        numToString.put(100, "Hundred");
        numToString.put(1000, "Thousand");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String sign = "";               // String representing our sign bit
        String strBeforeDecimal = "";   // String representing digits before the decimal point
        String strAfterDecimal = "";    // String representing digits after the decimal point
        double dollars = 0.0;
        try {
            dollars = in.nextDouble();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Exception while reading input: " + e.getLocalizedMessage());
        } finally {
            in.close();
        }
        // Zero is handled as a special case
        if (dollars == 0.0) {
            System.out.print("zero");
            return;
        } else {
            long beforeDecimal = (long) dollars;
            if (dollars < 0) {
                sign = "Minus ";
                beforeDecimal = 0 - beforeDecimal;
            }
            /**
             * Step 1: Process digits before the decimal point
             * Start processing from thousands onwards.
             * Right now, we don't have code to handle million/billion or higher
             */

            if (beforeDecimal > 1000) {
                int thousands = (int) beforeDecimal / 1000;
                strBeforeDecimal += numToString.get(thousands) + " " + numToString.get(1000) + " ";
                beforeDecimal = beforeDecimal % 1000;
            }
            if (beforeDecimal > 100) {
                int hundreds = (int) (beforeDecimal / 100);
                strBeforeDecimal += numToString.get(hundreds) + " " + numToString.get(100) + " ";
                beforeDecimal = beforeDecimal % 100;
            }
            if (beforeDecimal < 20) {
                String teens = numToString.get((int) beforeDecimal);
                strBeforeDecimal += teens;
            } else {
                int digit = (int) (beforeDecimal % 10);
                String digits = numToString.get(digit);
                int remainder = (int) (beforeDecimal / 10);
                String tens = numToString.get(remainder * 10);
                strBeforeDecimal += tens + "-" + digits;

            }

            /**
             * Step 2: Process digits after the decimal point
             */
            strAfterDecimal = String.valueOf(dollars);
            strAfterDecimal = strAfterDecimal.replaceAll("[-0-9]*\\.", "");
            int power = (int) Math.pow(10, strAfterDecimal.length());
            if (Integer.valueOf(strAfterDecimal) != 0) {
                strAfterDecimal = " AND " + strAfterDecimal + "/" + String.valueOf(power);
            } else {
                strAfterDecimal = "";
            }
            System.out.print(sign + strBeforeDecimal + strAfterDecimal);
        }
        System.out.println(" dollars");
    }
}
