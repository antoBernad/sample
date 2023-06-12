package com.example;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ExcelToXmlCommonsCsv {
	private static final String SAMPLE_CSV_FILE_PATH = "E:\\excelManipulation\\sample.csv";

	public static void main(String[] args) throws Exception {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
		for (CSVRecord csvRecord : csvParser) {
			// Accessing values by Header names
			String name = csvRecord.get("FnolId");
			String email = csvRecord.get(1);
			String phone = csvRecord.get(2);

			System.out.println("Record No - " + csvRecord.getRecordNumber());
			System.out.println("---------------");
			System.out.println("FnolId : " + name);
			System.out.println("ClaimNumber : " + email);
			System.out.println("Status : " + phone);
			System.out.println("---------------\n\n");
		}
	}
}