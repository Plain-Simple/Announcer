package plainsimple.announcer;

public class Main {
  /* for simplicity, all official events will be created, even though unused
     ones will have zero competitors. Note that they are static to avoid a huge
     amount of memory being used to copy them */
  static Event cube2 = new Event("2x2");
  static Event cube3 = new Event("3x3");
  static Event cube4 = new Event("4x4");
  static Event cube5 = new Event("5x5");
  static Event cube6 = new Event("6x6");
  static Event cube7 = new Event("7x7");
  static Event bld3 = new Event("333bld");
  static Event bld4 = new Event("444bld");
  static Event bld5 = new Event("555bld");
  static Event oh3 = new Event("333oh");
  static Event skewb = new Event("skewb");
  static Event pyra = new Event("pyra");
  //TODO: add missing events

  public static void main(String[] args) {
    String filename = "";
    if (args.length == 1) {
      filename = args[0];
    } else {
      System.out.println("Error: invalid argument. You must specify the name of the xls file as argument.");
      System.exit(0);
    }
    /* opens up the Excel file and puts competitors in their proper events */
    WorkbookReader reader = new WorkbookReader();
    reader.readWorkbook(filename);
    /* starts the command line of the program */
    runCommandLine();
  }
  static void runCommandLine() {
    /* when the user wants to switch to a new event, currentEvent will return
       the event the user wants to switch to, and that is stored in
       currentEvent. The CLI is then run on the new event. */
    Event currentEvent = cube3;
    CLI cli = new CLI();
    while (true) {
      currentEvent = cli.run(currentEvent);
    }
  }
}