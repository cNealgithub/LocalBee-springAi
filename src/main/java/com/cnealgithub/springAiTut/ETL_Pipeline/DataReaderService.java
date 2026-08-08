package com.cnealgithub.springAiTut.ETL_Pipeline;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DataReaderService {

    List<Document> readDocumentsFromJson();

    List<Document> readDocumentsFromPdf();
}
