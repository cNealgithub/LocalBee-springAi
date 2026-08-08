package com.cnealgithub.springAiTut.ETL_Pipeline.Impl;

import com.cnealgithub.springAiTut.ETL_Pipeline.DataReaderService;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataReaderServiceImpl implements DataReaderService {

    @Value("classpath:sample_prod_data.json")
    private Resource jsonResource;
    @Value("classpath:nealresymev5upp.pdf")
    private Resource pdfResource;

    @Override
    public List<Document> readDocumentsFromJson() {
        //calling the jsonReader
        JsonReader jsonReader = new JsonReader(this.jsonResource);
        return jsonReader.read();
    }


    @Override
    public List<Document> readDocumentsFromPdf() {
//        creating the pdfPageReader instance
        PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(this.pdfResource,
        PdfDocumentReaderConfig.builder()
                .withPageTopMargin(0)
                .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                        .withNumberOfTopTextLinesToDelete(0)
                        .build())
                .withPagesPerDocument(1)
                .build());
        return pagePdfDocumentReader.read();
    }
}
