package com.example.jasper_report.controller;

import com.example.jasper_report.service.PDFservice;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PDFcontroller {

    private final PDFservice pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<Resource> getPDF() throws JRException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                // show in browser without download (inline)
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.inline().build().toString())
                .body(new ByteArrayResource(pdfService.getReport()));
    }
}
