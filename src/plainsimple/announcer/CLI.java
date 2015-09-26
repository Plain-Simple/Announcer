package plainsimple.announcer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class CLI {
  private Event currentEvent;

  /* declared as an event so that it can return which event to switch to */
  public Event run(Event event) {
    System.out.println("Current event: " + event.name);
    currentEvent = event;
    String userInput;
    String userArgument;
    do {
      userInput = getInput();
      /* userArgument is the part after the space (the argument) */
      userArgument = getArgument(userInput);
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
          printFile("help");
          break;
        case "events":
          printFile("events");
          break;
        case "event":
          /* the event is returned back to main and the command line is run off of it */
          return runEvent(userArgument);
        case "rewind":
          runRewind(userArgument);
          break;
        case "recall":
          runRecall(userArgument);
          break;
        case "test":
          Announcements say = new Announcements();
          say.testSound();
          break;
        case "alphabetize":
          runAlphabetize();
          break;
        case "randomize":
          runRandomize();
          break;
        case "exit": /* no break is intentional */
        case "quit":
          System.exit(0);
        default:
          runCommandNotFound();
      }
    }
    while (true);
  }
  private String getInput() {
    System.out.print("\nCalled: " + currentEvent.currentCompetitor +
        "\nRemaining: " + currentEvent.competitorsRemaining() +
        "\nTotal: " + (currentEvent.currentCompetitor + currentEvent.competitorsRemaining()) +
        "\n\nAnnouncer$ ");
    return new Scanner(System.in).nextLine();
  }

  /* extracts just the command (up to the space) */
  private String extractCommand(String userInput) {
    if (userInput.indexOf(' ') > 0) { /*make sure it actually contains a space*/
      return userInput.substring(0, userInput.indexOf(' ')).toLowerCase();
    } else {
      return userInput.toLowerCase();
    }
  }

  /* extracts the argument from the user's command (after the space) */
  private String getArgument(String userInput) {
    int substringStart = userInput.indexOf(' ') + 1;
    if (substringStart > 0) {
      int substringEnd = userInput.length();
      return userInput.substring(substringStart, substringEnd).toLowerCase();
    } else {   /* substring was -1, so no space */
      return "";
    }
  }

  private void runCall(String userArgument) {
    if (userArgument.equals("remaining")) {
      currentEvent.callRemaining();
    } else {
      try {
        currentEvent.callUp(Integer.parseInt(userArgument));
      } catch (Exception e) {
        Announcements say = new Announcements();
        say.playFile(userArgument);
      }
    }
  }
  private void runRandomize() {
    if (currentEvent.currentCompetitor == 0) {
      currentEvent.shuffleCompetitors();
      System.out.println("Event randomized. This cannot be undone once names are called");
    } else {
      System.out.println("Unable to randomize because names have already been called");
    }

  }
  private void runAlphabetize() {
    if (currentEvent.currentCompetitor == 0) {
      currentEvent.unshuffleCompetitors();
      System.out.println("Event alphabetized. This cannot be undone once names are called");
    } else {
      System.out.println("Unable to alphabetize because names have already been called");
    }

  }
  private void runView(String userArgument) {
    if (userArgument.equals("remaining")) {
      currentEvent.viewRemaining();
      //todo: there should also be an "all" case for viewing every competitor,
      //todo: and also a view next x
    } else {
      try {
        currentEvent.viewNext(Integer.parseInt(userArgument));
      } catch (Exception e) {
        System.out.println("Error: you must enter either an integer or 'remaining'");
      }
    }
  }

  private void runFront(String userArgument) {

  }
  // reads specified file and prints contents to console
  // returns whether file was read successfully
  public static boolean printFile(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line = null;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
      return true;
    } catch (Exception e) {
      System.out.println ("File " + filename + " not found");
      return false;
    }
  }

  private Event runEvent(String userArgument) {
    switch (userArgument) {
      /* returns the event corresponding to what the user wanted. If no event
         corresponds to what the user said, they get an error and the current
         event is returned. */
      case "":
        return currentEvent;
      case "2x2":
      case "2":
        return Main.cube2;
      case "3x3":
      case "3":
        return Main.cube3;
      case "4x4":
      case "4":
        return Main.cube4;
      case "5x5":
      case "5":
        return Main.cube5;
      case "6x6":
      case "6":
        return Main.cube6;
      case "7x7":
      case "7":
        return Main.cube7;
      case "oh":
      case "333oh":
      case "one-handed":
        return Main.oh3;
      case "3bld":
      case "blind":
      case "3blind":
      case "333bld":
      case "3x3 bld":
      case "3x3 blindfolded":
      case "blindfolded":
        return Main.bld3;
      case "4bld":
      case "4blind":
      case "444bld":
      case "4x4 bld":
      case "4x4 blindfolded":
        return Main.bld4;
      case "5bld":
      case "5blind":
      case "555bld":
      case "5x5 bld":
      case "5x5 blindfolded":
        return Main.bld5;
      case "pyra":
      case "pyraminx":
      case "pyramid":
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

  private void runRewind(String userArgument) {
    if (userArgument.equals("")) {
      currentEvent.rewindLast();
    } else {
      try {
        currentEvent.rewindNumber(Integer.parseInt(userArgument));
      } catch (Exception e) {
        System.out.println("Error: you must either enter a number or nothing");
      }
    }
  }

  private void runRecall(String userArgument) {
    if (userArgument.equals("")) {
      currentEvent.recallLast();
    } else {
      try {
        currentEvent.recallNumber(Integer.parseInt(userArgument));
      } catch (Exception e) {
        System.out.println("Error: you must either enter a number or nothing");
      }
    }
  }
}
