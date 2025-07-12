# 🍔 Factory Design Patterns in Java

This repository demonstrates the three main **Factory Design Patterns** in Java using a Burger & Garlic Bread meal example.

---

## 🎯 What are Factory Design Patterns?

Factory Design Patterns are **creational patterns** that abstract the process of object creation. Instead of instantiating classes directly using `new`, factories encapsulate the logic needed to decide which class to instantiate — improving **flexibility**, **testability**, and **maintainability**.

---

## 🏗️ Types of Factory Patterns

| Pattern          | Description                                         | Use Case                                                                                       |
| ---------------- | --------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| Simple Factory   | One static method creates all products.             | When object creation logic is centralized in one class.                                        |
| Factory Method   | Subclasses decide which product to create.          | When you want to delegate creation logic to subclasses.                                        |
| Abstract Factory | A factory that creates families of related objects. | When multiple products (like UI components or meals) need consistent creation across variants. |

---

## 1️⃣ Simple Factory Pattern

A single static method that returns instances of related classes based on input.

### 🔧 Structure

```java
class BurgerFactory {
    public static Burger createBurger(String type) {
        if (type.equalsIgnoreCase("cheese")) return new CheeseBurger();
        if (type.equalsIgnoreCase("veggie")) return new VeggieBurger();
        if (type.equalsIgnoreCase("chicken")) return new ChickenBurger();
        return null;
    }
}
```

### 🧾 UML (Text)

```
           +------------------+
           |   BurgerFactory  |  <---- Static Creator
           +------------------+
           | +createBurger()  |
           +------------------+
                    |
                    ▼
    +-----------------------------+
    |       <<interface>>         |
    |           Burger            |
    +-----------------------------+
    | +prepare()                  |
    +-----------------------------+
          ▲        ▲         ▲
          |        |         |
     CheeseBurger  VeggieBurger  ChickenBurger
```

### ✅ Use Case

- Use when you want to encapsulate creation logic in a single place but don’t need extensibility via inheritance.

---

## 2️⃣ Factory Method Pattern

Here, the **factory itself is abstract**, and subclasses decide which concrete product to instantiate.

### 🔧 Structure

```java
interface BurgerFactory {
    Burger createBurger(String type);
}

class SinghBurger implements BurgerFactory {
    public Burger createBurger(String type) {
        return switch (type) {
            case "basic" -> new BasicBurger();
            case "standard" -> new StandardBurger();
            case "premium" -> new PremiumBurger();
            default -> null;
        };
    }
}
```

### 🧾 UML (Text)

```
        +---------------------+
        |  <<interface>>      |
        |   BurgerFactory     |
        +---------------------+
        | +createBurger(type) |
        +---------------------+
               ▲
        _______|_________
       |                 |
SinghBurger         KingBurger
       |                 |
       ▼                 ▼
  Returns Burger    Returns Burger
```

### ✅ Use Case

- When you want to **delegate the responsibility of object creation** to subclasses and use polymorphism for flexibility.

---

## 3️⃣ Abstract Factory Pattern

Provides an interface for creating families of related objects (e.g., `Burger`, `GarlicBread`) without specifying their concrete classes.

### 🔧 Structure

```java
interface MealFactory {
    Burger createBurger(BurgerType type);
    GarlicBread createGarlicBread(GarlicBreadType type);
}

class SinghBurger implements MealFactory {
    public Burger createBurger(BurgerType type) { ... }
    public GarlicBread createGarlicBread(GarlicBreadType type) { ... }
}
```

### 🧾 UML (Text)

```
               <<interface>>
             +---------------+
             |  MealFactory  |
             +---------------+
             | +createBurger |
             | +createBread  |
             +---------------+
                   ▲
       ┌───────────┴────────────┐
       |                        |
  SinghBurger              KingBurger
       |                        |
       ▼                        ▼
  +------------+          +------------+
  |  Burger    |          |  GarlicBread|
  +------------+          +------------+
```

### ✅ Use Case

- When you need to create **families of related products** that must be used together (e.g., all wheat-based items or all regular-based items).
- Example: creating consistent UI components across Light and Dark themes.

---

## ⚖️ Comparison Table

| Feature                    | Simple Factory        | Factory Method           | Abstract Factory             |
| -------------------------- | --------------------- | ------------------------ | ---------------------------- |
| Responsibility of Creation | One class             | Subclasses               | Concrete Factories           |
| Open for Extension?        | ❌ No                 | ✅ Yes                   | ✅ Yes                       |
| Supports Polymorphism      | ❌ No                 | ✅ Yes                   | ✅ Yes                       |
| Multiple Product Families  | ❌ No                 | ❌ No                    | ✅ Yes                       |
| Design Pattern Type        | Custom / Non GoF      | GoF (Creational)         | GoF (Creational)             |
| Ideal Use Case             | One centralized logic | Varying product creation | Families of related products |

---

## 📚 Real-World Analogies

- **Simple Factory**: Vending machine — choose an item, it gives it to you.
- **Factory Method**: Different stores (outlets) make burgers their own way.
- **Abstract Factory**: A meal combo — factory ensures all items (burger + side) match in style (wheat/regular).

---

## ✅ When to Use Which?

| Scenario                                              | Pattern to Use   |
| ----------------------------------------------------- | ---------------- |
| You want one method to create objects                 | Simple Factory   |
| You want different classes to decide instantiation    | Factory Method   |
| You want consistent product families (UI kits, meals) | Abstract Factory |

---

## 🧠 Key Takeaways

- Avoid `new` in client code; use factories for object creation.
- Improves **loose coupling** and adheres to **Open/Closed Principle**.
- Always choose the pattern based on **scalability and flexibility needs**.

---


_Happy coding! 🚀_
