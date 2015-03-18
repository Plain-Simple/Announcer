package plainsimple.announcer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
class WorkbookReader {
  void readWorkbook(String filename) {
    /* this will need much better error + exception handling later on, probably
       separated into multiple try/catch blocks */
    try {
      /* opens the workbook stored in the file, and takes the first
         sheet. That is the only sheet we will be using */
      Workbook wb = WorkbookFactory.create(new File(filename));
      Sheet sheet = wb.getSheetAt(0);
      identifyColumns(sheet);
    }
    catch (Exception e) {
      System.out.println("exception while reading workbook");
    }
  }
  /* identify (using title) which column represents which event, and run
     addCompetitorsToEvent() to add everyone to the events */
  void identifyColumns(Sheet sheet) {
    int column = 9;
    /* keep looking while cell isn't null or blank */
    while (sheet.getRow(2).getCell(column) != null &&
           sheet.getRow(2).getCell(column).getCellType() != Cell.CELL_TYPE_BLANK) {
      /* match up event title with the correct event */
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
  void addCompetitorToEvent(Sheet sheet, Event event, int eventColumn) {
    int currentRow = 3;
    try {
      /* add competitors that have a "1" in the column eventColumn to the event
         that eventColumn represents
       */
      //TODO: also check other cell, and check for blanks
      while (sheet.getRow(currentRow).getCell(1) != null) {
        //TODO: maybe change to numeric value rather than string?
        if (sheet.getRow(currentRow).getCell(eventColumn).toString().equals("1.0")) {
          /* if the competitor is registered for event, add his/her name */
          event.addCompetitor(sheet.getRow(currentRow).getCell(1).toString());
        }
        currentRow++;
      }
    }
    catch (Exception e) {
      //TODO: why is there an exception???
      //System.out.println("exception in addCompetitorToEvent()");
    }
    //todo: make this optional
    event.shuffleCompetitors();
  }
}
