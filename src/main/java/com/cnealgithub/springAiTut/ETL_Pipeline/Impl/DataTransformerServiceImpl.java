package com.cnealgithub.springAiTut.ETL_Pipeline.Impl;

import com.cnealgithub.springAiTut.ETL_Pipeline.DataTransformerService;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTransformerServiceImpl implements DataTransformerService {
    /**
     * @param extractedDocument
     * @return
     */
    @Override
    public List<Document> transformDocument(List<Document> extractedDocument) {
       TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
               .withChunkSize(250)
               .build();
        return tokenTextSplitter.apply(extractedDocument);
    }
}
