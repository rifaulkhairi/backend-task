package com.rifa.backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rifa.backend.model.Pegawai;

@Service
public class  ExcelService{

	public List<Pegawai> readPegawaiFromExcel(MultipartFile file) throws IOException {
		List<Pegawai> pegawaiList = new ArrayList<>();

		InputStream is = file.getInputStream();
		Workbook workbook = new XSSFWorkbook(is);
		Sheet sheet = workbook.getSheetAt(0);
		for (Row row: sheet) {
			Pegawai pegawai = new Pegawai();
			pegawai.setKodePegawai((long) row.getCell(0).getNumericCellValue());
            pegawai.setNamaPegawai(row.getCell(1).getStringCellValue());
            pegawai.setKodeCabang((int) row.getCell(2).getNumericCellValue());
            pegawai.setKodeJabatan((int) row.getCell(3).getNumericCellValue());
            pegawai.setTanggalMulaiKontrak(row.getCell(4).getLocalDateTimeCellValue().toLocalDate());
            pegawai.setTanggalHabisKontrak(row.getCell(5).getLocalDateTimeCellValue().toLocalDate());
            pegawaiList.add(pegawai);
			
		}
		
		return pegawaiList;

	}

}
