//!  Command Design Pattern
// Encapsulate a request as an object, thereby allowing you to parameterize clients with different requests, queue or log requests, and support undo operations.

// command Interface

import java.util.ArrayList;
import java.util.List;

interface Command {
    void execute();

    void undo();
}

// Receiver
class Light {
    void turnOn() {
        System.out.println("Light is ON");
    }

    void turnOff() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    void turnOn() {
        System.out.println("Fan is ON");
    }

    void turnOff() {
        System.out.println("Fan is OFF");
    }
}

// Concrete Command for Light
class LightCommand implements Command {
    private Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

// Concrete Command for Light
class FanCommand implements Command {
    private Fan fan;

    public FanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn();
    }

    @Override
    public void undo() {
        fan.turnOff();
    }
}

// Invoker: Remote Controller with
class RemoteController {
    private List<Command> buttons;
    private List<Boolean> buttonPressed;

    public RemoteController(int numButtons) {
        buttons = new ArrayList<>();
        buttonPressed = new ArrayList<>();
        for (int i = 0; i < numButtons; i++) {
            buttons.add(null);
            buttonPressed.add(false); // false = off, true = on
        }
    }

    public void setCommand(int idx, Command cmd) {
        if (idx >= 0 && idx < buttons.size()) {
            buttons.set(idx, cmd);
            buttonPressed.set(idx, false);
        } else {
            System.out.println("Invalid button index: " + idx);
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < buttons.size() && buttons.get(idx) != null) {
            if (!buttonPressed.get(idx)) {
                buttons.get(idx).execute();
            } else {
                buttons.get(idx).undo();
            }
            buttonPressed.set(idx, !buttonPressed.get(idx)); // toggling on & off
        } else {
            System.out.println("No command assigned at button " + idx);
        }
    }
}


// Main Application
public class CommandPattern {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        int buttonCount = 3;
        RemoteController remote = new RemoteController(buttonCount);

        remote.setCommand(0, new LightCommand(livingRoomLight));
        remote.setCommand(1, new FanCommand(ceilingFan));

        // Simulate button presses (toggle behavior)
        System.out.println("--- Toggling Light Button 0 ---");
        remote.pressButton(0); // ON
        remote.pressButton(0); // OFF

        System.out.println("--- Toggling Fan Button 1 ---");
        remote.pressButton(1); // ON
        remote.pressButton(1); // OFF

        // Press unassigned button to show default message
        System.out.println("--- Pressing Unassigned Button 2 ---");
        remote.pressButton(2);
    }
}