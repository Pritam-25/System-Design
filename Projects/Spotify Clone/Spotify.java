/*
? Functional Requirements:
→ User can play, pause songs.
→ User can create playlist, add songs to playlist,
  play entire playlist (Sequential Manner, Random manner, custom etc).
→ App should support multiple output devices (Bluetooth speakers, wired speaker, headphones etc).

? Non-Functional Req:
→ Entire design should be easily scalable.
→ New features (new output devices, new way to play songs from playlist etc) can be easily added.

! Design pattern used:
-> Strategy Pattern
-> Singleton Pattern
-> Adapter Pattern
-> Factory Pattern
-> Facade Pattern
 */

import enums.DeviceType;
import enums.PlayStrategyType;

public class Spotify {
    public static void main(String[] args) {
        try {
            MusicPlayerApplication application = MusicPlayerApplication.getInstance();

            // Populate the song library
            application.createSongInLibrary("Kesariya", "Arijit Singh", "Brahmāstra", "/music/kesariya.mp3");
            application.createSongInLibrary("Chaiyya Chaiyya", "Sukhwinder Singh", "Dil Se", "/music/chaiyya_chaiyya.mp3");
            application.createSongInLibrary("Tum Hi Ho", "Arijit Singh", "Aashiqui 2", "/music/tum_hi_ho.mp3");
            application.createSongInLibrary("Jai Ho", "A. R. Rahman", "Slumdog Millionaire", "/music/jai_ho.mp3");
            application.createSongInLibrary("Zinda", "Siddharth Mahadevan", "Bhaag Milkha Bhaag", "/music/zinda.mp3");

            // Create playlist and add songs
            application.createPlaylist("Bollywood Vibes");

            application.addSongToPlaylist("Bollywood Vibes", "Kesariya");
            application.addSongToPlaylist("Bollywood Vibes", "Chaiyya Chaiyya");
            application.addSongToPlaylist("Bollywood Vibes", "Tum Hi Ho");
            application.addSongToPlaylist("Bollywood Vibes", "Jai Ho");

            // Connect device
            application.connectAudioDevice(DeviceType.BLUETOOTH);

            // Play/Pause a single song
            application.playSingleSong("Zinda");
            application.pauseSong("Zinda");
            application.playSingleSong("Zinda");  // resume

            System.out.println("\n-- Sequential Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.SEQUENTIAL);
            application.loadPlaylist("Bollywood Vibes");
            application.playAllTracksInPlaylist();


            System.out.println("\n-- Random Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.RANDOM);
            application.loadPlaylist("Bollywood Vibes");
            application.playAllTracksInPlaylist();

            System.out.println("\n-- Custom Queue Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.CUSTOM_QUEUE);
            application.loadPlaylist("Bollywood Vibes");
            application.queueSongNext("Kesariya");
            application.queueSongNext("Tum Hi Ho");
            application.playAllTracksInPlaylist();

            System.out.println("\n-- Play Previous in Sequential --\n");
            application.selectPlayStrategy(PlayStrategyType.SEQUENTIAL);
            application.loadPlaylist("Bollywood Vibes");
            application.playAllTracksInPlaylist();

            application.playPreviousTrackInPlaylist();
            application.playPreviousTrackInPlaylist();
        } catch (Exception error) {
            System.out.println("An error occurred: " + error.getMessage());
        }
    }
}
