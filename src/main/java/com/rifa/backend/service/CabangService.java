package com.rifa.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.backend.model.Cabang;
import com.rifa.backend.repository.CabangRepository;

import java.util.List;

@Service
public class CabangService {

    @Autowired
    private CabangRepository cabangRepository;

    public void createCabang(Cabang cabang) {
        cabangRepository.save(cabang);
    }

    public List<Cabang> getAllCabang() {
        return cabangRepository.findAll();
    }

    public void updateCabang(Cabang cabang) {
        cabangRepository.update(cabang);
    }

    public void deleteCabang(int kodeCabang) {
        cabangRepository.deleteById(kodeCabang);
    }
    public Cabang findbyid(int kodeCabang) {
    	return cabangRepository.findById(kodeCabang);
    }
}
