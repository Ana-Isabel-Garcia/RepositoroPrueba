package com.qualitystream.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	// este metodo es para escribir una lista de datos en el excel
	public void writeExcel(String ruta, String nombreHoja, String[] dataToWrite) throws IOException {

		// creación del objeto para manipular el archivo
		File file = new File(ruta);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook libro = new XSSFWorkbook(inputStream);
		XSSFSheet hoja = libro.getSheet(nombreHoja);

		// indica el numero de filas que estamos manejando
		int rowNumber = hoja.getLastRowNum() - hoja.getFirstRowNum();
		XSSFRow row = hoja.getRow(rowNumber);

		// creamos una nueva fila para escribir
		XSSFRow newRow = hoja.createRow(rowNumber + 1);

		// comenzamos a escribir el arreglo de datos
		for (int i = 0; i <= row.getLastCellNum(); i++) {
			XSSFCell newCell = newRow.createCell(i);
			newCell.setCellValue(dataToWrite[i]);
		}

		// cerramos el objeto para manipular el archivo
		inputStream.close();

		FileOutputStream outpuStream = new FileOutputStream(file);
		libro.write(outpuStream);
		outpuStream.close();

		libro.close();
	}

	// este metodo sirve para escribir datos en una celda especifica
	public void writeCellValue(String ruta, String nombreHoja, int rowNumber, int CellNumber, String resultText)
			throws IOException {

		// creación del objeto para manipular el archivo
		File file = new File(ruta);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook libro = new XSSFWorkbook(inputStream);
		XSSFSheet hoja = libro.getSheet(nombreHoja);

		// fila a la que qieremos acceder
		XSSFRow row = hoja.getRow(rowNumber);

		// celda a la que queremos acceder
		XSSFCell firstCell = row.getCell(CellNumber - 1);
		System.out.println("Valor de la primera celda: " + firstCell.getStringCellValue());

		// crear otra celda
		XSSFCell nextCell = row.createCell(CellNumber);

		// escribir el resultado en la celda creada
		nextCell.setCellValue(resultText);
		System.out.println("Valor de la siguinete celda: " + nextCell.getStringCellValue());

		// cerramos el objeto para manipular el archivo
		inputStream.close();

		FileOutputStream outpuStream = new FileOutputStream(file);
		libro.write(outpuStream);
		outpuStream.close();

		libro.close();
	}
}
