class Bird {
    public void fly() {
        System.out.println("The bird is flying.");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("The sparrow is flying.");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}

class LSP_Violated {
    static void makeBirdFly(Bird bird) {
        bird.fly(); // polymorphism
    }

    public static void main(String[] args) {
        System.out.println("---- Sparrow Test ----");
        Bird sparrow = new Sparrow();
        makeBirdFly(sparrow);

        System.out.println("\n---- Penguin Test ----");
        Bird penguin = new Penguin();
        makeBirdFly(penguin); // 💥 runtime error here
    }
}