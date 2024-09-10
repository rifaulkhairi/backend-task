package com.rifa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rifa.backend.entity.Data;
import com.rifa.backend.model.Pegawai;
import com.rifa.backend.service.PegawaiService;

import dto.PegawaiFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pegawai")
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    // Upload and save data from Excel
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            pegawaiService.uploadAndSaveExcelData(file);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "File uploaded and data saved successfully");
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Failed to upload and save data");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addPegawai(@RequestBody Pegawai pegawai) {
        Map<String, Object> response = new HashMap<>();
        try {
            pegawaiService.savePegawai(pegawai);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Pegawai added successfully");
            response.put("data", pegawai);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error adding Pegawai");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPegawai() {
        Map<String, Object> response = new HashMap<>();
        List<Pegawai> pegawaiList = pegawaiService.getAllPegawai();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Pegawai list retrieved successfully");
        response.put("data", pegawaiList);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePegawai(@PathVariable Long id, @RequestBody Pegawai pegawai) {
        Map<String, Object> response = new HashMap<>();
        try {
            pegawai.setKodePegawai(id);
            pegawaiService.updatePegawai(pegawai);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Pegawai updated successfully");
            response.put("data", pegawai);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error updating Pegawai");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePegawai(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            pegawaiService.deletePegawai(id);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Pegawai deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error deleting Pegawai");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findPegawai(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pegawai pegawai = pegawaiService.findbyid(id);
            if (pegawai != null) {
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Pegawai found");
                response.put("data", pegawai);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "Pegawai not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error finding Pegawai");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> filterPegawai(
            @RequestParam(required = false) Integer DaysToExpire,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Map<String, Object> response = new HashMap<>();
        try {
            PegawaiFilter filter = new PegawaiFilter();
            filter.setDaysToExpire(DaysToExpire);

            // Using PageRequest to handle pagination
            PageRequest pageRequest = PageRequest.of(page, size);
            List<Data> pegawaiPage = pegawaiService.filterpegawai(filter, page, size);

            // Preparing response with pagination details
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Pegawai filtered successfully");
            response.put("data", pegawaiPage);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Error filtering Pegawai");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
