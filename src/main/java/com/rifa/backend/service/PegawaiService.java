package com.rifa.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rifa.backend.entity.Data;
import com.rifa.backend.model.Cabang;
import com.rifa.backend.model.Pegawai;
import com.rifa.backend.repository.PegawaiRepository;

import dto.PegawaiFilter;

import java.io.IOException;
import java.util.List;

@Service
public class PegawaiService {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private PegawaiRepository pegawaiRepository;
    


    public void uploadAndSaveExcelData(MultipartFile file) throws IOException {
        List<Pegawai> pegawaiList = excelService.readPegawaiFromExcel(file);
        pegawaiRepository.saveAll(pegawaiList);
    }
    
    public void savePegawai(Pegawai pegawai) {
        pegawaiRepository.save(pegawai);
    }

    public List<Pegawai> getAllPegawai() {
        return pegawaiRepository.findAll();
    }

    public void updatePegawai(Pegawai pegawai) {
        pegawaiRepository.update(pegawai);
    }

    public void deletePegawai(Long kodePegawai) {
        pegawaiRepository.deleteById(kodePegawai);
    }
    
    public List<Data> filterpegawai(PegawaiFilter filter){
    	return pegawaiRepository.filter(filter);
    }
    public Pegawai findbyid(int kodePegawai) {
    	return pegawaiRepository.findById(kodePegawai);
    }
    
}
