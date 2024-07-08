package org.example.save_to_disk;

import models.PointForStatistic;
import org.example.History;
import org.example.Main;

import java.io.*;
import java.util.HashMap;

public class Save_to_disk_history {

    static String log_histort = "points.txt";
    static String ps_log = "points_for_statistic.txt";
    static public String log_log = "log.txt";

    public static void save_to_disk_points() {
        StringBuilder sb = new StringBuilder();
        sb.append(History.starTimePoint + ",");
        sb.append(Main.km + "\n");
        History.history_add.forEach((key, value) -> sb.append(key + "," + value + "\n"));
        //  String str = String.valueOf(start_point_time);
        String fileName = log_histort;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void read_to_disk_history() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(log_histort));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        int i = 0;
        System.out.println("Read file history");
        while (line != null) {
            parser_line(i, line);
            i++;
           // System.out.println(i + ": " + line);
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();

        }
        //     String everything = sb.toString();
        //  History.print_history();
        //    System.out.println(Main.km + "  "+ History.starTimePoint);
        br.close();

    }


    public static void parser_line(int nl, String line) {
        String[] split_l = line.split(",");
        if (nl == 0) {
            History.starTimePoint = Long.parseLong(split_l[0]);
            Main.km = Long.parseLong(split_l[1]);
        } else {
            History.history_add.put(Integer.valueOf(split_l[0]), Integer.valueOf(split_l[1]));
        }
    }

    public static void addMesToFile(String messs) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(log_log, true)));
            writer.append(messs + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void save_to_disk_points_for_statistoc() {
        StringBuilder sb = new StringBuilder();

        //History.history_statistics.forEach((key, value) -> sb.append(key + "," + value + "\n"));
        for (int i = 0; i < History.history_statistics.size(); i++) {
            PointForStatistic p = History.history_statistics.get(i);
            sb.append(p.toString());
            sb.append("\n");
        }

        //  String str = String.valueOf(start_point_time);
        String fileName = ps_log;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(sb.toString());
            writer.close();
            load_to_disk_points_for_statistoc();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void load_to_disk_points_for_statistoc() {
        try {
            History.history_statistics.clear();
            BufferedReader br = new BufferedReader(new FileReader(ps_log));

            String line = br.readLine();
            int i = 0;

            while (line != null) {
                PointForStatistic p = new PointForStatistic();
                i++;
                String[] split_l = line.split(",");
                p.setId(Long.valueOf(split_l[0]));
                p.setUser_id(Long.valueOf(split_l[1]));
                p.setFirst_name(split_l[2]);
                p.setLast_name(split_l[3]);
                p.setUsername(split_l[4]);
                p.setDataPoint(Long.valueOf(split_l[5]));
                p.setDist(Integer.valueOf(split_l[6]));
                History.history_statistics.add(p);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
