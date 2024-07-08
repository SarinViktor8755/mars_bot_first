package org.example;


import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import models.PointForStatistic;
import org.example.save_to_disk.Save_to_disk_history;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import static org.example.MarsSrvice.get_l_to_target;

public class History { //������� ������������ ��
    static public HashMap<Integer, Integer> history_add = new HashMap<>();// ������� �����
    //   static public HashMap<Integer, Integer> history_statistics = new HashMap<>();// ������� ����� ��� ����������
    static public ArrayList<PointForStatistic> history_statistics = new ArrayList<>();// ������� ����� ��� ����������

    static public Long starTimePoint;

    static public void startTime() {

    //    System.out.println("������� ����: " + starTimePoint);
    }

    static public long startDeltaTime() {
        return System.currentTimeMillis() - starTimePoint;
    }

    static public int getSumKM() {
        int sum = 0;
        for (HashMap.Entry<Integer, Integer> entry : history_add.entrySet()) {
            sum += entry.getValue();
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + value);
        }
        return sum;
    }

    static public double getSpeed() { // ��������� ������ ��������
        double speed = getSumKM() / (History.startDeltaTime() / (double) Constants.HOUR);
        return speed;
    }

    static public String get_ve_to_marsa() {
        //      ���� / ��������
        if (getSpeed() == 0.0) return "��� ������";
        double v = get_l_to_target() / getSpeed();
        long ost_v_ch = ((long) (v * Constants.HOUR)) + System.currentTimeMillis();
        String pattern = "dd MMMM yy" + "�.";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date_f = simpleDateFormat.format(new Date(ost_v_ch));
        return date_f;
    }


    static public void print_history() {
     //   System.out.println(history_add.size());
        history_add.forEach((key, value) -> System.out.println(key + " " + value));
    }

    static public Integer make_changes_to_the_message(int mass_id, String text) {// ���������� ��������� � ���������
        int sz = 0;// ������ ��������
        int nz = PasrserString.parsKmString(text);// ����� ��������

        if (history_add.get(mass_id) != null) sz = history_add.get(mass_id);
        history_add.put(mass_id, nz);


        return (nz - sz);
    }

    /////////////////////////////////////

    static public void add_reuslt_from_statistic(Update mes, int km) {
        PointForStatistic p = new PointForStatistic(mes, km);
        History.history_statistics.add(p);
        Save_to_disk_history.save_to_disk_points_for_statistoc();
      //  System.out.println(History.history_statistics);


    }


    public static void redact_reuslt_from_statistic(Update mes, int kmDelta) {
        int mes_id = mes.editedMessage().messageId();
        for (int i = 0; i < History.history_statistics.size(); i++) {
            PointForStatistic p = History.history_statistics.get(i);
            if(mes_id == p.getId()){
                p.setDist(kmDelta);
                Save_to_disk_history.save_to_disk_points_for_statistoc();
                return;
            }
        }
    }




}
