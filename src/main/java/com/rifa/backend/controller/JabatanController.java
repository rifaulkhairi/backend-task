package com.rifa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rifa.backend.model.Cabang;
import com.rifa.backend.model.Jabatan;
import com.rifa.backend.service.JabatanService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/jabatan")
public class JabatanController {

    @Autowired
    private JabatanService jabatanService;

    @PostMapping
    public ResponseEntity<String> createJabatan(@RequestBody Jabatan jabatan) {
        jabatanService.createJabatan(jabatan);
        return ResponseEntity.ok("Jabatan created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Jabatan>> getAllJabatan() {
        return ResponseEntity.ok(jabatanService.getAllJabatan());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Jabatan> findJabatan(@PathVariable int id) {
        return ResponseEntity.ok(jabatanService.findbyid(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJabatan(@PathVariable int id, @RequestBody Jabatan jabatan) {
        jabatan.setKodeJabatan(id);
        jabatanService.updateJabatan(jabatan);
        return ResponseEntity.ok("Jabatan updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJabatan(@PathVariable int id) {
        jabatanService.deleteJabatan(id);
        return ResponseEntity.ok("Jabatan deleted successfully");
    }
}
