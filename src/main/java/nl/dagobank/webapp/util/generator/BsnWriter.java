package nl.dagobank.webapp.util.generator;

import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.util.BsnUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BsnWriter {

    private static final Path BSN_CSV_FILE = Paths.get(System.getProperty("user.dir")).resolve("GeneratedData/bsn.csv");

    public BsnWriter() {
        super();
    }

    public static void writeBSNs( String[] args ) {
        try (
                BufferedWriter bufferedWriter = Files.newBufferedWriter( BSN_CSV_FILE ) ;
                CSVPrinter csvPrinter = new CSVPrinter( bufferedWriter, CSVFormat.DEFAULT )
        ) {
            int baseNumber = 564234215;
            int roofNumber = baseNumber + 80000;

            while ( baseNumber <= roofNumber ) {
                if ( isCorrectBsn( baseNumber ) ) {
                    csvPrinter.printRecord( baseNumber );
                }
                baseNumber++;

            }

            csvPrinter.flush();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private static boolean isCorrectBsn( int inputBSN ) {
        return BsnUtil.isCorrect( inputBSN );
    }

    public static Iterable<Integer> getBsn( Integer start ) {


        return () -> new Iterator<Integer>() {

            private int currentBsn = start;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                System.out.println( currentBsn );
                while ( !BsnUtil.isCorrect( currentBsn ) ) {
                    currentBsn++;
                }
                return currentBsn;
            }
        };
    }
}
