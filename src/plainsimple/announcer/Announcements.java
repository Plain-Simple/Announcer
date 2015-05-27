package plainsimple.announcer;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import jaco.mp3.player.MP3Player;

class Announcements {
  private final MP3Player player = new MP3Player();
  public void playFile(String filename) {
    addToList(filename);
    if (player.getPlayList().size() != 0) {
      player.play();
    }
  }
  public void callNames(List names, String eventName) {
    addToList("events/" + eventName);
    for (int i = 0; i < names.size(); i++) {
      /* say and before the last name, as long as there is more than one name */
      if (i + 1 == names.size() && names.size() > 1) {
        String and = "and";
        addToList( and);
      }
      addToList("names/" + names.get(i));
      String silence = "silence";
      addToList(silence);
    }
    addToList("conclusion");
    /* make sure there is stuff to play (there may not be if there is an issue
       opening the file */
    if (player.getPlayList().size() != 0) {
      player.play();
    }
  }
  void addToList(String fileName) {
    try {
      File audioFile = new File("audio/" + fileName + ".mp3");
      if (!audioFile.isFile()) {
        /* force catch clause by throwing exception */
        throw new java.io.FileNotFoundException();
      }
      player.addToPlayList(audioFile);
      if (!"silence".equals(fileName)) {
        System.out.println("   playing " + fileName + ".mp3");
      }
    }
    catch (Exception e) {
      System.out.println("   unable to open " + fileName + ".mp3");
    }
  }
  void testSound () {
    addToList("test");
    /* make sure there is stuff to play (there may not be if there is an issue
       opening the file */
    if (player.getPlayList().size() != 0) {
      System.out.println("Sound test in progress..." +
                       "\nPress enter to end test");
      Scanner scanner = new Scanner(System.in);
      player.setRepeat(true);
      player.play();
      scanner.nextLine();
      player.stop();
    }
  }
}