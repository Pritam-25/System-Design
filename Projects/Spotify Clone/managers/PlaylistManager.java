package managers;

import models.Playlist;
import models.Song;

import java.util.HashMap;
import java.util.Map;

public class PlaylistManager {
    private static PlaylistManager instance = null;
    private final Map<String, Playlist> playlists;

    private PlaylistManager() {
        playlists = new HashMap<>();
    }

    public static synchronized PlaylistManager getInstance() {
        if (instance == null) {
            instance = new PlaylistManager();
        }
        return instance;
    }

    public void createPlaylist(String name) {
        if (playlists.containsKey(name)) {
            throw new IllegalArgumentException("Playlist " + name + " already exists");
        }
        playlists.put(name, new Playlist(name));
    }

    public void addSongToPlaList(String playlistName, Song song){
        if(!playlists.containsKey(playlistName)){
            throw new IllegalArgumentException("Playlist " + playlistName + " does not exist");
        }

        playlists.get(playlistName).addNewSongToPlaylist(song);
    }

    public Playlist getPlaylist(String name){
        if (!playlists.containsKey(name)) {
            throw new IllegalArgumentException("Playlist " + name + " does not exist");
        }
        return playlists.get(name);
    }
}
