package com.rifa.backend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.rifa.backend.model.Cabang;

import java.util.List;

@Repository
public class CabangRepository {

    private final JdbcTemplate jdbcTemplate;

    public CabangRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Cabang cabang) {
        jdbcTemplate.update("INSERT INTO Cabang (NamaCabang) VALUES (?)", cabang.getNamaCabang());
    }

    public List<Cabang> findAll() {
        return jdbcTemplate.query("SELECT * FROM Cabang", (rs, rowNum) -> {
            Cabang cabang = new Cabang();
            cabang.setKodeCabang(rs.getInt("KodeCabang"));
            cabang.setNamaCabang(rs.getString("NamaCabang"));
            return cabang;
        });
    }

    public void update(Cabang cabang) {
        jdbcTemplate.update("UPDATE Cabang SET NamaCabang = ? WHERE KodeCabang = ?",
                cabang.getNamaCabang(), cabang.getKodeCabang());
    }
    
    public Cabang findById(int kodeCabang) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM Cabang WHERE KodeCabang = ?",
            new Object[]{kodeCabang},
            cabangRowMapper()
        );
    }
    
    

    public void deleteById(int kodeCabang) {
        jdbcTemplate.update("DELETE FROM Cabang WHERE KodeCabang = ?", kodeCabang);
    }
    
    private RowMapper<Cabang> cabangRowMapper() {
        return (rs, rowNum) -> {
            Cabang cabang = new Cabang();
            cabang.setKodeCabang(rs.getInt("KodeCabang"));
            cabang.setNamaCabang(rs.getString("NamaCabang"));
            return cabang;
        };
    }
}