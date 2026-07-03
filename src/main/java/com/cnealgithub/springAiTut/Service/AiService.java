package com.cnealgithub.springAiTut.Service;

import com.cnealgithub.springAiTut.Entity.ResponseStructure;
import org.jspecify.annotations.Nullable;

public interface AiService {
     String chat(String query);

     ResponseStructure structuredChatResponse(String query);

     String chatTemplate(String uQuery, String subjectMatter);
}
