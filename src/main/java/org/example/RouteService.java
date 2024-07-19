package org.example;

import java.util.Arrays;

public class RouteService {
    static final String ballEmoji = "\uD83D\uDC4D";
    static public final float Distance_Earth_Mars = 54_600; // ???? ?????????

    static public String City_Name[] = {"Москва", "Берлин", "Париж", "Лондон", "Нью-Йорк", "Чикаго", "Лос-Анджелес", "Токио", "Пекин", "Сидней", "Кейптаун", "Каир", "Стамбул", "Афины", "Рим", "Барселона", "Лиссабон", "Рио-де-Жанейро", "Буэнос-Айрес", "Сантьяго", "Лима", "Мехико", "Лос-Анджелес", "Омск"};
    static public int City_Distant[] = {2700, 1800, 1050, 350, 5600, 1300, 2800, 8800, 2100, 8500, 11000, 8000, 1300, 600, 1100, 850, 1000, 7900, 2000, 1400, 2500, 4200, 2500, 9500 + 2500};

    public static int get_sum_distanc() {
        return Arrays.stream(City_Distant).sum();
    }

    public static int take_stage(int сurrent_distance) {
        int temp_lith = 0;
        int result = 0;
        for (int i = 0; i < City_Distant.length; i++) {
            temp_lith += City_Distant[i];
            if (сurrent_distance >= get_sum_distanc()) result = 999;
            if (сurrent_distance < temp_lith) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static int etermine_distance(int etap) {// растояние для этапа
        int l = 0;
        for (int i = 0; i < etap + 1; i++) {
            l += City_Distant[i];
        }

        return l;
    }

}
