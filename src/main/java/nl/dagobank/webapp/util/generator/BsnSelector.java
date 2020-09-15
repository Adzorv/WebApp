package nl.dagobank.webapp.util.generator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BsnSelector {

    public static final String FILE = "GeneratedData/bsn.csv";

    public static int getBsnAtLine( int line ) {
        try (
                Reader reader = Files.newBufferedReader( getPathToBsnFile() );
                CSVParser csvParser = new CSVParser( reader, CSVFormat.DEFAULT );
                ) {

            return Integer.parseInt( csvParser.getRecords().get( line ).get( 0 ) ) ;
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main( String[] args ) {
        getPathToBsnFile();
        System.out.println(getBsnAtLine( 0 ));
    }

    private static Path getPathToBsnFile() {
        Path rootPath = Paths.get(System.getProperty( "user.dir" ));
        return rootPath.resolve(FILE);
    }

}
