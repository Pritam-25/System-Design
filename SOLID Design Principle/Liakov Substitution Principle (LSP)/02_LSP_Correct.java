// --------------- INTERFACE -----------------
interface Flyable {
    void fly();
}

// --------------- BASE CLASS -----------------
class Bird {
    // common bird properties and methods
    void eat() {
        System.out.println("The bird is eating.");
    }
}

// --------------- FLYING BIRDS -----------------
class Sparrow extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("The sparrow is flying.");
    }
}

// --------------- NON-FLYING BIRDS -----------------
class Penguin extends Bird {
    // No fly() method here, so no unsupported operation
    public void swim() {
        System.out.println("The penguin is swimming.");
    }
}

class LSP_Correct {
    static void makeBirdFly(Flyable bird) {
        bird.fly(); // polymorphism works correctly
    }

    public static void main(String[] args) {
        System.out.println("---- Sparrow Test ----");
        Flyable sparrow = new Sparrow();
        makeBirdFly(sparrow); // ✅ works perfectly

        System.out.println("\n---- Penguin Test ----");
        Penguin penguin = new Penguin();
        penguin.swim(); // ✅ works perfectly
        // to Penguin
        // makeBirdFly(penguin); // ❌ compile-time error, so no runtime crash
    }
}
