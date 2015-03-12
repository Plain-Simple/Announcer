package plainsimple.announcer;

import java.util.Scanner;

public class CLI {
  public Event currentEvent;
  /* declared as an event so that it can return which event to switch to */
  public Event run(Event event) {
    currentEvent = event;
    String userInput = "";
    String userArgument = "";
    do {
      userInput = getInput();
      /* userArgument is the part after the space (the argument) */
      userArgument = getArgument(userArgument);
      /* extractCommand gets the part before the space (the command) */
      switch (extractCommand(userInput)) {
        case "call":
          runCall(userArgument);
          break;
        case "view":
          runView(userArgument);
          break;
        case "?": /* no break intentionally since both mean same thing */
        case "help":
          runHelp();
          break;
        case "event":
          return runEvent(userArgument);
        case "exit":
          System.exit(0);
        default:
          runCommandNotFound();
      }
    } while (true);
  }
  private String getInput() {
    System.out.println("Announcer$ ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
  /* extracts just the command (up to the space) */
  private String extractCommand(String userInput) {
    return userInput.substring(0, userInput.indexOf(' '));
  }
  /* extracts the argument from the user's command (after the space) */
  private String getArgument(String userInput) {
    return userInput.substring(userInput.indexOf(' ') + 1,
                               userInput.length() - 1);
  }
  private void runCall(String userArgument) {
    if (userArgument.equals("remaining") ||
        userArgument.equals("all")) {
      currentEvent.callRemaining();
    } else {
      currentEvent.callUp(Integer.parseInt(userArgument));
    }
  }
  private void runView(String userArgument) {
    if (userArgument.equals("remaining")) {
      currentEvent.viewRemaining();
      //todo: there should also be an "all" case for viewing every competitor,
      //todo: and also a view next x (not really needed)
    }
  }
  private void runHelp() {
    //TODO: write documentation of all commands
  }
  private Event runEvent(String userArgument) {
    switch (userArgument) {
      /* returns the event corresponding to what the user wanted. If no event
         corresponds to what the user said, they get an error and the current
         event is returned.
       */
      //TODO: add more cases
      case "2x2":
        return Main.cube2;
      case "3x3":
        return Main.cube3;
      case "4x4":
        return Main.cube4;
      case "5x5":
        return Main.cube5;
      case "6x6":
        return Main.cube6;
      case "7x7":
        return Main.cube7;
      case "oh":
        return Main.oh3;
      case "3bld":
        return Main.bld3;
      case "4bld":
        return Main.bld4;
      case "5bld":
        return Main.bld5;
      case "pyra":
        return Main.pyra;
      case "skewb":
        return Main.skewb;
      default:
        System.out.println("Error: no such event. Type 'help' or '?' for help" +
            " and a list of possible commands. Type 'events'" +
            " for a list of all events.");
        return currentEvent;
    }
  }
  private void runCommandNotFound() {
    System.out.println("Command not found. Type 'help' or '?' for help and a" +
                       " list of possible commands");
  }
}
