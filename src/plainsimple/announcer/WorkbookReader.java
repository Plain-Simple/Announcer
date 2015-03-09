package plainsimple.announcer;


import org.apache.poi.*;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class WorkbookReader {
  void readWorkbook(String filename) {
    /* this will need much better error + exception handling later on, probably
       separated into multiple try/catch blocks */
    try {
      /* opens the workbook stored in the file, and takes the first
         sheet. That is the only sheet we will be using */
      Workbook wb = WorkbookFactory.create(new File(filename));
      Sheet sheet = wb.getSheetAt(0);
      int currentRow = 3;
      Row r = sheet.getRow(77);
      /* I'm getting exceptions despite having the null check, this should be
         investigated */
      while(sheet.getRow(currentRow).getCell(1) != null) {
        /* cell 1 (second cell) of each row is the competitor names */
        System.out.println(sheet.getRow(currentRow).getCell(1));

        currentRow++;
      }
      System.out.println("done, currentRow=" + currentRow);
    } catch(Exception e) {
      //System.out.println("yikes!");
      System.out.println(e.getMessage().toString());
    }
  }
  public void addCompetitorToEvents(Row titleRow, Row competitorRow) {
    /* this function should read the title of the titleRow and add the
       competitor to the appropriate events based on the ones and zeroes
       in the competitorRow
     */
  }
}
