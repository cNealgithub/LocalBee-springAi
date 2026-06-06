package com.cnealgithub.springAiTut.Controller;

import com.cnealgithub.springAiTut.Entity.ResponseStructure;
import com.cnealgithub.springAiTut.Service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
