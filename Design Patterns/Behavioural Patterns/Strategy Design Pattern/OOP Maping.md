# 🎯 OOP Concepts Inside Strategy Design Pattern

Strategy Pattern is one of the best examples to understand:

- Encapsulation
- Polymorphism
- Abstraction
- Composition

all together.

The Strategy pattern encapsulates algorithms and makes them interchangeable through polymorphism. ([gofpattern.com][1])

## 📦 Full Code Example

```java id="q3u6rx"
interface PaymentStrategy {
    void pay(int amount);
}
```

### Concrete Strategies

```java id="k2kg0v"
class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("Paid using Credit Card");
    }
}
```

```java id="r7h1u4"
class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("Paid using UPI");
    }
}
```

### Context Class

```java id="6bhql6"
class PaymentService {

    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(int amount) {
        strategy.pay(amount);
    }
}
```

## 🧠 1. Where Encapsulation Happens

### 📍 Encapsulation #1 — Algorithm Hidden Inside Strategy

```java id="8qxh2g"
class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(int amount) {

        // validation
        // bank API
        // transaction logic
        // fraud check

        System.out.println("Paid using Credit Card");
    }
}
```

### 🧠 Meaning

All payment logic is hidden INSIDE:

```text id="wt6q4r"
CreditCardPayment
```

PaymentService does NOT know:

- bank API calls
- transaction validation
- OTP verification
- card processing logic

It only knows:

```java id="wzt0bo"
strategy.pay(amount);
```

That hiding of implementation details is:

### ✅ Encapsulation

Encapsulation hides internal implementation and exposes only required behavior. ([clear.rice.edu][2])

### 📍 Encapsulation #2 — Private Strategy Field

```java id="vjlwmk"
private PaymentStrategy strategy;
```

This is ALSO encapsulation.

Why?

Because outside classes cannot directly modify internal state.

Only controlled access allowed through:

```java id="q2py9t"
setStrategy()
```

### 🔥 Encapsulation Here Means

```text id="bh0jpb"
Hide HOW payment works
Expose only WHAT to do
```

## 🧠 2. Where Polymorphism Happens

### 📍 THIS LINE

```java id="4z25j6"
PaymentStrategy strategy = new UpiPayment();
```

This is polymorphism.

### 🔥 Why?

Because:

```text id="wtm32t"
Reference type  = PaymentStrategy
Actual object   = UpiPayment
```

One interface reference can hold many forms:

- CreditCardPayment
- UpiPayment
- PaypalPayment

That is:

### ✅ Polymorphism

Meaning:

```text id="mbrydc"
“One interface, many implementations”
```

### 📍 Runtime Polymorphism Happens Here

```java id="1z1nlt"
strategy.pay(amount);
```

At compile time:

Java only knows:

```text id="cf2t6y"
strategy is PaymentStrategy
```

At runtime:

Java decides:

```text id="4rbjlwm"
Which actual object's pay() should execute?
```

### 🔄 Example

If:

```java id="0ljlwm"
strategy = new CreditCardPayment();
```

Then:

```java id="7as3n6"
strategy.pay()
```

calls:

```java id="hrfif2"
CreditCardPayment.pay()
```

If later:

```java id="mnx1mo"
strategy = new UpiPayment();
```

Now SAME line:

```java id="6d7hkp"
strategy.pay()
```

calls:

```java id="5a1sp5"
UpiPayment.pay()
```

WITHOUT changing client code.

That is:

### ✅ Runtime Polymorphism (Dynamic Method Dispatch)

Strategy pattern heavily relies on runtime polymorphism. ([Stack Overflow][3])

## 🔥 VERY IMPORTANT INTERVIEW UNDERSTANDING

### Encapsulation = Hiding Implementation

```text id="61c1z0"
“How payment works internally”
```

is hidden inside concrete strategy classes.

### Polymorphism = One Interface Many Behaviors

```text id="1c1r6q"
PaymentStrategy
```

can behave differently based on actual object.

### 📌 In One Sentence

```text id="itwbrh"
Strategy Pattern uses encapsulation to hide algorithms and polymorphism to switch algorithms dynamically.
```

That is the BEST interview line.

## 🧠 Where Abstraction Happens

### 📍 Interface

```java id="mjlwmu"
interface PaymentStrategy {
    void pay(int amount);
}
```

This is abstraction.

It defines:

```text id="n02fh3"
WHAT should happen
```

NOT:

```text id="wkpc5k"
HOW it happens
```

## 🧠 Where Composition Happens

### 📍 Here

```java id="fdcexj"
private PaymentStrategy strategy;
```

PaymentService HAS-A strategy.

That is:

### ✅ Composition

NOT inheritance.

## 📊 Full OOP Mapping

| OOP Concept   | Where in Strategy Pattern              |
| ------------- | -------------------------------------- |
| Encapsulation | payment logic hidden inside strategies |
| Abstraction   | PaymentStrategy interface              |
| Polymorphism  | strategy.pay() resolved at runtime     |
| Composition   | PaymentService HAS-A strategy          |

## 🎯 Senior-Level Understanding

Strategy Pattern is basically:

```text id="i2xjlwm"
Encapsulated runtime polymorphism using composition.
```

That is the deepest clean definition.

## 🚀 Final Interview Answer

> “In Strategy Pattern, encapsulation happens because each algorithm is hidden inside its own strategy class, while runtime polymorphism happens when the context interacts with strategies through a common interface and the actual method execution is decided dynamically at runtime.” ([gofpattern.com][1])

[1]: https://www.gofpattern.com/behavioral/patterns/strategy-pattern.php?utm_source=chatgpt.com "Strategy Pattern (Family of Algorithms)"
[2]: https://www.clear.rice.edu/comp310/JavaResources/patterns/strategy.html?utm_source=chatgpt.com "Strategy Pattern"
[3]: https://stackoverflow.com/questions/31608902/polymorphism-vs-strategy-pattern?utm_source=chatgpt.com "java - Polymorphism vs Strategy pattern - Stack Overflow"
