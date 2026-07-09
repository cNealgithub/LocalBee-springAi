package com.cnealgithub.springAiTut.Service;

import com.cnealgithub.springAiTut.Entity.ResponseStructure;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AiService {
     String chat(String query);

     ResponseStructure structuredChatResponse(String query);

     String chatTemplate(String uQuery, String subjectMatter);

     Flux<String> streamingChatResponse(String uQuery, String sm, String conversationId);

     String memoryChat(String query, String conversationId);

     void addDataToVectorDb(List<String> dataForVector);
}
