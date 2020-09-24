package nl.dagobank.webapp.util.generator;

import java.util.Iterator;

public class MainGenerator {

    public static void main( String[] args ) {
//        BsnSetter.createUserScript();
        Iterator<Integer> bsnGenerator = BsnWriter.getBsn( 564234215 , 100000000).iterator();
        System.out.println(bsnGenerator.next());
        System.out.println(bsnGenerator.next());
    }

}
