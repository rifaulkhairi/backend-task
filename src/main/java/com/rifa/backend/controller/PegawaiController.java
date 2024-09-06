package com.rifa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rifa.backend.entity.Data;
import com.rifa.backend.model.Cabang;
import com.rifa.backend.model.Pegawai;
import com.rifa.backend.service.PegawaiService;

import dto.PegawaiFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pegawai")
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;
    


    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            pegawaiService.uploadAndSaveExcelData(file);
            return ResponseEntity.ok("File uploaded and data saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload and save data: " + e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<String> addPegawai(@RequestBody Pegawai pegawai) {
        pegawaiService.savePegawai(pegawai);
        return ResponseEntity.ok("Pegawai added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Pegawai>> getAllPegawai() {
        return ResponseEntity.ok(pegawaiService.getAllPegawai());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePegawai(@PathVariable Long id, @RequestBody Pegawai pegawai) {
        pegawai.setKodePegawai(id);
        pegawaiService.updatePegawai(pegawai);
        return ResponseEntity.ok("Pegawai updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePegawai(@PathVariable Long id) {
        pegawaiService.deletePegawai(id);
        return ResponseEntity.ok("Pegawai deleted successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pegawai> findCabang(@PathVariable int id) {
        return ResponseEntity.ok(pegawaiService.findbyid(id));
    }

     
    @GetMapping("/filter")
    public ResponseEntity<List<Data>> filterPegawai(
            @RequestParam(required = false) Integer DaysToExpire
            
    ) {
    	
    	
//    	
        // Create the PegawaiFilter object and set the query parameters
        PegawaiFilter filter = new PegawaiFilter();

        filter.setDaysToExpire( DaysToExpire);
//
//        // Call the service layer to get the filtered list
        List<Data> pegawaiList = pegawaiService.filterpegawai(filter);
        return ResponseEntity.ok(pegawaiList);
    }
}
