package nl.dagobank.webapp.util.generator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BsnWriter {

    private static final String BSN_CSV_FILE = "/Users/manueloostveen/IdeaProjects/MIW/DagoBankWebapp/GeneratedData/bsn.csv";


    public BsnWriter() {
        super();
    }

    public static void main(String[] args) {
        try (
                BufferedWriter bufferedWriter = Files.newBufferedWriter( Paths.get( BSN_CSV_FILE ) ) ;
                CSVPrinter csvPrinter = new CSVPrinter( bufferedWriter, CSVFormat.DEFAULT )
        ) {
            int baseNumber = 564234215;
            int roofNumber = baseNumber + 50000;

            while (baseNumber <= roofNumber) {
                if (isCorrectBsn( baseNumber )) {
                    csvPrinter.printRecord( baseNumber );
                    System.out.println(baseNumber);
                }
                baseNumber++;

            }

            csvPrinter.flush();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private static boolean isCorrectBsn( int inputBSN ) {
        int bsnToCheck = -1 * inputBSN % 10;
        for ( int multiplier = 2 ; inputBSN > 0 ; multiplier++ ) {
            int val = ( inputBSN /= 10 ) % 10;
            bsnToCheck += multiplier * val;
        }
        return bsnToCheck != 0 && bsnToCheck % 11 == 0;
    }




}
