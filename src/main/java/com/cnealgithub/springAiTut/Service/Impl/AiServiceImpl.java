package com.cnealgithub.springAiTut.Service.Impl;

import com.cnealgithub.springAiTut.Entity.ResponseStructure;
import com.cnealgithub.springAiTut.Service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final ChatClient ollamaChatClient;

    @Override
    public String chat(String query) {
        String response;
        // second: we use the Fluent Api concept to get industry garde setup and response
        Prompt prompt1 = new Prompt(query, OllamaChatOptions.builder()
                .model("llama3.2:latest")
                .temperature(0.3).build());
        String systemPromptParser = "You are an expert technical curator for a developer marketplace. " +
                "When a user requests a" +
                " code template, you must return ONLY the raw code block. Do not include greetings, explanations, or markdown formatting outside of the code itself." +
                ", Now reply for this query :{query} ";
        try {
            //we will now try to do prompt parsing for better results
            //ex if i want llm to give codes inly in java then i must parse the user prompt with some system
            //prompt that will guide the llm to use java language , hence creating an effeicint and good prompt
            response = ollamaChatClient
                    .prompt()
                    // using
                    .user(u-> u.text(systemPromptParser).param("query", query))
                    .call()
                    .chatResponse()
                    .getResult()
                    .getOutput()
                    .getText();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseStructure structuredChatResponse(String query) {
        String systemPrompt = " You are a structured answering assistant. \n" +
                "Always respond ONLY in valid JSON. \n" +
                "Do not include any text outside of JSON. \n" +
                "Use exactly these keys: \"question\" and \"answer\". \n" +
                "Ensure the JSON is properly formatted with matching braces and quotes.\n";
        // we are using an java class(entity) and mapping the response of the LLM to the class attributes
        ResponseStructure response;
        response = ollamaChatClient
                .prompt(query)
                .system(systemPrompt)
                .call()
                .entity(ResponseStructure.class);
        System.out.println(response);
        return response;
    }
}
