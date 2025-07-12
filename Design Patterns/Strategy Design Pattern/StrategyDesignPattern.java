// --- Strategy Interface for Walk ---
// Interface defining the "walk" behavior (Strategy Pattern)
interface WalkableRobot {
    void walk(); // Abstract method (to be implemented by concrete strategies)
}

// --- Concrete Strategies for walk ---
// Concrete implementation of WalkableRobot interface (Strategy)
class NormalWalk implements WalkableRobot {
    @Override
    public void walk() { // Overridden method (Polymorphism)
        System.out.println("Walking normally...");
    }
}

class NoWalk implements WalkableRobot {
    @Override
    public void walk() { // Overridden method (Polymorphism)
        System.out.println("Cannot walk.");
    }
}

// --- Strategy Interface for Talk ---
// Interface defining the "talk" behavior (Strategy Pattern)
interface TalkableRobot {
    void talk(); // Abstract method
}

// --- Concrete Strategies for Talk ---
// Concrete implementation of TalkableRobot interface (Strategy)
class NormalTalk implements TalkableRobot {
    @Override
    public void talk() { // Overridden method
        System.out.println("Talking normally...");
    }
}

class NoTalk implements TalkableRobot {
    @Override
    public void talk() { // Overridden method
        System.out.println("Cannot talk.");
    }
}

// --- Strategy Interface for Fly ---
// Interface defining the "fly" behavior (Strategy Pattern)
interface FlyableRobot {
    void fly(); // Abstract method
}

// --- Concrete Strategies for Fly ---
// Concrete implementation of FlyableRobot interface (Strategy)
class NormalFly implements FlyableRobot {
    @Override
    public void fly() { // Overridden method
        System.out.println("Flying normally...");
    }
}

class NoFly implements FlyableRobot {
    @Override
    public void fly() { // Overridden method
        System.out.println("Cannot fly.");

    }
}

// --- Robot Base Class ---
// Abstract class representing a Robot (Base Class for common features)
abstract class Robot {

    // Protected members for behavior composition (HAS-A relationship)
    protected WalkableRobot walkBehavior;
    protected TalkableRobot talkBehavior;
    protected FlyableRobot flyBehavior;

    // Constructor method (initializing behavior strategies through Dependency
    // Injection)
    public Robot(WalkableRobot w, TalkableRobot t, FlyableRobot f) {
        this.walkBehavior = w;
        this.talkBehavior = t;
        this.flyBehavior = f;
    }

    // Method to delegate walking behavior to the current strategy object
    public void walk() {
        walkBehavior.walk(); // Delegation to Strategy
    }

    // Method to delegate talking behavior to the current strategy object
    public void talk() {
        talkBehavior.talk(); // Delegation to Strategy
    }

    // Method to delegate flying behavior to the current strategy object
    public void fly() {
        flyBehavior.fly(); // Delegation to Strategy
    }

    // Abstract method that must be implemented by concrete subclasses
    public abstract void projection(); // Abstract Method (For custom feature per subclass)
}

// --- Concrete Robot Types ---

// Concrete class CompanionRobot extending Robot
class CompanionRobot extends Robot {

    // Constructor method - calls superclass (Robot) constructor
    public CompanionRobot(WalkableRobot w, TalkableRobot t, FlyableRobot f) {
        super(w, t, f); // Constructor chaining (calls Robot's constructor)
    }

    // Overridden abstract method - defines CompanionRobot specific projection
    // behavior
    @Override
    public void projection() {
        System.out.println("Displaying friendly companion features...");
    }
}

// Concrete class WorkerRobot extending Robot
class WorkerRobot extends Robot {

    // Constructor method - calls superclass (Robot) constructor
    public WorkerRobot(WalkableRobot w, TalkableRobot t, FlyableRobot f) {
        super(w, t, f); // Constructor chaining (calls Robot's constructor)
    }

    // Overridden abstract method - defines WorkerRobot specific projection behavior
    @Override
    public void projection() {
        System.out.println("Displaying worker efficiency stats...");
    }
}

// --- Main Function ---
// Main class containing the main() method (Entry Point of Java Program)
public class StrategyDesignPattern {

    // Main method - the starting point of program execution
    public static void main(String[] args) {

        // Creating a CompanionRobot object with NormalWalk, NormalTalk, NoFly behaviors
        Robot robot1 = new CompanionRobot(new NormalWalk(), new NormalTalk(), new NoFly());

        // Calling behavior methods (polymorphic behavior via strategy objects)
        robot1.walk(); // Executes NormalWalk.walk()
        robot1.talk(); // Executes NormalTalk.talk()
        robot1.fly(); // Executes NoFly.fly()
        robot1.projection(); // Executes CompanionRobot.projection()

        System.out.println("--------------------");

        // Creating a WorkerRobot object with NoWalk, NoTalk, NormalFly behaviors
        Robot robot2 = new WorkerRobot(new NoWalk(), new NoTalk(), new NormalFly());

        // Calling behavior methods
        robot2.walk(); // Executes NoWalk.walk()
        robot2.talk(); // Executes NoTalk.talk()
        robot2.fly(); // Executes NormalFly.fly()
        robot2.projection(); // Executes WorkerRobot.projection()
    }
}


/* 
!Conclusion

1. Encapsulate what varies & keep it separate from what remains same.
2. Solution to inheritance is not more inheritance.
3. Composition should be favoured over inheritance.
4. Code to interface & not to concretion.
5. Do NOT Repeat Yourself (DRY).
*/

