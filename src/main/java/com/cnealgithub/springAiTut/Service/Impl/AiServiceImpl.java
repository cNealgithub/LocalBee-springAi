package com.cnealgithub.springAiTut.Service.Impl;

import com.cnealgithub.springAiTut.Entity.ResponseStructure;
import com.cnealgithub.springAiTut.Service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

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
        String systemPrompt = "You are an expert technical curator for a developer marketplace. " +
                "When a user requests a" +
                " code template, you must return ONLY the raw code block. Do not include greetings, explanations, or markdown formatting outside of the code itself." +
                ", Now reply for this query :{query} ";
        try {
            //we will now try to do prompt parsing for better results
            //ex if i want llm to give codes inly in java then i must parse the user prompt with some system
            //prompt that will guide the llm to use java language , hence creating an efficient and good prompt
            response = ollamaChatClient
                    .prompt()
                    // using
                    .user(u-> u.text(systemPrompt).param("query", query))
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

//    PROMPT PARSING TECHNIQUES
    @Override
    public String chatTemplate(String uQuery, String subjectMatter) {
        String rawTemplate = "what is { uQuery} , answer in points.";
        PromptTemplate promptTemplate = PromptTemplate.builder().template(rawTemplate).build();
        String renderedMessage = promptTemplate.render(Map.of(
                "uQuery", uQuery
        ));
        // we can also add role specific templates:-
        SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder()
                .template("you are an expert in {subjectMatter} and always give real life examples ")
                .build();
        var systemRolePrompt = systemPromptTemplate.render(Map.of(
                "subjectMatter", subjectMatter
        ));
        Prompt prompt = new Prompt(renderedMessage);
        String response = ollamaChatClient
                .prompt(prompt)
                .system(systemRolePrompt)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
        return response;
    }

    @Override
    public Flux<String> streamingChatResponse(String uQuery, String sm, String conversationId) {

//        String conversationIdKey = "org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY";
        String rawUserQuery = "what is {uQuery} , answer in points";
        PromptTemplate promptTemplate = PromptTemplate.builder().template(rawUserQuery).build();
        String renderedMessage = promptTemplate.render(Map.of(
                "uQuery", uQuery
        ));
        String rawSystemMessage = "you are an expert in {subjectMatter} and always give real life examples";
        SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder().template(rawSystemMessage).build();
        String renderedSystemMessage = systemPromptTemplate.render(Map.of(
                "subjectMatter", sm
        ));
        return this.ollamaChatClient
                .prompt()
                .system(renderedSystemMessage)
                .user(renderedMessage)
                .advisors(advisorSpec -> advisorSpec.param("chat_memory_conversation_id", conversationId))
                .stream()
                .content()
                //added contextWrite to write he conversationId in context, as in streaming response the conversationId gets lost if not added explicitly to context
                .contextWrite(context -> context.put("chat_memory_conversation_id", conversationId));
    }

    @Override
    public String memoryChat(String query, String conversationId) {


        return this.ollamaChatClient
                .prompt(query)
                .advisors(advisorSpec -> advisorSpec.param("chat_memory_conversation_id", conversationId))
                .call()
                .content();
    }
}
