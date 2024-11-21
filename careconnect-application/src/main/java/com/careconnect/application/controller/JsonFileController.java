package com.careconnect.application.controller;

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
        ClassPathResource resource = new ClassPathResource("pasientInfo.json");
        byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bdata, StandardCharsets.UTF_8);
    }
}