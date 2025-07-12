package managers;

import device.IAudioOutputDevice;
import enums.DeviceType;
import factories.DeviceFactory;

public class DeviceManager {
    private static DeviceManager instance = null;
    private IAudioOutputDevice currentOutputDevice;

    private DeviceManager() {
        // Private constructor to prevent instantiation
        currentOutputDevice = null;
    }

    public static DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }

    public void connect(DeviceType deviceType) {
        if (currentOutputDevice != null) {
            // in c++: delete currentOutputDevice;
            // in Java, garbage collection will handle it
            currentOutputDevice = null;
        }

        currentOutputDevice = DeviceFactory.createDevice(deviceType);

        switch (deviceType) {
            case BLUETOOTH: {
                System.out.println("Bluetooth device connected");
                break;
            }
            case WIRED: {
                System.out.println("Wired device connected");
                break;
            }
            default: {
                System.out.println("Headphones connected");
                break;
            }
        }
    }

    public IAudioOutputDevice getOutputDevice() {
        if (currentOutputDevice == null) {
            throw new RuntimeException("Device not connected");
        }
        return currentOutputDevice;
    }

    public boolean hasOutputDevice() {
        return currentOutputDevice != null;
    }
}
