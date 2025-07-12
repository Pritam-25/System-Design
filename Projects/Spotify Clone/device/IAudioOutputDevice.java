package device;

import models.Song;

public interface IAudioOutputDevice {
    void playAudio(Song song);
}
