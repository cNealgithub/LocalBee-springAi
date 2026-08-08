package com.cnealgithub.springAiTut.ETL_Pipeline.Impl;

import com.cnealgithub.springAiTut.ETL_Pipeline.DataWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataWriterServiceImpl implements DataWriterService {

    private final VectorStore vectorStore;

    /**
     * @param transformedDocument
     * @return
     */
    @Override
    public String loadToVectorDb(List<Document> transformedDocument) {
        vectorStore.add(transformedDocument);
        return "Document Saved To Vector DB";
    }
}
