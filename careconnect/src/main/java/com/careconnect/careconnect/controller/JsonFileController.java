package com.careconnect.careconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class JsonFileController {

    @GetMapping("/api/patientInfo")
    public String getPatientInfo() throws IOException {
        // Dette er linjen som leser JSON-filen
        ClassPathResource resource = new ClassPathResource("pasientInfo.json");

        // Kopier filens innhold til en byte-array
        byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());

        // Konverter byte-array til en String og returner den
        return new String(bdata, StandardCharsets.UTF_8);
    }
}
