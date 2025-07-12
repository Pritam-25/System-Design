package models;

import java.util.List;
import java.util.ArrayList;

public class Playlist {
    private String playlistName;
    private final List<Song> songList;

    public Playlist(String name) {
        this.playlistName = name;
        this.songList = new ArrayList<>();
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<Song> getSongs() {
        return songList;
    }

    public void setPlaylistName(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Playlist name cannot be null or empty");
        }
        this.playlistName = name;
    }

    public void addNewSongToPlaylist(Song song) {
        if(song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }
        songList.add(song);
    }
}
