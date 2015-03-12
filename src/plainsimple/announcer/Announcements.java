package plainsimple.announcer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Announcements {
  public void playFile(String filename) {
    /* audio files are in a folder called "audio" */
    filename = "audio/" + filename;
    try {
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.start();
      System.out.println("Playing " + filename);
    } catch (Exception e) {
      System.out.println("Unable to open " + filename);
    }
    /* this function should play the audio of the specified filename */
  }
  /* the following strings are filenames that are referenced when using this
    class. They can be changed here so that they only need to be changed once.*/
  String announcementIntro = "announcement_introduction.mp3";
  String lunch = "lunch.mp3";
}
