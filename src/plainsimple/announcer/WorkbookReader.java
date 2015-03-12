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
//TODO: try/catch blocks everywhere!
public class WorkbookReader {
  void readWorkbook(String filename) {
    /* this will need much better error + exception handling later on, probably
       separated into multiple try/catch blocks */
    try {
      /* opens the workbook stored in the file, and takes the first
         sheet. That is the only sheet we will be using */
      Workbook wb = WorkbookFactory.create(new File(filename));
      Sheet sheet = wb.getSheetAt(0);
      identifyColumns(sheet);
    } catch (Exception e) {
      System.out.println("exception!!!!");
    }
  }

  public void identifyColumns(Sheet sheet) {
    int column = 9;
    System.out.println(sheet.getRow(2).getCell(column).toString());
    while (!(sheet.getRow(2).getCell(column)).equals(null)) {
      switch (sheet.getRow(2).getCell(column).toString()) {
        case "2x2":
          addCompetitorToEvent(sheet, Main.cube2, column);
          break;
        case "3x3":
          addCompetitorToEvent(sheet, Main.cube3, column);
          break;
        case "4x4":
          addCompetitorToEvent(sheet, Main.cube4, column);
          break;
        case "5x5":
          addCompetitorToEvent(sheet, Main.cube5, column);
          break;
        case "6x6":
          addCompetitorToEvent(sheet, Main.cube6, column);
          break;
        case "7x7":
          addCompetitorToEvent(sheet, Main.cube7, column);
          break;
        case "333bld":
          addCompetitorToEvent(sheet, Main.bld3, column);
          break;
        case "444bld":
          addCompetitorToEvent(sheet, Main.bld4, column);
          break;
        case "555bld":
          addCompetitorToEvent(sheet, Main.bld5, column);
          break;
        case "333oh":
          addCompetitorToEvent(sheet, Main.oh3, column);
          break;
        case "skewb":
          addCompetitorToEvent(sheet, Main.skewb, column);
          break;
        case "pyra":
          addCompetitorToEvent(sheet, Main.pyra, column);
          break;
        default:
          System.out.println("Unreadable event at column" + column);
      }
      column++;
    }
  }
  public void addCompetitorToEvent(Sheet sheet, Event event, int eventColumn) {
    int currentRow = 3;
    while (!(sheet.getRow(currentRow).getCell(1)).equals(null)) {
      if (sheet.getRow(currentRow).getCell(eventColumn).toString().equals("1")) {
        event.addCompetitor(sheet.getRow(currentRow).getCell(1).toString());
      }
    }
  }
}
