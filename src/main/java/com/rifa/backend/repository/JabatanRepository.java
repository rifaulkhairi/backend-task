package com.rifa.backend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rifa.backend.model.Cabang;
import com.rifa.backend.model.Jabatan;

import java.util.List;

@Repository
public class JabatanRepository {

    private final JdbcTemplate jdbcTemplate;

    public JabatanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Jabatan jabatan) {
        jdbcTemplate.update("INSERT INTO Jabatan (NamaJabatan) VALUES (?)", jabatan.getNamaJabatan());
    }

    public List<Jabatan> findAll() {
        return jdbcTemplate.query("SELECT * FROM Jabatan", (rs, rowNum) -> {
            Jabatan jabatan = new Jabatan();
            jabatan.setKodeJabatan(rs.getInt("KodeJabatan"));
            jabatan.setNamaJabatan(rs.getString("NamaJabatan"));
            return jabatan;
        });
    }

    public void update(Jabatan jabatan) {
        jdbcTemplate.update("UPDATE Jabatan SET NamaJabatan = ? WHERE KodeJabatan = ?",
                jabatan.getNamaJabatan(), jabatan.getKodeJabatan());
    }

    public void deleteById(int kodeJabatan) {
        jdbcTemplate.update("DELETE FROM Jabatan WHERE KodeJabatan = ?", kodeJabatan);
    }
    
    public Jabatan findById(int kodeJabatan) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM Jabatan WHERE KodeJabatan = ?",
            new Object[]{kodeJabatan},
            jabatanRowMapper()
        );
    }
    
    private RowMapper<Jabatan> jabatanRowMapper() {
        return (rs, rowNum) -> {
            Jabatan jabatan = new Jabatan();
            jabatan.setKodeJabatan(rs.getInt("kodeJabatan"));
            jabatan.setNamaJabatan(rs.getString("namaJabatan"));
            return jabatan;
        };
    }
}
