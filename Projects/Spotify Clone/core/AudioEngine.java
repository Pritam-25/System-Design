package core;

import device.IAudioOutputDevice;
import models.Song;

public class AudioEngine {
    private Song currentSong;
    private boolean songIsPaused;

    public AudioEngine() {
        currentSong = null;
        songIsPaused = false;
    }

    public String getCurrentSongTitle() {
        if (currentSong == null) {
            return "no song is currently playing";
        } else return currentSong.getTitle();
    }

    public boolean isPaused() {
        return songIsPaused;
    }

    public void playSong(Song song, IAudioOutputDevice device) {
        if (song == null || device == null) {
            throw new IllegalArgumentException("Song and device cannot be null");
        }
        // Resume playback if the song is paused
        if (songIsPaused && song == currentSong) {
            songIsPaused = false;
            System.out.println("Resuming playback of: " + getCurrentSongTitle());
            device.playAudio(song);
            return;
        }
        currentSong = song;
        songIsPaused = false;
        System.out.printf("Playing song: " + getCurrentSongTitle() + "\n");
        device.playAudio(song);
    }

    public void pauseSong() {
        if (currentSong == null) {
            System.out.println("No song is currently playing to pause.");
            return;
        }
        if (songIsPaused) {
            System.out.println("The song is already paused: " + getCurrentSongTitle());
        } else {
            songIsPaused = true;
            System.out.println("Pausing song: " + getCurrentSongTitle());
        }
    }
}
