package nl.dagobank.webapp.util.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BsnSetter {

    public static void createUserScript() {

        Path path = Paths.get(System.getProperty( "user.dir" )).resolve( "GeneratedData/bsnInsert.sql" );
        File file = new File( path.toString() );
        try ( PrintWriter printWriter = new PrintWriter( file ) ) {
            for ( int i = 1 ; i < 5010 ; i++ ) {
                String update = createRecordString( BsnSelector.getBsnAtLine( i ), i  );
                printWriter.write( update );
            }
            printWriter.flush();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private static String createRecordString( int bsn, int i ) {
        return String.format("UPDATE `dagobank`.`user` SET `bsn` = '%d' WHERE (`id` = '%d');", bsn, i);
    }

}
