/*
 * Chain of Responsibility Pattern
        Allow an object to pass a request along a chain of potential Handlers. Each handler in the chain decides either to process teh request or pass it to the next handler.
    
    Todo:   example -->  design a log system, employee leave approval.

    ! it like LinkedList
    ?composite design pattern --->  tree
 */

abstract class MoneyHandler {

    protected MoneyHandler nextHandler;

    public MoneyHandler() {
        this.nextHandler = null;
    }

    public void setNextHandler(MoneyHandler next) {
        this.nextHandler = next;
    }

    public abstract void dispense(int amount);
}

class ThousandHandler extends MoneyHandler {

    private int numNotes;

    public ThousandHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 1000;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (numNotes > 0) {
            System.out.println("Dispensing " + notesNeeded + " x ₹1000 notes. ");
        }

        int remainingAmount = amount - (notesNeeded * 1000);

        if (remainingAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainingAmount);
            } else {
                System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficient fund in ATM)");
            }
        }
    }
}

class FiveHundredHandler extends MoneyHandler {

    private int numNotes;

    public FiveHundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 500;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0) {
            System.out.println("Dispensing " + notesNeeded + " x ₹500 notes.");
        }

        int remainingAmount = amount - (notesNeeded * 500);
        if (remainingAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainingAmount); 
            }else {
                System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficient fund in ATM)");
            }
        }
    }
}

class TwoHundredHandler extends MoneyHandler {

    private int numNotes;

    public TwoHundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 200;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0) {
            System.out.println("Dispensing " + notesNeeded + " x ₹200 notes.");
        }

        int remainingAmount = amount - (notesNeeded * 200);
        if (remainingAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainingAmount); 
            }else {
                System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficient fund in ATM)");
            }
        }
    }
}

class HundredHandler extends MoneyHandler {

    private int numNotes;

    public HundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 100;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0) {
            System.out.println("Dispensing " + notesNeeded + " x ₹100 notes.");
        }

        int remainingAmount = amount - (notesNeeded * 100);
        if (remainingAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainingAmount); 
            }else {
                System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficient fund in ATM)");
            }
        }
    }
}

public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        // Create handlers
        MoneyHandler thousandHandler = new ThousandHandler(5); // 5 notes of ₹1000
        MoneyHandler fiveHundredHandler = new FiveHundredHandler(10); // 10 notes of ₹500
        MoneyHandler twoHundredHandler = new TwoHundredHandler(20); // 20 notes of ₹200
        MoneyHandler hundredHandler = new HundredHandler(50); // 50 notes of ₹100

        // Set up the chain
        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(twoHundredHandler);
        twoHundredHandler.setNextHandler(hundredHandler);

        // Request to dispense money
        int amountToDispense = 3700; // Example amount
        System.out.println("Requesting to dispense: ₹" + amountToDispense);
        thousandHandler.dispense(amountToDispense);
    }
}
