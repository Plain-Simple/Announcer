package plainsimple.announcer;

public class Main {
  /* for simplicity, all official events will be created, even though unused
     ones will have zero competitors */
  Event cube2 = new Event("2x2");
  Event cube3 = new Event("3x3");
  Event cube4 = new Event("4x4");
  Event cube5 = new Event("5x5");
  Event cube6 = new Event("6x6");
  Event cube7 = new Event("7x7");
  Event bld3 = new Event("333bld");
  Event bld4 = new Event("444bld");
  Event bld5 = new Event("555bld");
  Event oh3 = new Event("333oh");
  Event skewb = new Event("skewb");
  Event pyra = new Event("pyra");
  //TODO: add missing events

  public static void main(String[] args) {
    WorkbookReader reader = new WorkbookReader();
    reader.readWorkbook("sample.xls");
  }
}