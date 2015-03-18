package plainsimple.announcer;

import java.util.ArrayList;
import java.util.Collections;

public class Event {
  Event(String event_name) {
    name = event_name;
  }
  public String name = "";
  private final ArrayList competitors = new
  ArrayList(); /* names of all competitors in event */
  /* addCompetitor will be called as competitors are read from spreadsheet */
  public void addCompetitor(String name) {
    competitors.add(name);
  }
  /* for calling in random order - called after adding all competitors */
  public void shuffleCompetitors() {
    Collections.shuffle(competitors);
  }
  /* currentCompetitor marks the announcer place in arraylist competitors */
  private int currentCompetitor = 0;
  private int competitorsRemaining() {
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
}
