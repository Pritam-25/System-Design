
/*
✅ Core Rule: Exception Rule in Overriding
--------------------------------------------
?When overriding a method, the subclass can:
🔹Throw fewer exceptions.
🔹Throw narrower (subclass) exceptions.
🔹Throw unchecked exceptions (subclasses of RuntimeException) freely.

!But it cannot:
🔹Throw new checked exceptions not declared in the parent method.
🔹Throw a broader checked exception than the parent.

Java only enforces this rule for checked exceptions (i.e., subclasses of Exception that are not RuntimeException).
 */


/* 
└── java.lang.Exception                        // Conditions your application might want to catch
    ├── java.io.IOException                    // Checked I/O failures
    │   ├── java.io.FileNotFoundException
    │   ├── java.io.EOFException
    │   └── java.net.MalformedURLException
    ├── java.lang.ClassNotFoundException       // Checked reflect/… failures
    ├── java.lang.InterruptedException         // Checked thread interruption
    ├── java.sql.SQLException                  // Checked SQL/database errors
    ├── java.text.ParseException               // Checked parsing errors
    └── java.lang.RuntimeException             // Unchecked; subclasses may be thrown anywhere
        ├── java.lang.ArithmeticException      // e.g. divide by zero
        ├── java.lang.NullPointerException
        ├── java.lang.ArrayIndexOutOfBoundsException
        ├── java.lang.StringIndexOutOfBoundsException
        ├── java.lang.IllegalArgumentException
        │    └── java.lang.NumberFormatException
        ├── java.lang.IllegalStateException
        ├── java.lang.UnsupportedOperationException
        └── java.lang.IndexOutOfBoundsException // parent of the two “…OutOfBounds” above
*/

class Parent {
    public void getValue() throws RuntimeException {
        throw new RuntimeException("Parent error");
    }
}

// Subclass overrides getValue and throws the narrower ChildException
class Child extends Parent {
    @Override
    public void getValue() throws ArithmeticException {
        throw new ArithmeticException("Child error");
        // throw new Exception("Child error"); // This is wrong & not allowed
    }
}

// Client that invokes getValue and catches the parent exception type
class Client {
    private Parent p;

    public Client(Parent p) {
        this.p = p;
    }

    public void takeValue() {
        try {
            p.getValue();
        } catch (RuntimeException e) {
            System.out.println("RuntimeException occurred: " + e.getMessage());
        }
    }
}

public class ExceptionRule {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child   = new Child();

        Client client = new Client(parent);
        //Client client = new Client(child);
        
        client.takeValue();  
    }
}