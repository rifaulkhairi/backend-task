package com.rifa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rifa.backend.model.Cabang;
import com.rifa.backend.service.CabangService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cabang")
public class CabangController {

    @Autowired
    private CabangService cabangService;

    // Create Cabang
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCabang(@RequestBody Cabang cabang) {
        Map<String, Object> response = new HashMap<>();
        try {
            cabangService.createCabang(cabang);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Cabang created successfully");
            response.put("data", cabang);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error creating cabang");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get all Cabang
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCabang() {
        Map<String, Object> response = new HashMap<>();
        List<Cabang> cabangs = cabangService.getAllCabang();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Cabang list retrieved successfully");
        response.put("data", cabangs);
        return ResponseEntity.ok(response);
    }

    // Get Cabang by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findCabang(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Cabang cabang = cabangService.findbyid(id);
            if (cabang != null) {
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Cabang found");
                response.put("data", cabang);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "Cabang not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error finding cabang");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Cabang
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCabang(@PathVariable int id, @RequestBody Cabang cabang) {
        Map<String, Object> response = new HashMap<>();
        try {
            cabang.setKodeCabang(id);
            cabangService.updateCabang(cabang);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Cabang updated successfully");
            response.put("data", cabang);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error updating cabang");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Delete Cabang
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCabang(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            cabangService.deleteCabang(id);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Cabang deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error deleting cabang");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
