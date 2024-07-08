package org.example.delay_task;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.response.SendResponse;
import java.util.TimerTask;

public class DelateMesPhoto  extends TimerTask {
    TelegramBot bot;


    Long chat_id;
    int mes_id;


    public DelateMesPhoto(TelegramBot bot, SendResponse sendResponse) {
        this.bot = bot;
        this.chat_id = sendResponse.message().chat().id();
        this.mes_id = sendResponse.message().messageId();
    }

    @Override
    public void run() {
        DeleteMessage deleteMessage = new DeleteMessage(chat_id, mes_id);
        bot.execute(deleteMessage);


    }
}