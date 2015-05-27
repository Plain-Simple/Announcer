package plainsimple.announcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event {
  Event(String event_name) {
    name = event_name;
  }
  public String name = "";
  private final List competitors = new
  ArrayList(); /* names of all competitors in event */
  /* addCompetitor will be called as competitors are read from spreadsheet */
  public void addCompetitor(String name) {
    competitors.add(name);
  }
  /* for calling in random order - called after adding all competitors */
  public void shuffleCompetitors() {
    Collections.shuffle(competitors);
  }
  public void unshuffleCompetitors() {
    Collections.sort(competitors);
  }
  /* the number of competitor called in the last call */
  private int lastNumberCalled;
  /* currentCompetitor marks the announcer place in arraylist competitors */
  int currentCompetitor = 0;
  int competitorsRemaining() {
    int remaining = competitors.size() - (currentCompetitor);
    if (remaining == 0) {
      System.out.println("All competitors called");
    }
    return remaining;
  }
  public void callUp(int numberOfCompetitors) {
    if (competitorsRemaining() > 0) {
      Announcements say = new Announcements();
      try {
        say.callNames(competitors.subList(currentCompetitor, numberOfCompetitors + currentCompetitor), name);
        currentCompetitor += numberOfCompetitors;
        lastNumberCalled = numberOfCompetitors;
      }
      catch (Exception e) {
        /* if user specified too many competitors, call everyone remaining */
        callRemaining();
      }
    }
  }
  public void viewNext(int numberOfCompetitors) {
    /* don't go above number of competitors or user specified number, whichever
       is lower */
    for (int i = currentCompetitor; i < competitors.size() && i < currentCompetitor + numberOfCompetitors; i++) {
      System.out.println(competitors.get(i));
    }
  }
  public void callRemaining() {
    callUp(competitorsRemaining());
  }
  void viewRemaining() {
    boolean peopleLeft = (competitorsRemaining() != 0);
    if (peopleLeft) {
      /* prints number of remaining competitors */
      System.out.println(competitorsRemaining() + " competitors remaining:\n");
      /* prints list of remaining competitors */
      for (int i = currentCompetitor; i < competitors.size(); i++) {
        System.out.println(competitors.get(i));
      }
    }
  }
  public void recallLast() {
    currentCompetitor -= lastNumberCalled;
    System.out.println("Re-calling last " + lastNumberCalled + " people...");
    callUp(lastNumberCalled);
    lastNumberCalled = 0;
  }
  public void recallNumber(int howFarBack) {
    /* make sure recall doesn't go forward or farther back than zero */
    if (currentCompetitor - howFarBack < 0 || howFarBack < 0) {
      System.out.println("Please enter a positive number less than or equal to " + currentCompetitor);
    } else {
      currentCompetitor -= howFarBack;
      callUp(howFarBack);
      lastNumberCalled = 0;
    }
  }
  public void rewindLast() {
    currentCompetitor -= lastNumberCalled;
    lastNumberCalled = 0;
  }
  public void rewindNumber(int howFarBack) {
    /* make sure rewind doesn't go forward or farther back than zero */
    if (currentCompetitor - howFarBack < 0 || howFarBack < 0) {
      System.out.println("Please enter a positive number less than or equal to " + currentCompetitor);
    } else {
      currentCompetitor -= howFarBack;
      lastNumberCalled = 0;
    }
  }
  public void front(String name) {
    int targetIndex = competitors.indexOf(name);
    if (targetIndex >= 0) {
      competitors.remove(targetIndex);
      competitors.add(currentCompetitor, name);
    } else {
      System.out.println(name + " not found");
    }
  }
}
