package org.example;

import java.util.Locale;

import static org.example.Main.*;

public class MarsSrvice {

    static final String ballEmoji = "\uD83D\uDC4D";
    //static public final float Distance_Earth_Mars = 54_600; // ???? ?????????
    static String calculate_percentage(long km, int user_run) {

        float res = RouteService.get_percentage_stage((int)km);
   //     if (res <= 100)
            return "принято " + user_run + " км " + create_emogi(user_run) + "\n" + String.format(Locale.US, "%,d", Main.km_temp)  + "+" +
                    user_run + "=" + String.format(Locale.US, "%,d", km) + " \\ " +
                    String.format(Locale.US, "%,d", (int) ((get_l_to_target()))) + " \n"
                    + create_track_bar(19, res,km);
//        else{
//            return "Финиш!!!\nПробежали " + String.format(Locale.US, "%,d", km);
//        }

    }

    static public float get_l_to_target() {
        return RouteService.get_sum_distanc() - km;
    }

    static String create_track_bar(int length_bar, float res, long km) {
        int point = (int) map(0, 100, 0, length_bar, res);
        StringBuilder sb = new StringBuilder();
 //       sb.append("?|");
        String proc = String.format(Locale.US, "%,d", (int) (res));
        for (int i = 0; i < length_bar; i++) {
            if (point == i) sb.append(proc + "%");
            if (point < i) sb.append("-");
            if (point > i) sb.append("-");

        }
   //     sb.append("|?");
        sb.append("\n"+ RouteService.get_name_current_target((int) km));
        sb.append("\nдо цели : "+ (RouteService.etermine_distance((int) km)-(int)km));
        return sb.toString();
    }

    static String create_track_bar(int length_bar, float proc,int km) {
        int point = (int) map(0, RouteService.get_sum_distanc(), 0, length_bar, km);
        StringBuilder sb = new StringBuilder();
         sb.append("|");
        for (int i = 0; i < length_bar; i++) {
            if (point == i) sb.append("" + String.format("%.1f", proc) + "\uD83C\uDFC3\u200D\uFE0F");
            if (point < i) sb.append("-");
            if (point > i) sb.append("=");

        }
       // sb.append("|\uD83D\uDD34");
        sb.append(RouteService.get_name_current_target(km));
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

    public static boolean isFinish(){
        if(get_l_to_target() < 0) return true;
        return false;
    }



}
