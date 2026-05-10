# 🔒 Singleton Design Pattern in Java

The Singleton Pattern ensures a class has **only one instance** and provides a **global point of access** to it.

## 🌀 What is the Singleton Pattern?

>The Singleton Design Pattern is a **creational pattern** that restricts the instantiation of a class to one single object. This is useful when exactly **one object is needed to coordinate actions** across a system.

## 🧠 Key Characteristics

- **Single Instance**: Only one instance is ever created.
- **Global Access Point**: Provides a static method for accessing that instance.
- **Thread-Safe**: In multithreaded environments, care must be taken to ensure only one instance is created.

## 🧩 How to Implement Singleton in Java?

There are **multiple ways** to implement a Singleton:

## 1️⃣ Eager Initialization

```java
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("Constructor Called");
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
```

### ✅ Pros:

- Simple and thread-safe
- No synchronization overhead

### ❌ Cons:

- Instance is created even if it’s never used (wastes memory/resources)

## 2️⃣ Lazy Initialization (Non-thread-safe ❌)

```java
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

### ❌ Not Thread-Safe:

- Multiple threads could create multiple instances simultaneously.

## 3️⃣ Thread-Safe Singleton (Synchronized Method)

```java
public class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {}

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
```

### ✅ Pros:

- Thread-safe

### ❌ Cons:

- Slower due to method-level synchronization on every call

## 4️⃣ Double-Checked Locking Singleton ✅

```java
public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {
        DoubleCheckedSingleton local = instance;
        if (local == null) {
            synchronized (DoubleCheckedSingleton.class) {
                local = instance;
                if (local == null) {
                    instance = local = new DoubleCheckedSingleton();
                }
            }
        }
        return local;
    }
}
```

### ✅ Pros:

- High performance
- Lazy initialization
- Thread-safe

## 5️⃣ Singleton using Enum ✅🔥

```java
public enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

### ✅ Pros:

- Thread-safe
- Serialization safe
- Reflection safe

### ✅ Recommended by **Effective Java** (Joshua Bloch)

## ⚖️ Comparison Table

| Approach               | Thread-Safe | Lazy   | Serialization Safe | Performance | Complexity |
| ---------------------- | ----------- | ------ | ------------------ | ----------- | ---------- |
| Eager Initialization   | ✅ Yes      | ❌ No  | ✅ Yes             | ✅ Fast     | ✅ Simple  |
| Lazy (non-thread safe) | ❌ No       | ✅ Yes | ❌ No              | ✅ Fast     | ✅ Simple  |
| Synchronized Method    | ✅ Yes      | ✅ Yes | ❌ No              | ❌ Slow     | ✅ Simple  |
| Double-Checked Locking | ✅ Yes      | ✅ Yes | ❌ No              | ✅ Fast     | ⚠️ Medium  |
| Enum Singleton         | ✅ Yes      | ❌ No  | ✅ Yes             | ✅ Fast     | ✅ Easiest |

## 🧪 Real-World Use Cases

| Use Case                 | Why Singleton?                               |
| ------------------------ | -------------------------------------------- |
| Database Connection Pool | Only one pool manager should exist           |
| Logger Class             | Only one logging service needed system-wide  |
| Configuration Manager    | One shared config manager across all modules |
| Thread Pool              | Shared across application                    |
| Cache Manager            | Global memory cache instance                 |

## ⚠️ Pitfalls to Avoid

| Mistake                            | Problem                                                |
| ---------------------------------- | ------------------------------------------------------ |
| Not making the constructor private | Multiple instances can be created                      |
| Missing thread safety              | Leads to inconsistent state in multithreaded apps      |
| Forgetting serialization logic     | `readResolve()` is needed if not using enum            |
| Using too many singletons          | Becomes a disguised global variable (bad design smell) |

## ✅ Best Practices

- Prefer **Enum Singleton** where possible
- Use **Double-Checked Locking** only when performance matters
- Avoid Singleton **for shared mutable state** in multi-threaded code unless it's safe
- Consider **Dependency Injection** as a cleaner alternative

## 🙋🏻‍♂️ Author

Built with ❤️ by Pritam


