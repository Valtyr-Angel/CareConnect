package com.careconnect.careconnect.GUI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/journal")
public class PatientJournalController {

    // Endepunkt for å hente en pasientjournal basert på ID
    @GetMapping("/{patientId}")
    public ResponseEntity<Map<String, Object>> getPatientJournal(@PathVariable String patientId) {
        Map<String, Map<String, Object>> allPatients = readPatientJournalFile();
        
        if (allPatients == null || !allPatients.containsKey(patientId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Ingen journal funnet for pasient-ID: " + patientId));
        }
        
        // Returner pasientjournalen hvis den finnes
        return ResponseEntity.ok(allPatients.get(patientId));
    }

    // Endepunkt for testing av API-tilkobling
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("API is running");
    }

    // Privat metode for å lese journalen fra JSON-filen
    private Map<String, Map<String, Object>> readPatientJournalFile() {
        try {
            ClassPathResource resource = new ClassPathResource("patientJournal.json");
            InputStream inputStream = resource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
