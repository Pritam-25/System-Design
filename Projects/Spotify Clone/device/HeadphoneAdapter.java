package device;

import api.HeadphonesAPI;
import models.Song;

public class HeadphoneAdapter implements IAudioOutputDevice {
    private final HeadphonesAPI headphonesApi;

    public  HeadphoneAdapter(HeadphonesAPI headphonesApi) {
        this.headphonesApi = headphonesApi;
    }

    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist() + " from album " + song.getAlbum();
        headphonesApi.playSoundViaHeadphone(payload);
    }
}
