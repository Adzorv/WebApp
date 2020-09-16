package nl.dagobank.webapp.util;

public class BsnUtil {

    public static boolean isCorrect( int inputBSN ) {
        if ( inputBSN <= 9999999 || inputBSN > 999999999 ) {
            return false;
        }
        int bsnToCheck = -1 * inputBSN % 10;
        for ( int multiplier = 2 ; inputBSN > 0 ; multiplier++ ) {
            int val = ( inputBSN /= 10 ) % 10;
            bsnToCheck += multiplier * val;
        }
        return bsnToCheck != 0 && bsnToCheck % 11 == 0;
    }

}
