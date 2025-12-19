package com.example.languageteacherbot.config;

import com.example.languageteacherbot.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ShutdownConfig implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private TelegramService telegramService;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        telegramService.stopPolling();
    }
}