package org.example;

import java.util.HashMap;
import java.util.Map;

public class Constants {    static final long SECOND = 1000;
    static final long MINUTE = SECOND * 60;
    static final long HOUR = MINUTE * 60;
    static final long DAY = HOUR * 24;
    static final long MONTH = DAY * 30;
    static final long YEAR = DAY * 365;
    static final Map<Character, Long> MAP = new HashMap<>();
    static {
        MAP.put('s', SECOND);
        MAP.put('m', MINUTE);
        MAP.put('h', HOUR);
        MAP.put('d', DAY);
        MAP.put('M', MONTH);
        MAP.put('Y', YEAR);
    }
}
