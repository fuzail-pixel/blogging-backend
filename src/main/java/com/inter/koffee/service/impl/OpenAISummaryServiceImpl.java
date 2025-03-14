package com.inter.koffee.service.impl;

import com.inter.koffee.service.SummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAISummaryServiceImpl implements SummaryService {

    private static final Logger logger = LoggerFactory.getLogger(OpenAISummaryServiceImpl.class);

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String summarizeText(String text) {
        logger.info("Sending request to OpenAI for summarization...");
        logger.info("Input Text: {}", text);

        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openAiApiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
                Map.of("role", "user", "content", "Summarize this: " + text)
        });
        requestBody.put("max_tokens", 100);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

            logger.info("Response received: {}", responseEntity.getBody());

            return extractSummary(responseEntity.getBody());
        } catch (Exception e) {
            logger.error("Error calling OpenAI API", e);
            return "Error: Unable to summarize text due to API failure - " + e.getMessage();
        }
    }

    private String extractSummary(Map<String, Object> responseBody) {
        if (responseBody != null && responseBody.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            if (!choices.isEmpty() && choices.get(0).containsKey("message")) {
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                return message.get("content").toString();
            }
        }
        return "Error: Unable to extract summary from OpenAI response.";
    }
}
