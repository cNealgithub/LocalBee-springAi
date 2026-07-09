package com.cnealgithub.springAiTut.Config;

import com.cnealgithub.springAiTut.Advisors.CustomTokenCountAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiConfig {

    private Logger logger = LoggerFactory.getLogger(AiConfig.class);

//    we can craete our own implementation for jdbcChatMemoryRepo by creating a bean for ChatMemory
//    as ChatMemory uses the chatMemoryRepo that is implemented by jdbcChatMemoryRepo we can pass jdbcChatMemoryRepo
//    as method parameter and use builder of chatMemory and define parameters like max_message and ets for jdbcchatMemoeyRepo
    ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
        return MessageWindowChatMemory
                .builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(15)
                .build();
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel, ChatMemory chatMemory){

        //we will create a chatMemory advisor using builder
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatMemory)
                .build();
        this.logger.info("chatMemory impl class " + chatMemory.getClass().getName());

        return ChatClient.builder(ollamaChatModel)
//                .defaultSystem("You are an technology expert and will answer tech related questions ")
                //we cam add Advisors or middlewares to do specific tasks and operations like 1) logging and
                // 2) stopping response for sensitive words and many more things like this:
                .defaultAdvisors(messageChatMemoryAdvisor, //for chat memory, tomake llm remeber
                         new CustomTokenCountAdvisor(),
//                         new SimpleLoggerAdvisor(), //this helps in logging
                         new SafeGuardAdvisor(List.of("game", "games", "Fraud"))) //this advisor helps in gaurding the llm to respond for any given sensitive word
                .defaultOptions(OllamaChatOptions.builder()
                        .model("llama3.2:latest")
                        .maxTokens(200)
                        .temperature(0.5)

                )
                //.defaultSystem("You are an expert technical curator for a developer marketplace. " +
//                        "When a user requests a code template, " +
//                        "you must return ONLY the raw code block. Do not include greetings, explanations, or markdown formatting outside of the code itself.")
                .build();
    }

}
