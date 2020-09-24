package nl.dagobank.webapp.util.generator;

import nl.dagobank.webapp.backingbeans.Business;

import java.util.concurrent.ThreadLocalRandom;

public class BusinessGenerator {

    public static void main( String[] args ) {
        BusinessGenerator businessGenerator = new BusinessGenerator();
        for ( int i = 0 ; i < 1000 ; i++ ) {
            System.out.println( businessGenerator.next() );
        }
    }

    private final String[] sbiCodes = new String[]{
            "A: Landbouw, bosbouw en Visserij", "B: Winning van Delftstoffen ",
            "C: Industrie", "D: Productie en Distributie van en handel in energie ",
            "E: Winning en distributie van water", "F: Bouwnijverheid", "G: Groot- en detailhandel", "H: Vervoer en opslag"
    };

    private int kvkNumberStart;

    private Business currentBusiness;

    public BusinessGenerator() {
        this( 11122233 );
    }

    public BusinessGenerator( int kvkNumberStart ) {
        super();
        this.kvkNumberStart = kvkNumberStart;
    }

    private void createBusiness() {
        int random = ThreadLocalRandom.current().nextInt( 0, 8 );
        String sbiCode = sbiCodes[ random ];
        String name = "bedrijf" + kvkNumberStart % 1000 + sbiCode.substring( 0, 1 );
        currentBusiness = new Business( name, kvkNumberStart++, sbiCodes[ random ] );
    }

    public Business next() {
        createBusiness();
        return currentBusiness;
    }
}
