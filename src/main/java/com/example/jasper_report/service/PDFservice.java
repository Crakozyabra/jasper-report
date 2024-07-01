package com.example.jasper_report.service;

import com.example.jasper_report.model.Animal;
import com.example.jasper_report.model.Gender;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PDFservice {

    private List<Animal> animals;

    public byte[] getReport() throws JRException {

        // get template
        InputStream template = getClass().getResourceAsStream("/template.jrxml");

        // compile template
        JasperReport jasperReport = JasperCompileManager.compileReport(template);

        Map<String, Object> parameters = new HashMap<>();

        // add animal collection
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(animals);
        parameters.put("CollectionBeanParam", dataSource);

        // need to pass new JREmptyDataSource(), most important
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        // convert to type PDF in byte representation
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    /**
     * init animals
     */
    @PostConstruct
    private void init() {
        animals = IntStream.iterate(0, i -> i + 1)
                .limit(10)
                .mapToObj(i -> new Animal(++i, "Barsik" + i, Math.random() > 0.5 ? Gender.M.name() : Gender.F.name()))
                .toList();
    }
}
