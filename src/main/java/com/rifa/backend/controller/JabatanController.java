package com.rifa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rifa.backend.model.Jabatan;
import com.rifa.backend.service.JabatanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/jabatan")
public class JabatanController {

    @Autowired
    private JabatanService jabatanService;

    // Create Jabatan
    @PostMapping
    public ResponseEntity<Map<String, Object>> createJabatan(@RequestBody Jabatan jabatan) {
        Map<String, Object> response = new HashMap<>();
        try {
            jabatanService.createJabatan(jabatan);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Jabatan created successfully");
            response.put("data", jabatan);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error creating Jabatan");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get all Jabatan
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllJabatan() {
        Map<String, Object> response = new HashMap<>();
        List<Jabatan> jabatans = jabatanService.getAllJabatan();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Jabatan list retrieved successfully");
        response.put("data", jabatans);
        return ResponseEntity.ok(response);
    }

    // Get Jabatan by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findJabatan(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Jabatan jabatan = jabatanService.findbyid(id);
            if (jabatan != null) {
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Jabatan found");
                response.put("data", jabatan);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "Jabatan not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error finding Jabatan");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Jabatan
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateJabatan(@PathVariable int id, @RequestBody Jabatan jabatan) {
        Map<String, Object> response = new HashMap<>();
        try {
            jabatan.setKodeJabatan(id);
            jabatanService.updateJabatan(jabatan);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Jabatan updated successfully");
            response.put("data", jabatan);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error updating Jabatan");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Delete Jabatan
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteJabatan(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            jabatanService.deleteJabatan(id);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Jabatan deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error deleting Jabatan");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
