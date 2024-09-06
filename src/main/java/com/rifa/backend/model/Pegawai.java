package com.rifa.backend.model;

import java.time.LocalDate;

public class Pegawai {
	private Long kodePegawai;
    private String namaPegawai;
    private int kodeCabang;
    private int kodeJabatan;
    private LocalDate tanggalMulaiKontrak;
    private LocalDate tanggalHabisKontrak;
	public Long getKodePegawai() {
		return kodePegawai;
	}
	public void setKodePegawai(Long kodePegawai) {
		this.kodePegawai = kodePegawai;
	}
	public String getNamaPegawai() {
		return namaPegawai;
	}
	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}
	public int getKodeCabang() {
		return kodeCabang;
	}
	public void setKodeCabang(int kodeCabang) {
		this.kodeCabang = kodeCabang;
	}
	public int getKodeJabatan() {
		return kodeJabatan;
	}
	public void setKodeJabatan(int kodeJabatan) {
		this.kodeJabatan = kodeJabatan;
	}
	public LocalDate getTanggalMulaiKontrak() {
		return tanggalMulaiKontrak;
	}
	public void setTanggalMulaiKontrak(LocalDate tanggalMulaiKontrak) {
		this.tanggalMulaiKontrak = tanggalMulaiKontrak;
	}
	public LocalDate getTanggalHabisKontrak() {
		return tanggalHabisKontrak;
	}
	public void setTanggalHabisKontrak(LocalDate tanggalHabisKontrak) {
		this.tanggalHabisKontrak = tanggalHabisKontrak;
	}
    
    

}
