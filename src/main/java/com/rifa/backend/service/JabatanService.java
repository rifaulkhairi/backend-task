package com.rifa.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifa.backend.model.Cabang;
import com.rifa.backend.model.Jabatan;
import com.rifa.backend.repository.JabatanRepository;

import java.util.List;

@Service
public class JabatanService {

    @Autowired
    private JabatanRepository jabatanRepository;

    public void createJabatan(Jabatan jabatan) {
        jabatanRepository.save(jabatan);
    }

    public List<Jabatan> getAllJabatan() {
        return jabatanRepository.findAll();
    }

    public void updateJabatan(Jabatan jabatan) {
        jabatanRepository.update(jabatan);
    }

    public void deleteJabatan(int kodeJabatan) {
        jabatanRepository.deleteById(kodeJabatan);
    }
    
    public Jabatan findbyid(int kodeJabatan) {
    	return jabatanRepository.findById(kodeJabatan);
    }
}
