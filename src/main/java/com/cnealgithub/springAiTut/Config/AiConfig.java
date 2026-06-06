package com.cnealgithub.springAiTut.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel chatModel){
        return ChatClient.builder(chatModel)
                //.defaultSystem("You are an expert technical curator for a developer marketplace. " +
//                        "When a user requests a code template, " +
//                        "you must return ONLY the raw code block. Do not include greetings, explanations, or markdown formatting outside of the code itself.")
                .build();
    }

}
