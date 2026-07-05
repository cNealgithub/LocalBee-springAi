package com.cnealgithub.springAiTut.Controller;

import com.cnealgithub.springAiTut.Entity.ResponseStructure;
import com.cnealgithub.springAiTut.Service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiChatController {

    private final AiService aiService;

   @GetMapping("/chat")
   public ResponseEntity<String> chat(@RequestParam(value = "q") String query){
    return ResponseEntity.ok(aiService.chat(query));
   }
   @GetMapping("/Structure_chat")
    public ResponseEntity<ResponseStructure> structuredChat(@RequestParam(value = "q") String query){
       System.out.println("Structure chat controller is working");
    return ResponseEntity.ok(aiService.structuredChatResponse(query));
   }
   @GetMapping("/ChatTemplate")
    public ResponseEntity<String> chat_template(@RequestParam(value = "uQuery")String uQuery,
                                                @RequestParam(value = "sm")String subjectMatter){
       return ResponseEntity.ok(aiService.chatTemplate(uQuery, subjectMatter));
   }

   @GetMapping("/stream-chat")
    public ResponseEntity<Flux<String>> streamingChat(@RequestParam(value = "uQuery") String uQuery,
                                                      @RequestParam(value = "sm") String sm,
                                                      @RequestParam(value = "chatId", required = false) String chatId){

       String conversationId = (chatId != null && !chatId.isEmpty()) ? chatId : UUID.randomUUID().toString();
       return ResponseEntity.ok(aiService.streamingChatResponse(uQuery, sm, conversationId));
   }
   @GetMapping("/memoryBasedChat")
    public ResponseEntity<String> memoryChat(@RequestParam(value = "query") String query,
                                                   @RequestParam(value = "chatId", required = false) String chatId){
       String conversationId = (chatId != null && !chatId.isEmpty()) ? chatId : UUID.randomUUID().toString();
       return ResponseEntity.ok()
               .header("convoId: " , conversationId)
               .body(aiService.memoryChat(query, conversationId));
   }
}
