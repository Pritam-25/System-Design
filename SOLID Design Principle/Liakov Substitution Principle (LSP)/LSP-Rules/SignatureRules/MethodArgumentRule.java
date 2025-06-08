/*
 ✅ Method Argument Rule (Overriding Rules)
In Java,Subtype method arguments can be identical or wider than the supertype,
Java enforces this by requiring the same method signature for overrides.

!it means:
--------------
Same method name
Same parameter types and order
Same return type (or covariant)
Same (or fewer) checked exceptions thrown
*/




class Parent {
    public void print(String msg) {
        System.out.println("Parent: " + msg);
    }
}

// Child class correctly overrides the print(String msg) method of the Parent class.
class Child extends Parent {  
    @Override
    public void print(String msg) {
        System.out.println("Child: " + msg);
    }
}

// Client that passes a String msg as the client expects.
class Client {
    private Parent p;

    public Client(Parent p) {
        this.p = p;
    }

    public void printMsg() {
        p.print("Hello");
    }
}

public class MethodArgumentRule {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Parent child  = new Child();

        Client client = new Client(parent);
        //Client client = new Client(child);

        client.printMsg();
    }
}
