package utils;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	public static int MENU_1 = 0;

	public static int BOUNCE = 0;
	public static int BREAK = 1;
	public static int START = 2;
	
	private Clip[] songs, effects;
	private int currentSongId;

	public SoundPlayer() {
		loadSongs();
		loadEffects();
		playSong(MENU_1);
	}

	private void loadSongs() {
		String[] names = { "menu"};
		songs = new Clip[names.length];
		for (int i = 0; i < songs.length; i++)
			songs[i] = getClip(names[i]);
	}

	private void loadEffects() {
		String[] effectNames = { "bounce", "break", "game_start"};
		effects = new Clip[effectNames.length];
		for (int i = 0; i < effects.length; i++)
			effects[i] = getClip(effectNames[i]);
		
	}

	private Clip getClip(String name) {
		URL url = getClass().getResource("/audio/" + name + ".wav");
		AudioInputStream audio;

		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			return c;

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

			e.printStackTrace();
		}

		return null;

	}


	public void stopSong() {
		if (songs[currentSongId].isActive())
			songs[currentSongId].stop();
	}

	public void gameStartEffect() {
		stopSong();
		playEffect(START);
	}
	
	public void bounceEffect() {
		stopSong();
		playEffect(BOUNCE);
	}
	
	public void breakEffect() {
		stopSong();
		playEffect(BREAK);
	}

	public void playEffect(int effect) {
		effects[effect].setMicrosecondPosition(0);
		effects[effect].start();
	}

	public void playSong(int song) {
		stopSong();

		currentSongId = song;
		songs[currentSongId].setMicrosecondPosition(0);
		songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
	}

}