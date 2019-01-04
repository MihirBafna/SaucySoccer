import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
  
public class SoundEffect {
	private Clip clip;
	
	  public static enum Volume {
	   	   MUTE, LOW, MEDIUM, HIGH
	   }
	   public static Volume volume = Volume.LOW;

	 SoundEffect(String soundFileName) {
	      try {
	         URL url = this.getClass().getClassLoader().getResource(soundFileName);
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	         clip = AudioSystem.getClip();
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	    	  e.printStackTrace();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      } catch (LineUnavailableException e) {
	    	  e.printStackTrace();
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	   }
	 public void play() {
	      if (volume != Volume.MUTE) {
	         if (clip.isRunning())
	            clip.stop();   // Stop the player if it is still running
	         clip.setFramePosition(0); // Rewind to the beginning
	         clip.start();     // Start playing
	      }
	      
	 	}
}


	


	 
	
	   

  


