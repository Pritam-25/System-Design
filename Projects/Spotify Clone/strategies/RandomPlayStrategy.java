package strategies;

import models.Playlist;
import models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RandomPlayStrategy implements PlayStrategy {
    private Playlist currentPlaylist;
    private List<Song> remainingSongs;
    private Stack<Song> history;
    private final Random random;

    public RandomPlayStrategy() {
        currentPlaylist = null;
        random = new Random();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        if (currentPlaylist == null || currentPlaylist.getSongs().isEmpty())
            return;
        remainingSongs = new ArrayList<>(currentPlaylist.getSongs());
        history = new Stack<>();
    }

    // next in loop
    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSongs().isEmpty()) {
            throw new IllegalStateException("No playlist loaded or playlist is empty.");
        }

        if (remainingSongs.isEmpty()) {
            throw new IllegalStateException("No songs left to play.");
        }

        int index = random.nextInt(remainingSongs.size());
        Song selectedSong = remainingSongs.get(index);

        // Remove the selected song from the remaining songs list  (swap and pop to remove in O(1))
        int lastIndex = remainingSongs.size() - 1;
        remainingSongs.set(index, remainingSongs.get(lastIndex));
        remainingSongs.remove(lastIndex);
        /*
        ?🧠 Explanation: Why not just remove(index)?
        In Java, ArrayList.remove(int index) is an O(n) operation in the worst case.
        When you remove an element at index i, all elements after index i are shifted one position to the left to fill the gap.
        This shifting makes remove(index) inefficient if the list is large and index is somewhere near the beginning or middle.
        */

        // Add the selected song to the history stack
        history.push(selectedSong);
        return selectedSong;
    }

    @Override
    public boolean hasNext() {
        return currentPlaylist != null && !remainingSongs.isEmpty();
    }

    @Override
    public Song previous() {
        if (history.isEmpty()) {
            throw new IllegalStateException("No Previous song available.");
        }
        return history.pop();
    }

    @Override
    public boolean hasPrevious() {
        return !history.isEmpty();
    }
}
