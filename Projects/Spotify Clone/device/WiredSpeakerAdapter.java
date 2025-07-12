package device;

import api.WiredSpeakeAPI;
import models.Song;

public class WiredSpeakerAdapter implements IAudioOutputDevice {
    private final WiredSpeakeAPI wiredSpeakerAPI;

    public WiredSpeakerAdapter(WiredSpeakeAPI wiredSpeakerAPI) {
        this.wiredSpeakerAPI = wiredSpeakerAPI;
    }

    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist() + " from album " + song.getAlbum();
        wiredSpeakerAPI.playSoundViaCable(payload);
    }
}
