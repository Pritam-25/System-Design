package strategies;

import models.Playlist;
import models.Song;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;
import java.util.ArrayDeque;

public class CustomQueueStrategy implements PlayStrategy {

    private Playlist currentPlaylist;
    private int currentIndex;
    private final Queue<Song> nextQueue;
    private final Deque<Song> prevStack;

    public CustomQueueStrategy() {
        currentPlaylist = null;
        currentIndex = -1;
        nextQueue = new LinkedList<>();
        prevStack = new ArrayDeque<>(); // Deque used instead of legacy Stack
    }

    private Song nextSequential() {
        if (currentPlaylist == null || currentPlaylist.getSongs().isEmpty()) {
            throw new IllegalStateException("No playlist loaded or playlist is empty.");
        }
        if (currentIndex + 1 >= currentPlaylist.getSongs().size()) {
            throw new IllegalStateException("No more songs to play.");
        }
        currentIndex++;
        Song nextSong = currentPlaylist.getSongs().get(currentIndex);
        prevStack.push(nextSong); // Add to history
        return nextSong;
    }

    private Song previousSequential() {
        if (currentPlaylist == null || currentPlaylist.getSongs().isEmpty()) {
            throw new IllegalStateException("No playlist loaded or playlist is empty.");
        }
        if (currentIndex - 1 < 0) {
            throw new IllegalStateException("No previous songs available.");
        }
        currentIndex--;
        return currentPlaylist.getSongs().get(currentIndex);
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        currentIndex = -1;
        nextQueue.clear();
        prevStack.clear();
    }

    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSongs().isEmpty()) {
            throw new IllegalStateException("No playlist loaded or playlist is empty.");
        }

        if (!nextQueue.isEmpty()) {
            Song nextSong = nextQueue.poll();
            prevStack.push(nextSong);

            // update index to match queued song
            for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
                if (currentPlaylist.getSongs().get(i).equals(nextSong)) {
                    currentIndex = i;
                    break;
                }
            }
            return nextSong;
        }

        // Otherwise play sequentially
        return nextSequential();
    }

    @Override
    public boolean hasNext() {
        return currentPlaylist != null &&
                (currentIndex + 1) < currentPlaylist.getSongs().size();
    }

    @Override
    public Song previous() {
        if (currentPlaylist == null || currentPlaylist.getSongs().isEmpty()) {
            throw new IllegalStateException("No playlist loaded or playlist is empty.");
        }

        if (!prevStack.isEmpty()) {
            Song previousSong = prevStack.pop();

            // update index to match song in playlist
            for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
                if (currentPlaylist.getSongs().get(i).equals(previousSong)) {
                    currentIndex = i;
                    break;
                }
            }
            return previousSong;
        }

        // Otherwise play previous sequentially
        return previousSequential();
    }

    @Override
    public boolean hasPrevious() {
        return currentPlaylist != null &&
                (currentIndex - 1) >= 0;
    }

    @Override
    public void addToNext(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null.");
        }
        nextQueue.add(song);
    }
}
