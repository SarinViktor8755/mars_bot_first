package org.example.delay_task;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.TimerTask;

public class MyTimerTask  extends TimerTask {
    TelegramBot bot;
    Update mes;

    public MyTimerTask(TelegramBot bot, Update mes) {
        this.bot = bot;
        this.mes = mes;
    }

    @Override
    public void run() {
        // Вот тут делаем всё что нужно (отправляем данные в телеграмм и т.п.)
        bot.execute(new SendMessage(mes.message().chat().id(),mes.message().text()));
       // System.out.println("111111");

    }
}
