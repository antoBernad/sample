package com.example;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class ExcelToXmlOpenCsv {
	private static final String SAMPLE_CSV_FILE_PATH = "E:\\excelManipulation\\sampleA.csv";

	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element rootElement = doc.createElement("response");
		doc.appendChild(rootElement);
		Element rows;

		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
		//CSVReader csvReader = new CSVReader(reader);
		CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
		CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();

		// CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		String[] headers = csvReader.readNext();

		// Reading Records One by One in a String array
		String[] nextRecord;
		while ((nextRecord = csvReader.readNext()) != null) {
			rows = doc.createElement("rows");
			for (int i = 0; i < headers.length; i++) {
				Element temp = doc.createElement("Example");
				temp.appendChild(doc.createTextNode(nextRecord[i]));
				rows.appendChild(temp);
				// System.out.println(headers[i] + " " + nextRecord[i]);
			}
			rootElement.appendChild(rows);
		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		// StreamResult result = new StreamResult(new File("C:\\cars.xml"));
		// transformer.transform(source, result);

		// Output to console for testing
		StreamResult consoleResult = new StreamResult(System.out);
		transformer.transform(source, consoleResult);
	}
}
