package device;

import api.BluetoothSpeakerAPI;
import models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice {
    private final BluetoothSpeakerAPI bluetoothAPI;

    public BluetoothSpeakerAdapter(BluetoothSpeakerAPI bluetoothAPI) {
        this.bluetoothAPI = bluetoothAPI;
    }

    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist() + " from album " + song.getAlbum();
        bluetoothAPI.playSoundViaBluetooth(payload);
    }
}
