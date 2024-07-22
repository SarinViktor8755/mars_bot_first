package org.example;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import models.PointForStatistic;
import models.Users;
import org.example.delay_task.BanUser;
import org.example.delay_task.DelateMesPhoto;
import org.example.delay_task.MyTimerTask;
import org.example.save_to_disk.Save_to_disk_history;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;


import static org.example.Calck.main_calck;
import static org.example.PasrserString.parsKmString;
import static org.example.RouteService.Distance_Earth_Mars;


public class Main {
    static String BOT_TOKKEN_test = "7039553340:AAHCuowlWMQltfoQNwXOg6MUQ3srtm95N0o";
    static String BOT_TOKKEN = "6552221670:AAFpvfvUmNAEfkhkDUS5nt8HCl3N8906soc";

    static String MY_ID = "2008008852";
    static Long MY_IDl = 6467255873L;
    static Long id_ls = 2008008852L;

    static public int block_lskala = 0;

    static public long km = 0; // ?????????
    static public long km_temp = 0; // ????????? ?????????



    static public ArrayList<String> Admins_nik = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        //Statistics_run.parser_log();

        System.out.println("Start_BOT_RUN");
        System.out.println(System.currentTimeMillis());

        add_admins();
        Save_to_disk_history.load_to_disk_points_for_statistoc();
        //  System.out.println(History.history_statistics);
        //  System.out.println("!!!!!!!!!!!!!!!");
        try {
            Save_to_disk_history.read_to_disk_history();
        } catch (IOException e) {

            History.startTime();
            History.starTimePoint = System.currentTimeMillis();
            System.out.println("START DATA_TIME");
            Save_to_disk_history.save_to_disk_points();
            throw new RuntimeException(e);
        }
        start_distanc(args);

        System.out.println("Distension : " + Main.km);
        TelegramBot bot = new TelegramBot(BOT_TOKKEN_test);
        // TelegramBot bot = new TelegramBot(BOT_TOKKEN_test);
//        System.out.println("main_calck");
//        System.out.println(main_calck("6:00"));
        ////////////////////

        for (int i = 0; i < RouteService.City_Name.length; i++) {
            System.out.println(RouteService.City_Name[i]);
            System.out.println(RouteService.City_Distant[i]);
            System.out.println(RouteService.get_amoun_passed_distance(i));
            RouteService.get_percentage_stage(i);

        }
        System.out.println(RouteService.get_sum_distanc());

//        for (int i = 0; i < 5000 ; i+=100) {
//            System.out.println();
//            if(RouteService.take_stage(i) <800)
//            System.out.println(i+"  "+RouteService.take_stage(i) + "  " + RouteService.City_Name[RouteService.take_stage(i)]);
//            System.out.println(RouteService.get_amoun_passed_distance(i) + "get_amoun_passed_distance");
//            System.out.println(RouteService.etermine_distance(i));
//
//            System.out.println(RouteService.get_percentage_stage(i) + "  proc");
//
//        }
        System.out.println("-----");
//        for (int i = 0; i < 20; i++) {
//            System.out.println(RouteService.etermine_distance(i));
//        }


        bot.setUpdatesListener(updates -> {
            Update mes;

            for (int i = 0; i < updates.size(); i++) {
                try {

                    mes = updates.get(i);


                   // в личку
                    //System.out.println(dd);
                    // if(mes.message().chat().id()!=-1001617066120L) continue;  // ?????????? ??????

                    //  System.out.println(mes);
                    km_temp = km;
//                    bot.execute(new SendMessage("299695014", mes.toString())); //Send_to_IGOR
//                    //    Save_to_disk_history.addMesToFile(mes.toString());
//                    bot.execute(new ForwardMessage("299695014", mes.message().chat().id(), mes.message().messageId()));

                    if (mes.editedMessage() != null) {
                        int m_id = mes.editedMessage().messageId();
                        String new_text = mes.editedMessage().caption();
                        if (mes.editedMessage().caption() != null) new_text = mes.editedMessage().caption();
                        if (!PasrserString.fineKM(new_text)) break;
                        int km_delta = History.make_changes_to_the_message(m_id, new_text);


                        if (km_delta == 0) break;
                        Main.km += km_delta;

                        // System.out.println(mes);
                        int km_in_mes = parsKmString(new_text);
                        bot.execute(new SendMessage(mes.editedMessage().chat().id(), "»справлено ::\n" + MarsSrvice.calculate_percentage(km, km_in_mes)).replyToMessageId(m_id));
                        History.redact_reuslt_from_statistic(mes, km_in_mes);
                        Save_to_disk_history.save_to_disk_points();

                        // System.out.println(History.history_statistics);
                        //  System.out.println(History.history_statistics.size());
                        break;
                        // System.out.println("---");

                    }

                    long td = Main.km;
                    long chatId = mes.message().chat().id();
                    User user = mes.message().from();
                    String text_mes;
                    boolean isPhoto = false;


                    if (mes.message().caption() != null) {
                        text_mes = mes.message().caption();
                        isPhoto = true;

                    } else text_mes = mes.message().text();


                    if (PasrserString.distanc_reader(text_mes, user)) {
                        bot.execute(new SendMessage(chatId, user.username() + " »справил значение  " + td + " на " + Main.km + " "));
                        Save_to_disk_history.save_to_disk_points();
                    }


                    try {
                        if (mes.message().text().contains("/start")) {
//                            String s = "AnimatedSticker.tgs";
//                            SendSticker sendSticker = new SendSticker(mes.message().chat().id(), "AnimatedSticker.tgs");
//                            SendResponse response = bot.execute(sendSticker);
                            // System.out.println(response);
                            System.out.println("START");
                            PointForStatistic p = new PointForStatistic(mes, 0);
                            String nik = "<b>" + Statistics_run.get_name_user(p) + "</b>";
                            String text_hellou = "  нам зашел новый бегун. ѕоприветствуйте все " + nik + " - он будет плюсовать свои тренировки и помогать нам достичь общей цели. " + nik + ", добро пожаловать. \uD83D\uDC4D\uD83D\uDC4D";


                            Path imagePath = Paths.get("11.gif");
                            byte[] imageBytes = null;
                            try {
                                imageBytes = Files.readAllBytes(imagePath);
                                bot.execute(new SendAnimation(mes.message().chat().id(), imageBytes).caption(text_hellou).parseMode(ParseMode.HTML));
                                // bot.execute(new SendPhoto(mes.message().chat().id(), imageBytes).caption(text_hellou).parseMode(ParseMode.HTML));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                            //  bot.execute(new SendMessage(chatId, "  " + text_hellou).parseMode(ParseMode.HTML));

//                            delMess(mes,bot);
//                            SendResponse r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic()).disableNotification(true));
//                            start_delate_mes(bot, r);
//
//                            r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic_moon()).disableNotification(true));
//                            start_delate_mes(bot, r);
//
//                            r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic_week()).disableNotification(true));
//                            start_delate_mes(bot, r);
//
//                            r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic_day()).disableNotification(true));
//                            start_delate_mes(bot, r);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }


                    try {
                        if (mes.message().text().contains("/statistics")) {
                            System.out.println("statistics");
                            delMess(mes, bot); // удаление команды 
                            SendResponse r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic()).disableNotification(true));
                            start_delate_mes(bot, r);

                            r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic_moon()).disableNotification(true));
                            start_delate_mes(bot, r);

                            r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic_week()).disableNotification(true));
                            start_delate_mes(bot, r);

                            r = bot.execute(new SendMessage(chatId, Statistics_run.create_statisstic_day()).disableNotification(true));
                            start_delate_mes(bot, r);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }


                    try {
                        if (mes.message().text().contains("/history")) {
                            //  delMess(mes,bot);
                            SendResponse r = bot.execute(new SendMessage(chatId, Statistics_run.getHistory()));
                            start_delate_mes(bot, r);

                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    //    getHistory()

                 //   System.out.println("CHECK");
                    try {
                        if (mes.message().text().toLowerCase().contains("/c".toLowerCase())) {
                            delMess(mes, bot);
                            SendResponse r = bot.execute(new SendMessage(chatId, Calck.main_calck(mes.message().text())));
                            start_delate_mes(bot, r);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        SendResponse r = bot.execute(new SendMessage(chatId, "неверный формат записи").disableNotification(true));
                        start_delate_mes(bot, r);
                    }

                    try {
                        if (mes.message().text().toLowerCase().contains("/print_events".toLowerCase())) {
                            delMess(mes, bot);
                            SendResponse r = bot.execute(new SendMessage(chatId, Events.print()).parseMode(ParseMode.HTML).disableWebPagePreview(true).disableNotification(true));
                            start_delate_mes(bot, r);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
//                        SendResponse r =  bot.execute(new SendMessage(chatId, Events.print()));
//                        start_delate_mes(bot, r);
                    }


                    if (PasrserString.fineKM(text_mes)) {

                        if (!isPhoto) {
                            give_my_photo(chatId, bot, mes.message().messageId());
                        } else {
                            ask_km(text_mes, bot, chatId, user, mes.message().messageId());
                            History.add_reuslt_from_statistic(mes, parsKmString(text_mes));

                            start_kick_user(bot,mes);
                        }
//                        System.out.println(km);
//                        System.out.println(Distance_Earth_Mars);
                        if (km > RouteService.get_sum_distanc()) send_photo(bot, "33333.jpg", mes.message().chat().id());
                    }
                    //check_1000


                    if (check_thousand()) {
                        send_photo(bot, "22.jpg", mes.message().chat().id());
                    }

                    if (check_City()) {
                        String file = RouteService.take_stage(km)+"b.jpg";
                        send_photo(bot, file, mes.message().chat().id());
                    }



                    //skala
                    //  check_block(mes, bot);
                    // lediskala_Del(bot, mes);
                    //   System.out.println(mes);
                } catch (NullPointerException | NumberFormatException e) {
                    e.printStackTrace();
                }

            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;

        }, e -> {
            if (e.response() != null) {
                // got bad response from telegram
                e.response().errorCode();
                e.response().description();
            } else {
                // probably network error
                e.printStackTrace();
            }
        });


    }

    ///// ???????? ???????? ????????
    static public void ask_km(String text_mes, TelegramBot bot, long chatId, User user, Integer mes_id) {
        int user_run = (int) parsKmString(text_mes);
        if (user_run <= 0) return;
        km += Long.valueOf(user_run);
        bot.execute(new SendMessage(chatId, MarsSrvice.calculate_percentage(km, user_run)).replyToMessageId(mes_id));


        Users.add_km_for_user(user_run, user);
        History.history_add.put(mes_id, user_run);
        Save_to_disk_history.save_to_disk_points();

    }

    //// ???????? ???????? ????
    static public void give_my_photo(long chatId, TelegramBot bot, int mes_id) {
        SendResponse ov = bot.execute(new SendMessage(chatId, "ƒовер€й но провер€й: приложи фото или скрин трека к своему сообщению \uD83D\uDE09").replyToMessageId(mes_id));
        //  System.out.println(ov);
        start_delate_mes(bot, ov);

    }

    static public void start_distanc(String[] args) {
        //System.out.println(args[0]);
        try {
            km = Long.parseLong(args[0]);
            Save_to_disk_history.save_to_disk_points();
        } catch (ArrayIndexOutOfBoundsException e) {
            //km = 0;
            e.printStackTrace();
        }

    }

    static public void send_photo(TelegramBot bot, String filename, Long chat_id) {  // ????????? ?????
        try {
            Path imagePath = Paths.get(filename);
            byte[] imageBytes = Files.readAllBytes(imagePath);
            bot.execute(new SendPhoto(chat_id, imageBytes));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static public boolean check_thousand() {
        if ((km / 1000) != (km_temp / 1000)) return true;
        return false;
    }

    static public boolean check_City() {
        if (RouteService.take_stage(km) != RouteService.take_stage(km_temp)) return true;
        return false;
    }


    static public void lediskala_Del(TelegramBot bot, Update mes) {
        System.out.println(block_lskala);
        int id_my = 299695014;
        //    System.out.println(mes.editedMessage().text()==null);
        // if (mes.editedMessage().caption() != null) return;
        //  System.out.println(block_lskala);
        if (block_lskala == 0) return;
        if (!mes.message().from().username().equals("lediskala")) return;


        if (mes.message().from().id().equals(MY_IDl)) {

            String chatId = String.valueOf(mes.message().chat().id());
            Integer messageId = mes.message().messageId();
            String text = mes.message().text();

            if (block_lskala == 2) {
                StringBuilder sb = new StringBuilder();
                sb.append("\uD83C\uDD7B\uD83C\uDD74\uD83C\uDD73\uD83C\uDD78\uD83C\uDD82\uD83C\uDD7A\uD83C\uDD70\uD83C\uDD7B\uD83C\uDD70\n");
                int kol = text.length() / 3;
                if (kol < 1) kol = 2;
                for (int i = 0; i < kol; i++) {
                    if (randomBoolean(.5f)) sb.append("??? ");
                    else sb.append("??? \uD83E\uDD84\uD83E\uDD84\uD83E\uDD84");
                }
                delMess(mes, bot);
                bot.execute(new SendMessage(chatId, sb.toString()));
            }
        } else System.out.println("NOT");


    }


    static public void check_block(Update mes, TelegramBot bot) {
        if (mes.message().from().id().equals(MY_IDl)) return;
        String text = mes.message().text();
        if (text.equals("/ls0")) {
            block_lskala = 0;
            delMess(mes, bot);
        }
        if (text.equals("/ls1")) {
            block_lskala = 1;
            delMess(mes, bot);
        }
        if (text.equals("/ls2")) {
            block_lskala = 2;
            delMess(mes, bot);
        }
    }

    static private void delMess(Update mes, TelegramBot bot) {
        String chatId = String.valueOf(mes.message().chat().id());
        Integer messageId = mes.message().messageId();
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        bot.execute(deleteMessage);
    }

    static private void start_statisic(TelegramBot bot, Update mes) {
        MyTimerTask mMyTimerTask = new MyTimerTask(bot, mes);
        Statistics_run.mTimer.schedule(mMyTimerTask, 1000, 500);
    }

    static private void start_delate_mes(TelegramBot bot, SendResponse ov) { // ?????? ????????? ????? ?????
        DelateMesPhoto delateMesPhoto = new DelateMesPhoto(bot, ov);
        Timer timer = new Timer();
        timer.schedule(delateMesPhoto, Constants.MINUTE * 2);
    }

    static private void start_delate_mes(TelegramBot bot, SendResponse ov, int min) { // ?????? ????????? ????? ?????
        DelateMesPhoto delateMesPhoto = new DelateMesPhoto(bot, ov);
        Timer timer = new Timer();
        timer.schedule(delateMesPhoto, Constants.MINUTE * min);
    }

    static private void start_kick_user(TelegramBot bot, Update update){
        BanUser banUser = new BanUser(bot,update.message().chat().id(),id_ls);
        Timer timer = new Timer();
        timer.schedule(banUser, Constants.HOUR * MarsSrvice.random(5,12));
    }

    static public void delate_mess(TelegramBot bot, Update mes) {
        // mes.deletedBusinessMessages();
        String chatId = String.valueOf(mes.message().chat().id());
        Integer messageId = mes.message().messageId();
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        bot.execute(deleteMessage);
    }

    static public void make_changes(TelegramBot bot, Update mes) {
        //  mes.deletedBusinessMessages();
        String chatIdd = String.valueOf(mes.message().chat().id());
        Integer messageId = mes.message().messageId();
        DeleteMessage deleteMessage = new DeleteMessage(chatIdd, messageId);
        bot.execute(deleteMessage);
    }


    static public boolean check_photo(Update mes) {
        if (mes.message().photo() == null) return false;
        else return true;
    }


    static public void add_admins() {
        Admins_nik = new ArrayList<>();
        Admins_nik.add("Vity55");
        Admins_nik.add("Firefighter55");
        Admins_nik.add("Anton_Kipchoge");
    }

    static public boolean randomBoolean(float chance) {
        return random() < chance;
    }

    static public float random() {
        Random random = new Random();
        return random.nextFloat();
    }


}