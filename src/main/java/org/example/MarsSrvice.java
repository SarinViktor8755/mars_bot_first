package org.example;

import java.util.Locale;

import static org.example.Main.Distance_Earth_Mars;
import static org.example.Main.km;

public class MarsSrvice {

    static final String ballEmoji = "\uD83D\uDC4D";

    static String calculate_percentage(long km, int user_run) {

        float res = (km / Distance_Earth_Mars) * 100;
        if (res <= 100)
            return "Принято " + user_run + " км " + create_emogi(user_run) + "\n" + String.format(Locale.US, "%,d", Main.km_temp)  + "+" +
                    user_run + "=" + String.format(Locale.US, "%,d", km) + " \\ " +
                    String.format(Locale.US, "%,d", (int) ((get_l_to_target()))) + " \n"
                    + create_track_bar(19, res);
        else
            return "Финиш!!!\nПробежали " + String.format(Locale.US, "%,d", km) + "  https://www.asn-news.ru/uploads/news/photo/big/scalhobr2.jpeg";
    }

    static public float get_l_to_target() {
        return Distance_Earth_Mars - km;
    }

    static String create_track_bar(int length_bar) {
        int point = (int) map(0, Distance_Earth_Mars, 0, length_bar, km);
        StringBuilder sb = new StringBuilder();
        sb.append("?|");
        for (int i = 0; i < length_bar; i++) {
            if (point == i) sb.append("*(??)>");
            if (point < i) sb.append("-");
            if (point > i) sb.append("=");

        }
        sb.append("|?");
        return sb.toString();
    }

    static String create_track_bar(int length_bar, float proc) {
        int point = (int) map(0, Distance_Earth_Mars, 0, length_bar, km);
        StringBuilder sb = new StringBuilder();
        sb.append("\uD83C\uDF0F|");
        for (int i = 0; i < length_bar; i++) {
            if (point == i) sb.append("" + String.format("%.1f", proc) + "%>");
            if (point < i) sb.append("-");
            if (point > i) sb.append("=");

        }
        sb.append("|\uD83D\uDD34");
        return sb.toString();
    }

    static String create_emogi(long km) {
        StringBuilder sb = new StringBuilder();
        int kol = (int) (km / 4f);
        if (kol > 7) kol = 7;
        if (kol < 1) kol = 1;
        for (int i = 0; i < kol; i++) {
            sb.append(ballEmoji);
        }


        return sb.toString();
    }


    static public float map(float inRangeStart, float inRangeEnd, float outRangeStart, float outRangeEnd, float value) {
        return outRangeStart + (value - inRangeStart) * (outRangeEnd - outRangeStart) / (inRangeEnd - inRangeStart);
    }
}
