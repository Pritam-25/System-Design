/*
! Principle of Least Knowledge (a.k.a. Law of Demeter)
* The principle states that a method of an object should only call methods belonging to:
  → The object itself
  → Any object passed in as a parameter
  → Any object the method creates
  → Any object that is a direct component (HAS-A relationship)
  
* This leads to:
  → Reduced coupling
  → Better encapsulation
  → More maintainable and testable code

! Facade Design Pattern
* The Facade pattern provides a simplified, unified interface to a complex subsystem.
* It hides the internal complexity of the system and exposes only what is necessary to the client.
* Ideal when you want to provide a high-level interface over a complex set of classes or APIs.

! Loosely Coupled Design (Principle of Least Knowledge in action)
* "Talk only to your immediate friends"
? Example:
    A → B → C
    * A should interact only with B
    * B is responsible for communicating with C
    * A must not directly communicate with C
    * If A needs work done by C, then:
      → A should **communicate only with B**
      → B will **delegate the task to C**
      → B will then **return the result back to A**
*/



// Subsystems
class PowerSupply {
    public void providePower() {
        System.out.println("Power Supply: Providing power...");
    }
}

class CoolingSystem {
    public void startFans() {
        System.out.println("Cooling System: Fans started...");
    }
}

class CPU {
    public void initialize() {
        System.out.println("CPU: Initialization started...");
    }
}

class Memory {
    public void selfTest() {
        System.out.println("Memory: Self-test passed...");
    }
}

class HardDrive {
    public void spinUp() {
        System.out.println("Hard Drive: Spinning up...");
    }
}

class BIOS {
    public void boot(CPU cpu, Memory memory) {
        System.out.println("BIOS: Booting CPU and Memory checks...");
        cpu.initialize();
        memory.selfTest();
    }
}

class OperatingSystem {
    public void load() {
        System.out.println("Operating System: Loading into memory...");
    }
}

// Facade
class ComputerFacade {
    private final PowerSupply powerSupply = new PowerSupply();
    private final CoolingSystem coolingSystem = new CoolingSystem();
    private final CPU cpu = new CPU();
    private final Memory memory = new Memory();
    private final HardDrive hardDrive = new HardDrive();
    private final BIOS bios = new BIOS();
    private final OperatingSystem os = new OperatingSystem();

    public void startComputer() {
        System.out.println("----- Starting Computer -----");
        powerSupply.providePower();
        coolingSystem.startFans();
        bios.boot(cpu, memory);
        hardDrive.spinUp();
        os.load();
        System.out.println("Computer Booted Successfully!");
    }
}

// Client
public class FacadeDesignPattern {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
} 


