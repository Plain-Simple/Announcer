package plainsimple.announcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Event {
  Event(String event_name){
    name = event_name;
    /* this is the filename of the mp3 containing the spoken name of the event*/
    audioFile = event_name + ".mp3";
  }
  public String name = "";
  public String audioFile;
  private ArrayList competitors = new ArrayList(); /* names of all competitors in event */
  /* addCompetitor will be called as competitors are read from spreadsheet */
  public void addCompetitor(String name){
    competitors.add(name);
  }
  /* needed to call competitors in random order - plan to add a way to disable
     this when settings are implemented */
  public void shuffleCompetitors() {
    Collections.shuffle(competitors);
  }
  /* currentCompetitor marks the announcer place in arraylist competitors */
  private int currentCompetitor = 0;
  public boolean competitorsRemain() {
    if (currentCompetitor + 1 == competitors.size()) {
      System.out.println("All competitors called");
      return false;
    } else {
      return true;
    }
  }
  public void callUp(int numberOfCompetitors) {
    if (competitorsRemain()) {
      Announcements say = new Announcements();
      try {
        say.callNames(competitors.subList(currentCompetitor, numberOfCompetitors));
        currentCompetitor += numberOfCompetitors;
      } catch (Exception e) {
        callRemaining();
      }
    }
  }
  public void callRemaining() {
    callUp((competitors.size() - (currentCompetitor + 1)));
  }
  void viewRemaining() {
    /* prints number of remaining competitors */
    System.out.println((competitors.size() - (currentCompetitor + 1))
                       + " competitors remaining:\n");
    /* prints list of remaining competitors */
    for (int i = currentCompetitor; i < competitors.size(); i++) {
      System.out.println(competitors.get(i));
    }
  }
}
