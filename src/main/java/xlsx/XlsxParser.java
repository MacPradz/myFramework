package xlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.InputStream;

/**
 * Created by MacPradz on 2016-02-28.
 */
public class XlsxParser {
    public static final DateTimeZone TZ = DateTimeZone.forID("UTC");
    public static final DateTimeFormatter DTF = DateTimeFormat.forPattern("dd-MMM-yyyy").withZone(TZ);
    public static final int FIRST_DATA_ROW_N = 1;
    public static final int DATE_COL_N = 0;
    public static final int WDR_COL_N = 1;
    public static final int INJ_COL_N = 2;

    public String parse(InputStream is) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            String sheetName = sheet.getSheetName();
            System.out.println("parsing sheet: " + sheetName + "\n");

            for (int rowN = FIRST_DATA_ROW_N; rowN <= sheet.getLastRowNum(); rowN++) {
                XSSFRow row = sheet.getRow(rowN);
                DateTime vDate = getValueDate(row);
                Number wdrValue = getValueForCol(row, WDR_COL_N);
                Number injValue = getValueForCol(row, INJ_COL_N);
                System.out.println(vDate+"\twdr: "+wdrValue+"\tinj: "+injValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "x";
    }

    private Number getValueForCol(XSSFRow row, int colN) {
        XSSFCell valueCell = row.getCell(colN);
        if (valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return valueCell.getNumericCellValue();
        } else {
            throw new RuntimeException("not numeric cell");
        }
    }

    private DateTime getValueDate(XSSFRow row) {
        XSSFCell dateCell = row.getCell(DATE_COL_N);
        String dateString = dateCell.toString();
        return DTF.parseDateTime(dateString);
    }
}
