package plainsimple.announcer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jaco.mp3.player.MP3Player;

public class Announcements {
  public void playFile(String filename) {
    /* audio files are in a folder called "audio" */
    //filename = "me.mp3";
    filename = "audio/" + filename;
    try {
      File audioFile = new File(filename);
      if (!audioFile.isFile()) {
        throw new java.io.FileNotFoundException();
      }
      MP3Player player = new MP3Player(audioFile);
      player.play();
      System.out.println("Playing " + filename);
    } catch (Exception e) {
      System.out.println("Unable to open " + filename);
    }
    /* this function should play the audio of the specified filename */
  }

  public void callNames(List names) {
    /* audio files are in a folder called "audio" */
    MP3Player player = new MP3Player();
    player.addToPlayList(new File(announcementIntro));
    for (int i = 0; i < names.size(); i++) {
      try {
        File audioFile = new File("audio/names/" + names.get(i) + ".mp3");
        System.out.println("gj");
        if (!audioFile.isFile()) {
          throw new java.io.FileNotFoundException();
        }
        player.addToPlayList(audioFile);
        System.out.println("vgj");
      } catch (Exception e) {
        System.out.println("Unable to open audio/names/" + names.get(i) + ".mp3");
      }
    }
    player.play();
  }
  /* the following strings are filenames that are referenced when using this
    class. They can be changed here so that they only need to be changed once.*/
  String announcementIntro = "announcement_introduction.mp3";
  String lunch = "lunch.mp3";
}
