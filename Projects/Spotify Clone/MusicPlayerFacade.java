import core.AudioEngine;
import device.IAudioOutputDevice;
import enums.DeviceType;
import enums.PlayStrategyType;
import managers.DeviceManager;
import managers.PlaylistManager;
import managers.StrategyManager;
import models.Playlist;
import models.Song;
import strategies.PlayStrategy;

public class MusicPlayerFacade {
    private static MusicPlayerFacade instance = null;
    private final AudioEngine audioEngine;
    private Playlist loadedPlaylist;
    private PlayStrategy playStrategy;

    private MusicPlayerFacade() {
        loadedPlaylist = null;
        playStrategy = null;
        audioEngine = new AudioEngine();
    }

    public static synchronized MusicPlayerFacade getInstance() {
        if (instance == null) {
            instance = new MusicPlayerFacade();
        }
        return instance;
    }

    public void connectDevice(DeviceType deviceType) {
        DeviceManager.getInstance().connect(deviceType);
    }

    public void setPlayStrategy(PlayStrategyType strategyType) {
        playStrategy = StrategyManager.getInstance().getStrategy(strategyType);
    }

    public void loadPlaylist(String name) {
        loadedPlaylist = PlaylistManager.getInstance().getPlaylist(name);
        if (playStrategy == null) {
            throw new RuntimeException("PlayStrategy not set before loading playlist");
        }
        playStrategy.setPlaylist(loadedPlaylist);
    }

    public void playSong(Song song) {
        if (!DeviceManager.getInstance().hasOutputDevice())
            throw new IllegalStateException("No audio device connected");
        IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
        audioEngine.playSong(song, device);
    }

    public void pauseSong(Song song) {
        if (!audioEngine.getCurrentSongTitle().equals(song.getTitle())) {
            throw new IllegalArgumentException("Song is not currently playing");
        }
        audioEngine.pauseSong();
    }


    public void playAllTracks() {
        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }
        while (playStrategy.hasNext()) {
            Song song = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            audioEngine.playSong(song, device);
        }

        System.out.println("Completed playing all tracks in the playlist: " + loadedPlaylist.getPlaylistName());
    }


    public void playNextTrack() {
        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }
        if (playStrategy.hasNext()) {
            Song song = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            audioEngine.playSong(song, device);
        } else {
            System.out.println("Completed playing all songs in the track: " + loadedPlaylist.getPlaylistName());
        }
    }

    public void playPreviousTrack() {
        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }
        if (playStrategy.hasPrevious()) {
            Song song = playStrategy.previous();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            audioEngine.playSong(song, device);
        } else {
            System.out.printf("Completed playing all songs in the track: %s\n", loadedPlaylist.getPlaylistName());
        }
    }

    public void enqueNextSong(Song song) {
        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }
        if (playStrategy == null) {
            throw new RuntimeException("PlayStrategy not set before enqueuing song");
        }
        playStrategy.addToNext(song);
    }
}

