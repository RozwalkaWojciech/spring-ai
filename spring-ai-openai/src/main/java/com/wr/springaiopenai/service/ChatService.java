package com.wr.springaiopenai.service;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final OpenAiChatModel chatModel;

    public ChatService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String ask(String query) {
        String template = """
                Masz tutaj czesc instukcji:
                Cennik staje się cennikiem wygasłem w sytuacji, gdy upłynie okres ważności cennika wskazany w
                polach Ważny od oraz Ważny do. Cennik można również wygasić z dniem bieżącym za pomocą opcji
                Zakończ dzisiaj. Taki cennik stanie się wygasły tj. przeterminowany, ale wciąż pozostanie aktywny.
                Aby zmienić status cennika na nieaktywny należy użyć opcji Dezaktywuj.
                
                Na jej podstawie instrukcji odpowiedz na pytanie:
                {query} ?
                Jeżeli pytanie dotyczy czegoś innego niż dana instrukcja to przekaż że nie masz takich danych w swojej instrukcji!
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        promptTemplate.add("query", query);
        Prompt prompt = promptTemplate.create();

        return chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getContent();
    }

}
