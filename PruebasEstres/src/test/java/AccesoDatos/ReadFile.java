package AccesoDatos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFile {
	// este metodo sirve para leer una hoja fila por fila
	public void readExcel(String ruta, String nombreHoja) throws IOException {

		// creación del objeto para manipular el archivo
		File file = new File(ruta);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook libro = new XSSFWorkbook(inputStream);
		XSSFSheet hoja = libro.getSheet(nombreHoja);

		// indica el numero de filas que estamos manejando
		int rowNumber = hoja.getLastRowNum() - hoja.getFirstRowNum();

		// aqui empezamos a manipular las filas
		for (int i = 0; i <= rowNumber; i++) {
			XSSFRow row = hoja.getRow(i);

			// iteramos sobre las celdas (columnas) de esa fila
			for (int j = 0; j < row.getLastCellNum(); j++) {
				// imprimimos el contendido de la celda
				System.out.println(row.getCell(j).getStringCellValue() + "||");
			}
		}

		libro.close();
	}

	// este emtodo sirve para leer una celda específica
	public String getCellValue(String ruta, String nombreHoja, int rowNumber, int cellNumber) throws IOException {

		// creación del objeto para manipular el archivo
		File file = new File(ruta);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook libro = new XSSFWorkbook(inputStream);
		XSSFSheet hoja = libro.getSheet(nombreHoja);

		// la fila en la que queremos leer
		XSSFRow row = hoja.getRow(rowNumber);

		// la celda que queremos de esa fila
		XSSFCell cell = row.getCell(cellNumber);

		libro.close();

		// retormanos el valor de la celda

		if (cell != null) {
			// System.out.println(cell.getCellType());
			String tipo = String.valueOf(cell.getCellType());

			if (tipo == "NUMERIC") {
				System.out.println("celda: " + cellNumber + ": " + cell.getNumericCellValue());
				return String.valueOf(cell.getNumericCellValue());

			}

			else {
				System.out.println("celda: " + cellNumber + ": " + cell.getStringCellValue());
				return cell.getStringCellValue();
			}
		}

		else {
			return "";
		}
	}

	public int getColumnas(String ruta, String nombreHoja, int fila) throws IOException {

		// creación del objeto para manipular el archivo
		File file = new File(ruta);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook libro = new XSSFWorkbook(inputStream);
		XSSFSheet hoja = libro.getSheet(nombreHoja);

		XSSFRow row = hoja.getRow(fila);
		libro.close();
		return row.getLastCellNum();
	}

	public int getFilas(String ruta, String nombreHoja) throws IOException {
		// creación del objeto para manipular el archivo
		File file = new File(ruta);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook libro = new XSSFWorkbook(inputStream);
		XSSFSheet hoja = libro.getSheet(nombreHoja);
		
		int rows = hoja.getLastRowNum();
		libro.close();
		return rows;
	}
}
