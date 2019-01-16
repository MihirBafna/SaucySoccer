import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundEffect {

	private Clip clip;
	private boolean repeat;
	public static final SoundEffect synthybeat = new SoundEffect("music/synthygamebeat.wav");
	public static final SoundEffect icysynth = new SoundEffect("music/icysynthbeat.wav");
	public static final SoundEffect chimesbeattrap = new SoundEffect("music/chimesbeattrap.wav");
	public static final SoundEffect powerup = new SoundEffect("music/powerup1.wav");
	public static final SoundEffect fireball = new SoundEffect("music/fireball.wav");

	
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}

	public static Volume volume = Volume.LOW;

	SoundEffect(String soundFileName) {
		try {
			URL url = this.getClass().getClassLoader().getResource(soundFileName);
			// Set up an audio input stream piped from the sound file.
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			// Get a clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
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
			if (this.repeat){
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}else{
				if (clip.isRunning()) {
					clip.stop(); // Stop the player if it is still running
				}
				clip.setFramePosition(0); // Rewind to the beginning
				clip.start(); // Start playing
			}
		}

	}
	public void setRepeat(boolean x){
		this.repeat = x;
	}
}
