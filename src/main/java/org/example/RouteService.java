package org.example;

import java.util.Arrays;

public class RouteService {
    static final String ballEmoji = "\uD83D\uDC4D";
    static public final float Distance_Earth_Mars = 54_600; // ???? ?????????

    static public String City_Name[] = {"������", "������", "�����", "������", "���-����", "������", "���-��������", "�����", "�����", "������", "��������", "����", "�������", "�����", "���", "���������", "��������", "���-��-�������", "������-�����", "��������", "����", "������", "���-��������", "����"};
    static public int City_Distant[] = {2700, 1800, 1050, 350, 5600, 1300, 2800, 8800, 2100, 8500, 11000, 8000, 1300, 600, 1100, 850, 1000, 7900, 2000, 1400, 2500, 4200, 2500, 9500 + 2500};

    public static int get_sum_distanc() {
        return Arrays.stream(City_Distant).sum();
    }


    public static String get_name_current_target(int distantion){ // ����� ��� ����
        return City_Name[take_stage(distantion)];
    }

    public static String get_name_current_target(float distantion){ // ����� ��� ����
        return City_Name[take_stage((int)distantion)];
    }


    public static int take_stage(float �urrent_distance){
        return take_stage((int)�urrent_distance);
    }
    public static int take_stage(int �urrent_distance) {// ������� ����
        int temp_lith = 0;
        int result = 0;
        for (int i = 0; i < City_Distant.length; i++) {
            temp_lith += City_Distant[i];
            if (�urrent_distance >= get_sum_distanc()) result = 999;
            if (�urrent_distance < temp_lith) {
                result = i;
                break;
            }
        }
        return result;
    }

//    public static int etermine_distance(int etap) {// ��������� ��� �����
//        int l = 0;
//        for (int i = 0; i < etap + 1; i++) {
//            l += City_Distant[i];
//        }
//        return l;
//    }

    public static int etermine_distance(int km) {// ��������� ��� �����
        int l = 0;
        int etap = take_stage(km);
        for (int i = 0; i < etap + 1; i++) {
            l += City_Distant[i];
        }
        return l;
    }


    public static float get_percentage_stage(int �urrent_distance){
        System.out.println(etermine_distance(�urrent_distance));
        float a = etermine_distance(�urrent_distance);
        float b = (�urrent_distance - etermine_distance(�urrent_distance)) * -1;

        return MarsSrvice.map(0,a,100,0 ,b);
    }

}
