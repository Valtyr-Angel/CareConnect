package com.careconnect.careconnect.controller;

import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DoorLockController {

    @PutMapping("/updateDoorLockStatus")
    public Map<String, String> updateDoorLockStatus(@RequestBody Map<String, String> requestBody) {
        Map<String, String> response = new HashMap<>();

        try {
            int newStatus = Integer.parseInt(requestBody.get("DoorLock"));

            // Load resource from classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("IoT-status.json");
            if (inputStream == null) {
                response.put("error", "IoT-status.json not found");
                return response;
            }
            // Read existing JSON or start with a new one if the file is empty
            String jsonString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject jsonObject = jsonString.isEmpty() ? new JSONObject() : new JSONObject(jsonString);

            jsonObject.put("DoorLock", newStatus); // Update the DoorLock status

            // Overwrite the existing file with updated contents
            try (OutputStream outputStream = new FileOutputStream(
                    new File(getClass().getClassLoader().getResource("IoT-status.json").getFile()))) {
                outputStream.write(jsonObject.toString(2).getBytes(StandardCharsets.UTF_8));
            }

            response.put("message", "Door lock status updated");
            return response;

        } catch (NumberFormatException e) {
            response.put("error", "Invalid input: DoorLock must be an integer.");
            return response;

        } catch (IOException | JSONException e) {
            response.put("error", "Error updating file: " + e.getMessage());
            return response;
        }
    }
}