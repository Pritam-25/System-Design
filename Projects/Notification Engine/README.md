# 🧠 Notification System - Full Design Walkthrough

> A **Decorator + Strategy + Observer + Singleton** based notification system, crafted for **modularity**, **extensibility**, and **real-world applicability**.

## 📋 Table of Contents

- [Motivation](#-motivation)
- [Step-by-Step Design & Build](#️-step-by-step-design--build)
- [Execution Flow](#-execution-flow-step-by-step)
- [Why This Design?](#-why-this-design-pros)
- [What Not to Do](#-what-not-to-do)
- [Future Enhancements](#-future-enhancements)
- [Summary](#-summary)
- [Class Diagram](#-class-diagram)
- [Usage](#-usage)
- [Acknowledgements](#-acknowledgements)

---

## 📌 Motivation

Modern applications require **dynamic notification systems**. These systems:

- Must support **multiple delivery channels** (Email, SMS, Popups…)
- Should allow **chaining decorators** (like adding timestamps or signatures)
- Should **react automatically** when new notifications arrive
- And be **easy to extend**, without rewriting everything

So, instead of just writing `sendEmail("Hi")`, we built a **production-grade notification engine** using design patterns. Here's how we built it — one step at a time.

---

## 🛠️ Step-by-Step Design & Build

---

### ✅ Step 1: Core Abstraction - `INotification`

We start with the heart of the system: `INotification`.

```cpp
class INotification {
    virtual string getContent() = 0;
};
```

This is an **interface** for anything that can represent a notification. Think of it as a "blueprint" for notification messages.

---

### ✅ Step 2: Basic Message - `SimpleNotification`

Now we need a simple message type.

```cpp
class SimpleNotification : public INotification {
    string text;
    string getContent() override { return text; }
};
```

This gives us our raw message like `"Welcome to the app!"`.

---

### ✅ Step 3: 🎨 Decorators - Adding Timestamp & Signature

We want to **dynamically add info** to a message like:

- `[2025-07-15] Welcome`
- `Welcome - Team App`

To avoid creating dozens of subclasses (e.g., `TimestampAndSignatureNotification`), we use the **Decorator Pattern**.

```cpp
class INotificationDecorator : public INotification {
protected:
    INotification* notif;
};

class TimestampDecorator : public INotificationDecorator {
    string getContent() override {
        return "[Time] " + notif->getContent();
    }
};

class SignatureDecorator : public INotificationDecorator {
    string getContent() override {
        return notif->getContent() + " - Team App";
    }
};
```

Now we can chain:

```cpp
notif = new SignatureDecorator(new TimestampDecorator(new SimpleNotification("Hi")));
```

---

### ✅ Step 4: 👀 Observer Pattern - Auto-Reactive System

When a new notification is created, multiple components should react (like loggers or delivery systems). We use **Observer Pattern**.

```cpp
class IObservable {
    vector<IObserver*> observers;
    void add(IObserver* o);
    void remove(IObserver* o);
    void notify();  // calls update on all observers
};

class IObserver {
    virtual void update() = 0;
};
```

---

### ✅ Step 5: Observable Wrapper - `NotificationObservable`

```cpp
class NotificationObservable : public IObservable {
    INotification* notif;
    void setNotification(INotification* n);
    string getNotification();
};
```

This acts as the **shared subject** that observers listen to.

---

### ✅ Step 6: Logger Observer - `Logger`

```cpp
class Logger : public IObserver {
    NotificationObservable* no;
    void update() override {
        cout << "LOG: " << no->getNotification();
    }
};
```

This will print every notification received — for debugging or logging purposes.

---

### ✅ Step 7: 🎯 Strategy Pattern - Send Notification by Channel

We now want **multiple ways to send** a notification: SMS, Email, Popup.

```cpp
class INotificationStrategy {
    virtual void sendNotification(string content) = 0;
};

class EmailStrategy : public INotificationStrategy {
    void sendNotification(string content) { sendEmail(content); }
};
```

You can now extend this system without changing a single existing class!

---

### ✅ Step 8: Notification Engine - `NotificationEngine`

This is the **main controller**.

```cpp
class NotificationEngine : public IObserver {
    NotificationObservable* no;
    vector<INotificationStrategy*> strategies;

    void update() override {
        string content = no->getNotification();
        for (auto s : strategies) {
            s->sendNotification(content);
        }
    }
};
```

It listens for updates, grabs the content, and pushes it to all delivery strategies.

---

### ✅ Step 9: Singleton - `NotificationService`

We want a **global access point** that maintains all notifications sent so far.

```cpp
class NotificationService {
    static NotificationService* instance;
    vector<INotification*> notifications;

    void sendNotification() {
        // store notification
        // pass to observable
    }
};
```

This avoids unnecessary duplication and gives centralized access.

---

## 🧵 Execution Flow (Step-by-Step)

1. **Client builds a notification**:

   ```cpp
   INotification* notif = new SignatureDecorator(
                            new TimestampDecorator(
                            new SimpleNotification("Welcome")));

   NotificationService::getInstance()->sendNotification(notif);
   ```

2. `NotificationService::sendNotification()` sets this `notif` in `NotificationObservable`.

3. `NotificationObservable::notify()` calls `update()` on all registered observers.

4. - `Logger::update()` logs the content.
   - `NotificationEngine::update()` pulls the content and pushes it via each delivery strategy:

     - Email
     - SMS
     - Pop-up

---

## ✅ Why This Design? Pros

| Feature                                 | Design Choice                         |
| --------------------------------------- | ------------------------------------- |
| Add/remove message metadata dynamically | ✅ **Decorator Pattern**              |
| Multiple delivery options               | ✅ **Strategy Pattern**               |
| Log + Send both                         | ✅ **Observer Pattern**               |
| One instance controlling everything     | ✅ **Singleton Pattern**              |
| Easy to extend                          | ✅ **Open/Closed Principle**          |
| No tight coupling                       | ✅ **Dependency Inversion Principle** |

---

## ❌ What Not to Do

| Mistake                              | Why It’s Bad                         |
| ------------------------------------ | ------------------------------------ |
| Hardcode delivery logic in engine    | Violates SRP and makes it untestable |
| Inherit for every combo of decorator | Leads to **class explosion**         |
| Use multiple global variables        | Breaks **Singleton + Clean Access**  |
| Mix UI (like Popups) in core classes | Hurts modularity                     |

---

## 🚀 Future Enhancements

- ✅ Add RetryStrategy if SMS fails
- ✅ Allow scheduling of notifications (Delayed send)
- ✅ Webhooks or 3rd party integrations (e.g., Discord/Slack)
- ✅ Admin dashboard to toggle strategies
- ✅ Persistent logging to file or DB

---

## 📎 Summary

This system combines **four major design patterns**:

- 🧩 **Decorator**: Enhances content
- 🔄 **Strategy**: Sends via different mediums
- 🛰 **Observer**: Notifies all listeners
- 🔐 **Singleton**: Manages centralized sending

And the best part? It's easily extensible, readable, and modular.

---

## 📊 Class Diagram

```mermaid
---

---
classDiagram
direction LR
    class INotification {
	    +getContent() : string
    }
    class INotificationDecorator {
	    +INotification* notif
	    +getContent() : string
    }
    class IObservable {
	    +vector~IObserver*~ observers
	    +add(IObserver* o)
	    +remove(IObserver* o)
	    +notify()
    }
    class IObserver {
	    +update()
    }
    class INotificationStrategy {
	    +sendNotification(string content)
    }
    class SimpleNotification {
	    +string text
	    +getContent() : string
    }
    class TimestampDecorator {
	    +INotification* notif
	    +getContent() : string
    }
    class SignatureDecorator {
	    +INotification* notif
	    +getContent() : string
    }
    class NotificationObservable {
	    +INotification* notif
	    +add(IObserver* o)
	    +remove(IObserver* o)
	    +notify()
	    +setNotification(INotification* n)
	    +getNotification() : string
    }
    class NotificationEngine {
	    +NotificationObservable* no
	    +vector~INotificationStrategy*~ ns
	    +update()
    }
    class Logger {
	    +NotificationObservable* no
	    +update()
    }
    class NotificationService {
	    +vector~INotification*~ notifications
	    +sendNotification()
    }
    class EmailStrategy {
	    +sendNotification(string content)
    }
    class SMSStrategy {
	    +sendNotification(string content)
    }
    class PopUpStrategy {
	    +sendNotification(string content)
    }
	<<abstract>> INotification
	<<abstract>> INotificationDecorator
	<<abstract>> IObservable
	<<abstract>> IObserver
	<<abstract>> INotificationStrategy
	<<Singleton>> NotificationService
    INotification <|-- SimpleNotification
    INotification <|-- INotificationDecorator
    INotificationDecorator <|-- TimestampDecorator
    INotificationDecorator <|-- SignatureDecorator
    IObservable <|-- NotificationObservable
    IObserver <|-- Logger
    IObserver <|-- NotificationEngine
    INotificationStrategy <|-- EmailStrategy
    INotificationStrategy <|-- SMSStrategy
    INotificationStrategy <|-- PopUpStrategy
    NotificationEngine --> NotificationObservable
    NotificationEngine --> INotificationStrategy
    Logger --> NotificationObservable
    NotificationObservable --> INotification
    NotificationService --> INotification
    NotificationObservable --> IObservable : implements
    Logger --> IObserver : implements
    NotificationEngine --> IObserver : implements
```

## 📱 Usage

```cpp
// Create a basic notification
auto notification = new SimpleNotification("Welcome to our service!");

// Add decorators
notification = new TimestampDecorator(notification);
notification = new SignatureDecorator(notification);

// Send through the notification service
NotificationService::getInstance()->sendNotification(notification);
```

---

> ✅ Made for revision. Come back anytime to refresh your concepts before an interview or implementation.

---

## 💖 Acknowledgements

Built with passion and precision by Pritam. Transforming complex design patterns into elegant, maintainable code.
