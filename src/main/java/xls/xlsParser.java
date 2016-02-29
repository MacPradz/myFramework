package xls;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.InputStream;

/**
 * Created by MacPradz on 2016-02-28.
 */
public class XlsParser {
    public static final DateTimeZone TZ = DateTimeZone.forID("UTC");
    public static final DateTimeFormatter DTF = DateTimeFormat.forPattern("dd-MMM-yyyy").withZone(TZ);
    public static final int FIRST_DATA_ROW_N = 1;
    public static final int DATE_COL_N = 0;
    public static final int WDR_COL_N = 1;
    public static final int INJ_COL_N = 2;

    public String parse(InputStream is) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            String sheetName = sheet.getSheetName();
            System.out.println("parsing sheet: " + sheetName + "\n");

            for (int rowN = FIRST_DATA_ROW_N; rowN <= sheet.getLastRowNum(); rowN++) {
                HSSFRow row = sheet.getRow(rowN);
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

    private Number getValueForCol(HSSFRow row, int colN) {
        HSSFCell valueCell = row.getCell(colN);
        if (valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return valueCell.getNumericCellValue();
        } else {
            throw new RuntimeException("not numeric cell");
        }
    }

    private DateTime getValueDate(HSSFRow row) {
        HSSFCell dateCell = row.getCell(DATE_COL_N);
        String dateString = dateCell.toString();
        return DTF.parseDateTime(dateString);
    }
}
