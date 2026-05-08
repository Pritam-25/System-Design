//* */ --- Burger Interface ---

interface Burger {

    void prepare();
}

//* --- Concrete Burger Classes ---
class CheeseBurger implements Burger {

    @Override
    public void prepare() {
        System.out.println("Preparing Cheese Burger");
    }
}

class VeggieBurger implements Burger {

    @Override
    public void prepare() {
        System.out.println("Preparing Veggie Burger");
    }
}

class ChickenBurger implements Burger {

    @Override
    public void prepare() {
        System.out.println("Preparing Chicken Burger");
    }
}

//*  --- Simple Factory Class ---
// This class will decide which concrete Burger class to instantiate
class BurgerFactory {

    public static Burger createBurger(String type) {
        if (type.equalsIgnoreCase("cheese")) {
            return new CheeseBurger();
        } else if (type.equalsIgnoreCase("veggie")) {
            return new VeggieBurger();
        } else if (type.equalsIgnoreCase("chicken")) {
            return new ChickenBurger();
        } else {
            System.err.println("Unknown burger type: " + type);
            return null;
        }
    }
}

public class SimpleFactory {

    public static void main(String[] args) {
        String type = "cheese"; // Example burger type

        Burger burger = BurgerFactory.createBurger(type);

        if (burger != null) {
            burger.prepare(); // Prepare the burger
        } else {
            System.out.println("No burger created.");
        }
    }
}


/*
 
!Simple Factory
----------------

                     +----------------------+
                     |     <<abstract>>     |
                     |        Burger        |
                     |----------------------|
                     |     prepare();       |
                     +----------------------+
                                 ▲
             ┌────────────-------|---────────────-------┐
             │                   │                      │
             │                   │                      │
+------------------+   +------------------+   +------------------+
|   CheeseBurger   |   |   VeggieBurger   |   |   ChickenBurger  |
|------------------|   |------------------|   |------------------|
|  prepare() { }   |   |  prepare() { }   |   |  prepare() { }   |
+------------------+   +------------------+   +------------------+

                        uses
                          ▲
                          │
          +-------------------------------+
          |         BurgerFactory         |
          |-------------------------------|
          | createBurger(type: string)    |
          |                               |
          | if(type == "cheese")          |
          |    return new CheeseBurger()  |
          |                               |
          | else if(type == "veggie")     |
          |    return new VeggieBurger()  |
          |                               |
          | else if(type == "chicken")    |
          |    return new ChickenBurger() |
          +-------------------------------+

 */