package com.careconnect.careconnect;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/journal")
public class PatientJournalController {

    @GetMapping("/{patientId}")
    public ResponseEntity<Map<String, Object>> getPatientJournal(@PathVariable String patientId) {
        Map<String, Object> patientRecord = UpdateJournal.getPatientRecord(patientId);
        if (patientRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientRecord);
    }

    @PutMapping("/{patientId}")
    public String updatePatientJournal(@PathVariable String patientId, @RequestParam String newNote) {
        UpdateJournal.updatePatientRecord(patientId, newNote);
        return "Journal oppdatert for pasient " + patientId;
    }

    @GetMapping("/test")              //kun for testing...
        public String testEndpoint() {
        return "API is running";
}

}
