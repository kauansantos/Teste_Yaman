package Standard.utils.others;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelEmpresa {

    private Iterator<Row> rowIterator;
    private static String pathFileExcel = "C:\\Users\\" + System.getProperty("user.name")
            + "\\Desktop\\Empresa\\LAYOUT_Empresa.xlsx";
    private File fileExcel = new File(pathFileExcel);

    @SuppressWarnings("unused")
    public List<String> getCodEmp() {

        try {
            int numLinha = 0;
            FileInputStream fis = new FileInputStream(pathFileExcel);
            @SuppressWarnings("resource")
            LineNumberReader lineNumber = new LineNumberReader(new FileReader(fileExcel));
            List<String> listColuna = new ArrayList<String>();
            lineNumber.skip(fileExcel.length());
            numLinha = lineNumber.getLineNumber();
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            Sheet abaPlanilha = wb.getSheetAt(3);
            Row lCell = rowIterator.next();
            for (int x = 1; x < numLinha; x++) {

                /*
                 * Cell celStatus =
                 * abaPlanilha.getRow(x).getCell(3);//.getStringCellValue();
                 */ /* if(celStatus == null){ */
                /*C�DIGO EMPRESA*/
                listColuna.add(abaPlanilha.getRow(x).getCell(0).getStringCellValue());
                /*RAZ�O SOCIAL*/
                listColuna.add(abaPlanilha.getRow(x).getCell(1).getStringCellValue());
                /*NOME FANTASIA*/
                listColuna.add(abaPlanilha.getRow(x).getCell(2).getStringCellValue());
                /*C�DIGO CNPJ*/
                listColuna.add(abaPlanilha.getRow(x).getCell(3).getStringCellValue());

                return listColuna;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}