package com.cnealgithub.springAiTut.ETL_Pipeline;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DataTransformerService {

    List<Document> transformDocument(List<Document> extractedDocument);
}
