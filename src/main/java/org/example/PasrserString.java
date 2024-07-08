package org.example;

import com.pengrad.telegrambot.model.User;
import org.example.save_to_disk.Save_to_disk_history;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasrserString {

    static public boolean fineKM(String enterS) {
        enterS = enterS.replaceAll(" ", "");
        return enterS.contains("#κμ");
    }

    static public boolean distanc_reader(String enterS,User user ) {
        if(enterS.contains("/rd")) {

            if(!Main.Admins_nik.contains(user.username())) return false;
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(enterS);
            while (matcher.find()) {
                Main.km = Long.parseLong(matcher.group());
            }
            return true;
        }
        return false;
    }


    static public int parsKmString(String enterS) {
        int sum_km = 0;
        enterS = enterS.replaceAll(" ", "");

        Pattern pattern = Pattern.compile("[+]\\d+");
        Matcher matcher = pattern.matcher(enterS);

        while (matcher.find()) {
           // System.out.println(matcher.group());
            sum_km += Integer.valueOf(matcher.group());
        }


        return sum_km;
    }

//    public static String return_statistic(String textMes, User user) {
//        return Statistics_run.create_statisstic();
//    }
}
