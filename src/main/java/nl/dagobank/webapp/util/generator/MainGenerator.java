package nl.dagobank.webapp.util.generator;

import java.util.Iterator;

public class MainGenerator {

    public static void main( String[] args ) {
//        BsnSetter.createUserScript();
        BsnGenerator bsnGenerator = new BsnGenerator(  );
        System.out.println(bsnGenerator.next());
        System.out.println(bsnGenerator.next());

    }

}
