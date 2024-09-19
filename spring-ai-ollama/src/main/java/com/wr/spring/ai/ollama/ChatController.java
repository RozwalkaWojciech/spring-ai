package com.wr.spring.ai.ollama;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    public final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/joke")
    public String getJoke(@RequestParam(value = "subject", defaultValue = "Chuck Norris") String subject) {
        return chatService.generateJoke(subject);
    }

    @GetMapping("/ask")
    public String ask(@RequestParam(value = "query") String query) {
        return chatService.ask(query);
    }

}
