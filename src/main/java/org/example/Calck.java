package org.example;

public class Calck {

    public static String main_calck(String text) throws ArrayIndexOutOfBoundsException {

        float[] d = new float[]{5, 10, 15, 21.0975f, 42.195f,50,100};
        text = text.replaceAll(" ", "");
        String[] a = getArreySec(text);
        float time;
        StringBuilder sb = new StringBuilder();
        sb.append("Темп : " + a[0] + " " + a[1] + "\n");

        for (int i = 0; i < d.length; i++) {
            time = sumSec(tempToSec(a[0], a[1]), d[i]);
            if (i == 0) sb.append(((int) d[i]) + "    " + timeToString((long) time) + "\n");
            else
                sb.append(((int) d[i]) + "  " + timeToString((long) time) + "\n");
        }
        return sb.toString();

    }

    private static String timeToString(long secs) {
        long hour = secs / 3600,
                min = secs / 60 % 60,
                sec = secs / 1 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    private static float sumSec(int sec, float dist) {
        return sec * dist;
    }

    private static int tempToSec(String min, String sec) {
        return (Integer.valueOf(min) * 60) + Integer.valueOf(sec);
    }

    private static String[] getArreySec(String text) {
        String number = text.replaceAll("[^0-9\\:]", "");
       // System.out.println(number);
        String[] a = number.split(":");
        return a;
    }

}
