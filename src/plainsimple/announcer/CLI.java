package plainsimple.announcer;

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
          runHelp();
          break;
        case "events":
          runEvents();
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
    }
    else {
      return userInput.toLowerCase();
    }
  }
  /* extracts the argument from the user's command (after the space) */
  private String getArgument(String userInput) {
    int substringStart = userInput.indexOf(' ') + 1;
    if (substringStart > 0) {
      int substringEnd = userInput.length();
      return userInput.substring(substringStart, substringEnd).toLowerCase();
    }
    else {   /* substring was -1, so no space */
      return "";
    }
  }
  private void runCall(String userArgument) {
    if (userArgument.equals("remaining")) {
      currentEvent.callRemaining();
    }
    else {
      try {
        currentEvent.callUp(Integer.parseInt(userArgument));
      }
      catch (Exception e) {
        Announcements say = new Announcements();
        say.playFile(userArgument);
      }
    }
  }
  private void runView(String userArgument) {
    if (userArgument.equals("remaining")) {
      currentEvent.viewRemaining();
      //todo: there should also be an "all" case for viewing every competitor,
      //todo: and also a view next x
    }
    else {
      try {
        currentEvent.viewNext(Integer.parseInt(userArgument));
      }
      catch (Exception e) {
        System.out.println("Error: you must enter either an integer or 'remaining'");
      }
    }
  }
  private void runHelp() {
    System.out.println(
      "This program should be started by passing the desired spreadsheet to read from as an argument like this: java Announcer Spreadsheet.xls \n"
      +
      "Possible commands are: (n stands for any number)\n" +
      "    test             tests the sound \n" +
      "    call n           calls the next n competitors in the current event \n" +
      "    call remaining   calls remaining competitors in the current event \n" +
      "    call whatever    plays whatever.mp3 from the audio folder \n" +
      "    recall           calls the competitors last called again \n" +
      "    recall n         calls the n last competitors called again \n" +
      "    rewind           rewinds the called competitors number to what it was before the last round of calls \n" +
      "    rewind n         rewinds the called competitors number n back \n" +
      "    view n           shows the next n competitors in the current event \n" +
      "    view remaining   shows remaining competitors in the current event \n" +
      "    events           shows list of possible events \n" +
      "    event newevent   switches to newevent \n" +
      "    help             show this help guide \n" +
      "    exit or quit     exit the program");
  }
  private void runEvents() {
    System.out.println(
      "Supported events: \n" +
      "note: the most common abbreviations for events are also supported, like '3' for 3x3 \n"
      +
      "    2x2 \n" +
      "    3x3 \n" +
      "    4x4 \n" +
      "    5x5 \n" +
      "    6x6 \n" +
      "    7x7 \n" +
      "    OH \n" +
      "    Pyra \n" +
      "    Skewb \n" +
      "    3BLD \n" +
      "    4BLD \n" +
      "    5BLD"
    );
  }
  private Event runEvent(String userArgument) {
    switch (userArgument) {
      /* returns the event corresponding to what the user wanted. If no event
         corresponds to what the user said, they get an error and the current
         event is returned.
       */
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
  private void testSound() {

  }
}
