package com.cnealgithub.springAiTut;

import com.cnealgithub.springAiTut.Service.AiService;
import com.cnealgithub.springAiTut.Util.DumpDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=org.springframework.ai.model.chat.memory.repository.jdbc.autoconfigure.JdbcChatMemoryRepositoryAutoConfiguration"
})
class SpringAiTutApplicationTests {
    static {
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("GMT+5:30"));
        System.setProperty("user.timezone", "GMT+5:30");
    }

//    @Autowired
//    private AiService aiService;
    @Autowired
    private VectorStore vectorStore;

	@Test
	void contextLoads() {
	}

    @Test
    void dumpDataToVectorDbTest(){
        List<String> dataList = DumpDataUtil.getData();
        List<Document> docDataList = dataList.stream().map(Document::new).toList();
        System.out.println("Adding data to vector DB");
        this.vectorStore.add(docDataList);
        System.out.println("Data successfully added to vector DB");
    }
}
