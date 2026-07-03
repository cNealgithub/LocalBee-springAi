package com.cnealgithub.springAiTut.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel){
        return ChatClient.builder(ollamaChatModel)
//                .defaultSystem("You are an technology expert and will answer tech related questions ")
                //we cam add Advisors or middlewares to do specific tasks and operations like 1) logging and
                // 2) stopping response for sensitive words and many more things like this:
                .defaultAdvisors(new SimpleLoggerAdvisor(), //this helps in logging
                         new SafeGuardAdvisor(List.of("game", "games", "Fraud"))) //this advisor helps in gaurding the llm to respond for any given sensitive word
                .defaultOptions(OllamaChatOptions.builder()
                        .model("llama3.2:latest")
                        .temperature(0.5)

                )
                //.defaultSystem("You are an expert technical curator for a developer marketplace. " +
//                        "When a user requests a code template, " +
//                        "you must return ONLY the raw code block. Do not include greetings, explanations, or markdown formatting outside of the code itself.")
                .build();
    }

}
