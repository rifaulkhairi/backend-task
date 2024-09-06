package com.rifa.backend.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rifa.backend.model.Cabang;
import com.rifa.backend.service.CabangService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cabang")
public class CabangController {

    @Autowired
    private CabangService cabangService;

    @PostMapping
    public ResponseEntity<String> createCabang(@RequestBody Cabang cabang) {
        cabangService.createCabang(cabang);
        return ResponseEntity.ok("Cabang created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Cabang>> getAllCabang() {
        return ResponseEntity.ok(cabangService.getAllCabang());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cabang> findCabang(@PathVariable int id) {
        return ResponseEntity.ok(cabangService.findbyid(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCabang(@PathVariable int id, @RequestBody Cabang cabang) {
        cabang.setKodeCabang(id);
        cabangService.updateCabang(cabang);
        return ResponseEntity.ok("Cabang updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCabang(@PathVariable int id) {
        cabangService.deleteCabang(id);
        return ResponseEntity.ok("Cabang deleted successfully");
    }
}
