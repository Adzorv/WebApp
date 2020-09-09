package nl.dagobank.webapp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtil {
    public static <K,V extends Comparable<? super V>> List<Map.Entry<K, V>> entriesSortedByValues( Map<K,V> map) {

        List<Map.Entry<K,V>> sortedEntries = new ArrayList<>(map.entrySet());

        sortedEntries.sort( ( e1, e2 ) -> e2.getValue().compareTo( e1.getValue() ) );

        return sortedEntries;
    }
}
