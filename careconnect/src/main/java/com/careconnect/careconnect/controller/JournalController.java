package com.careconnect.careconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

@RestController
public class JournalController {

    @GetMapping("/api/patientJournal")
    public Map<String, Object> getPatientJournal() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File("src/main/resources/patientJournal.json"), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
