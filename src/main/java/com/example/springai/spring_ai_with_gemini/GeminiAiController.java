package com.example.springai.spring_ai_with_gemini;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/gemini")
@AllArgsConstructor
public class GeminiAiController {

    private final QnAService qnAService;

    @PostMapping("/qna-chat-bot")
    public ResponseEntity<String> qnaChatBotCaller(@RequestBody Map<String, String> payload) {

        String question = payload.get("question");
        String answer = qnAService.getAnswer(question);
        return ResponseEntity.ok(answer);

    }
}
