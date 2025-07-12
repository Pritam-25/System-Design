package factories;

import api.BluetoothSpeakerAPI;
import api.HeadphonesAPI;
import device.BluetoothSpeakerAdapter;
import device.HeadphoneAdapter;
import device.IAudioOutputDevice;
import device.WiredSpeakerAdapter;
import enums.DeviceType;

public class DeviceFactory {
    public static IAudioOutputDevice createDevice(DeviceType deviceType) {
        return switch (deviceType) {
            case BLUETOOTH -> new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
            case WIRED -> new WiredSpeakerAdapter(new api.WiredSpeakeAPI());
            default -> new HeadphoneAdapter(new HeadphonesAPI());
        };
    }
}
