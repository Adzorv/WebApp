package nl.dagobank.webapp.util.generator;

import nl.dagobank.webapp.util.BsnUtil;

public class BsnGenerator {
    private int currentBsn;

    public BsnGenerator( int base ) {
        int current = base;
        while ( !BsnUtil.isCorrect( current ) ) {
            current++;
        }
        currentBsn = current;
    }

    public BsnGenerator() {
        currentBsn = 10000020;
    }

    public int next() {
        currentBsn++;
        while ( !BsnUtil.isCorrect( currentBsn ) ) {
            currentBsn++;
        }
        return currentBsn;
    }


}
