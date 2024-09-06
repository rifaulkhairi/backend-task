package com.rifa.backend.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PegawaiRowMapper implements RowMapper<Pegawai> {
    @Override
    public Pegawai mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pegawai pegawai = new Pegawai();
        pegawai.setKodePegawai(rs.getLong("KodePegawai"));
        pegawai.setNamaPegawai(rs.getString("NamaPegawai"));
        pegawai.setKodeCabang(rs.getInt("KodeCabang"));
        pegawai.setKodeJabatan(rs.getInt("KodeJabatan"));
        pegawai.setTanggalMulaiKontrak(rs.getDate("TanggalMulaiKontrak").toLocalDate());
        pegawai.setTanggalHabisKontrak(rs.getDate("TanggalHabisKontrak").toLocalDate());
        return pegawai;
    }
}
