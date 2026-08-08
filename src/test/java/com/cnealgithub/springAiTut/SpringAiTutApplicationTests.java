package com.cnealgithub.springAiTut;

import com.cnealgithub.springAiTut.ETL_Pipeline.DataReaderService;
import com.cnealgithub.springAiTut.ETL_Pipeline.DataTransformerService;
import com.cnealgithub.springAiTut.ETL_Pipeline.DataWriterService;
import com.cnealgithub.springAiTut.Util.DumpDataUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    @Autowired
    private DataReaderService dataReaderService;
    @Autowired
    private DataTransformerService dataTransformerService;
    @Autowired
    private DataWriterService dataWriterService;


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

    // ETL:: DocumentReader :: JsonReader test
    @Test
    void jsonLoaderTest(){
        System.out.println("json load test starts");
        var documents = dataReaderService.readDocumentsFromJson();
        System.out.println(documents.size());
        documents.forEach(item->
                System.out.println(item.getFormattedContent()));
    }

    // ETL:: DocumentReader :: pagePdfReader test
    @Test
    void pagePdfReaderTest(){
        System.out.println("pagePdfReader test started");
        var documents = dataReaderService.readDocumentsFromPdf();
        documents.forEach(doc-> {
            System.out.println(doc.getFormattedContent());
            System.out.println("--------------");
        });
        System.out.println("now chunking the extracted data from ODF");
        List<Document> transformedDocuments =  dataTransformerService.transformDocument(documents);
        System.out.println(transformedDocuments.size());
        transformedDocuments.forEach(document -> {
            System.out.println(document.getFormattedContent());
            System.out.println("------------------");
        });
    }
    // ETL:: DocumentReader :: saving the transformed document to vectorStore
    @Test
    void writeToVectorStore(){
        System.out.println("pagePdfReader test started :: dataWriter Test");
        var documents = dataReaderService.readDocumentsFromPdf();
        documents.forEach(doc-> {
            System.out.println(doc.getFormattedContent());
            System.out.println("--------------");
        });
        System.out.println("now chunking the extracted data from pDF :: dataWriter Test");
        List<Document> transformedDocuments =  dataTransformerService.transformDocument(documents);
        System.out.println(transformedDocuments.size());
        transformedDocuments.forEach(document -> {
            System.out.println(document.getFormattedContent());
            System.out.println("------------------");
        });
        dataWriterService.loadToVectorDb(transformedDocuments);
    }
}
