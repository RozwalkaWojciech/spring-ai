package com.wr.spring.ai.ollama;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final OllamaChatModel chatModel;

    public ChatService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateJoke(String subject) {
        String template = """
                Write joke about {subject}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        promptTemplate.add("subject", subject);

        Prompt prompt = promptTemplate.create();

        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

    public String ask(String query) {
        return chatModel.call(query);
    }

}
