package com.example.languageteacherbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceIntegration {

    @Value("${admin.service.url:http://localhost:8081}")
    private String adminServiceUrl;

    private final RestTemplate restTemplate;

    public AdminServiceIntegration() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Отправляет сообщение в админ-сервис для сохранения истории
     */
    public void sendMessageToAdminService(Long chatId, String messageText, boolean isFromUser) {
        try {
            String url = adminServiceUrl + "/api/bot/save-message";
            
            Map<String, Object> request = new HashMap<>();
            request.put("chatId", chatId);
            request.put("messageText", messageText);
            request.put("isFromUser", isFromUser);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

            // Отправляем асинхронно, чтобы не блокировать основной поток
            new Thread(() -> {
                try {
                    restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                    String sender = isFromUser ? "пользователя" : "бота";
                    System.out.println("✅ Сообщение от " + sender + " отправлено в админ-сервис: " + messageText.substring(0, Math.min(50, messageText.length())));
                } catch (Exception e) {
                    System.err.println("❌ Ошибка отправки в админ-сервис: " + e.getMessage());
                }
            }).start();

        } catch (Exception e) {
            System.err.println("Ошибка интеграции с админ-сервисом: " + e.getMessage());
        }
    }

    /**
     * Проверяет доступность админ-сервиса
     */
    public boolean isAdminServiceAvailable() {
        try {
            String url = adminServiceUrl + "/api/bot/health";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }
}
