package com.example.springai.spring_ai_with_gemini;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnAService {

    private final WebClient webClient;
    // Access   to APIKEY  and URL
    @Value("${gemini.ai.api-url}")
    private String geminiApiUrl;
    @Value("${gemini.ai.api-key}")
    private String geminiApikey;

    public QnAService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    /**
     * To  Build Request body
     * Call  Gemini API
     * And send response back
     *
     * @param question
     * @return {
     * "contents": [
     * {
     * "parts": [
     * {
     * "text": "Explain how AI works"
     * }
     * ]
     * }
     * ]
     * }
     */
    public String getAnswer(String question) {

        // Construct  the request payload
        Map<String, Object> requestBody = Map.of("contents", new Object[]{
                Map.of("parts", new Object[]{
                        Map.of("text", question)
                })
        });

        // Make API  Call
        String response = webClient
                .post()
                .uri(geminiApiUrl + geminiApikey)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)  // reactive wrapper
                .block();

        // Return response
        return response;
    }
}
