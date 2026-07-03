package com.cnealgithub.springAiTut.Service;

import com.cnealgithub.springAiTut.Entity.ResponseStructure;
import org.jspecify.annotations.Nullable;
import reactor.core.publisher.Flux;

public interface AiService {
     String chat(String query);

     ResponseStructure structuredChatResponse(String query);

     String chatTemplate(String uQuery, String subjectMatter);

     Flux<String> streamingChatResponse(String uQuery, String sm);
}
