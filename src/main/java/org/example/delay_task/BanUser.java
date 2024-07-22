package org.example.delay_task;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.KickChatMember;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.TimerTask;

public class BanUser extends TimerTask {
    TelegramBot bot;


    Long chatId;
    Long userId;


    public BanUser(TelegramBot bot,Long chatId,Long userId) {
        this.bot = bot;
        this.chatId = chatId;
        this.userId = userId;
      //  System.out.println("111");

    }

    @Override
    public void run() {
      // System.out.println("222");
        bot.execute(new KickChatMember(chatId, userId));
    }
}