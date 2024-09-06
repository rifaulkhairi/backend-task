package com.rifa.backend.repository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.rifa.backend.entity.Data;
import com.rifa.backend.model.Jabatan;
import com.rifa.backend.model.Pegawai;
import com.rifa.backend.model.PegawaiRowMapper;

import aj.org.objectweb.asm.Type;
import dto.PegawaiFilter;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

@Repository
public class PegawaiRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;
  

    public PegawaiRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_GetPegawaiKontrak");
    }

//    public PegawaiRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public void save(Pegawai pegawai) {
    	jdbcTemplate.update("INSERT INTO Pegawai (NamaPegawai, KodeCabang, KodeJabatan, TanggalMulaiKontrak, TanggalHabisKontrak) VALUES (?, ?, ?, ?, ?)",
                pegawai.getNamaPegawai(), pegawai.getKodeCabang(), pegawai.getKodeJabatan(),
                pegawai.getTanggalMulaiKontrak(), pegawai.getTanggalHabisKontrak());
    }
    public void saveAll(List<Pegawai> pegawaiList) {
        for (Pegawai pegawai : pegawaiList) {
            jdbcTemplate.update("INSERT INTO Pegawai (NamaPegawai, KodeCabang, KodeJabatan, TanggalMulaiKontrak, TanggalHabisKontrak) VALUES (?, ?, ?, ?, ?)",
                    pegawai.getNamaPegawai(), pegawai.getKodeCabang(), pegawai.getKodeJabatan(),
                    pegawai.getTanggalMulaiKontrak(), pegawai.getTanggalHabisKontrak());
        }
    }
    

    public List<Pegawai> findAll() {
        return jdbcTemplate.query("SELECT * FROM Pegawai", new PegawaiRowMapper());
    }

    
    public void update(Pegawai pegawai) {
        jdbcTemplate.update("UPDATE Pegawai SET NamaPegawai = ?, KodeCabang = ?, KodeJabatan = ?, TanggalMulaiKontrak = ?, TanggalHabisKontrak = ? WHERE KodePegawai = ?",
                pegawai.getNamaPegawai(), pegawai.getKodeCabang(), pegawai.getKodeJabatan(),
                pegawai.getTanggalMulaiKontrak(), pegawai.getTanggalHabisKontrak(), pegawai.getKodePegawai());
    }

    public void deleteById(Long kodePegawai) {
        jdbcTemplate.update("DELETE FROM Pegawai WHERE KodePegawai = ?", kodePegawai);
    }
    
    
    public Pegawai findById(int kodePegawai) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM Pegawai WHERE KodePegawai = ?",
            new Object[]{kodePegawai},
            new PegawaiRowMapper()
        );
    }
    
	

    
    public List<Data> filter(PegawaiFilter filter) {
        // Create a map to hold the parameters
        Map<String, Object> params = new HashMap<>();

        if (filter.getDaysToExpire() != null) {
            params.put("DaysToExpire", filter.getDaysToExpire());
        }
        

        // Execute the stored procedure with the parameters
        Map<String, Object> result = simpleJdbcCall.execute(params);

        
        return mapResultToPegawai(result);
    }

    private List<Data> mapResultToPegawai(Map<String, Object> result) {
        // Extract the result set from the returned Map
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

        // Check if rows is null
        if (rows == null) {
            return new ArrayList<>(); // Return empty list if no rows found
        }

        List<Data> listdatapegawai = new ArrayList<>();

        // Loop through the result set and map each row to a Pegawai object
        for (Map<String, Object> row : rows) {
             Data datapgawai= new Data();

            // Handle potential null values while mapping
            datapgawai.setKodePegawai((row.get("KodePegawai") != null) ? ((Number) row.get("KodePegawai")).longValue() : null);
            datapgawai.setNamaPegawai((String) row.get("NamaPegawai"));
            datapgawai.setKodeCabang((row.get("KodeCabang") != null) ? ((Number) row.get("KodeCabang")).intValue() : null);
            datapgawai.setKodeJabatan((row.get("KodeJabatan") != null) ? ((Number) row.get("KodeJabatan")).intValue() : null);
            datapgawai.setNamaCabang((String) row.get("NamaCabang"));
            datapgawai.setNamaCJabatan((String) row.get("NamaJabatan"));

            // Convert date fields, making sure nulls are handled
            datapgawai.setTanggalMulaiKontrak((row.get("TanggalMulaiKontrak") != null) 
                ? ((java.sql.Date) row.get("TanggalMulaiKontrak")).toLocalDate() : null);
            datapgawai.setTanggalHabisKontrak((row.get("TanggalHabisKontrak") != null) 
                ? ((java.sql.Date) row.get("TanggalHabisKontrak")).toLocalDate() : null);

            listdatapegawai.add(datapgawai);
        }

        return listdatapegawai;
    }

    
}