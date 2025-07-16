
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/*
----------------------------------
     Notification & Decorators
__________________________________
 */
interface INotification {

    String getMessage();
}

// Concrete NOtification: simple text notification
class SimpleNotification implements INotification {

    private String message;

    public SimpleNotification(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

// Abstract Decorator: wraps a Notification object.
abstract class INotificationDecorator implements INotification {

    protected INotification notification;

    public INotificationDecorator(INotification notification) {
        this.notification = notification;
    }

    @Override
    public String getMessage() {
        return notification.getMessage();
    }
}

// Decorator to add a timestamp to the notification
class TimestampDecorator extends INotificationDecorator {

    public TimestampDecorator(INotification notification) {
        super(notification);
    }

    @Override
    public String getMessage() {
        // Import these at the top of your file:
        // import java.time.LocalDateTime;
        // import java.time.format.DateTimeFormatter;
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        
        return "[" + formattedDateTime + "] " + notification.getMessage();
    }
}

// Decorator to append a signature to the notification.
class SignatureDecorator extends INotificationDecorator {

    private String signature;

    public SignatureDecorator(INotification notification, String signature) {
        super(notification);
        this.signature = signature;
    }

    @Override
    public String getMessage() {
        return notification.getMessage() + " - " + signature;
    }
}


/*
 ----------------------------------------
    Observer Pattern Components
 ----------------------------------------
 */
interface IObserver {

    void update();
}

interface IObservable {

    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers();
}

// Concrete Observable: Notification Engine that manages observers.
class NotificationObservable implements IObservable {

    private List<IObserver> observers = new ArrayList<>();
    private INotification currentNotification;

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }

    public void setNotification(INotification notification) {
        this.currentNotification = notification;
        notifyObservers();
    }

    public INotification getCurrentNotification() {
        return currentNotification;
    }

    public String getNotificationMessage() {
        if (currentNotification != null) {
            return currentNotification.getMessage();
        }
        return "No notification set.";
    }

}

// Concrete Observer 1
class Logger implements IObserver {

    private NotificationObservable observable;

    public Logger(NotificationObservable observable) {
        this.observable = observable;
    }

    @Override
    public void update() {
        System.out.println("Logger: New notification received - " + observable.getNotificationMessage());
    }
}


/*
  -----------------------------------------------------
    Strategy Pattern Components (Concrete Observer 2)
  -----------------------------------------------------
 */
interface INotificationStrategy {

    void sendNotification(String context);
}

class EmailStrategy implements INotificationStrategy {

    private String emailAddress;

    public EmailStrategy(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void sendNotification(String context) {
        System.out.println("Sending Email Notification to " + emailAddress + ": " + context);
    }
}

class SMSStrategy implements INotificationStrategy {

    private String phoneNumber;

    public SMSStrategy(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(String context) {
        System.out.println("Sending SMS Notification to " + phoneNumber + ": " + context);
    }
}

class PopupStrategy implements INotificationStrategy {

    @Override
    public void sendNotification(String context) {
        System.out.println("Showing Popup Notification: " + context);
    }
}

class NotificationEngine implements IObserver {

    private NotificationObservable observable;
    private List<INotificationStrategy> strategies = new ArrayList<>();

    public NotificationEngine(NotificationObservable observable) {
        this.observable = observable;
    }

    public void addNotificationStrategy(INotificationStrategy strategy) {
        strategies.add(strategy);
    }

    @Override
    public void update() {
        String notificationMessage = observable.getNotificationMessage();
        for (INotificationStrategy strategy : strategies) {
            strategy.sendNotification(notificationMessage);
        }
    }
}

/*
 --------------------------------------
    Notification Service
 --------------------------------------
 */
// The NotificationService manages notifications. It keeps track of notifications.
// Any client code will interact with this service to send notifications.
// It can use the NotificationEngine to send notifications using different strategies.
// Singleton class
class NotificationService {

    private static NotificationService instance;
    private NotificationObservable observable;
    private List<INotification> notifications = new ArrayList<>();

    public NotificationService() {
        observable = new NotificationObservable();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public NotificationObservable getObservable() {
        return observable;
    }

    public void sendNotification(INotification notification) {
        observable.setNotification(notification);
        notifications.add(notification);
    }
}

public class NotificationSystem {

    public static void main(String[] args) {
        // Create NotificationService instance
        NotificationService notificationService = NotificationService.getInstance();

        // Get Observable from NotificationService
        NotificationObservable observable = notificationService.getObservable();

        // Create Logger observer
        Logger logger = new Logger(observable);

        // Create NotificationEngine observers.
        NotificationEngine notificationEngine = new NotificationEngine(observable);

        notificationEngine.addNotificationStrategy(new EmailStrategy("maityp394@gmail.com"));
        notificationEngine.addNotificationStrategy(new SMSStrategy("7001104826"));
        notificationEngine.addNotificationStrategy(new PopupStrategy());

        // Add observers to the observable
        observable.addObserver(logger);
        observable.addObserver(notificationEngine);

        // Create a notification with decorators
        INotification notification = new SimpleNotification("Your order has been shipped!");
        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");

        // Send the notification
        notificationService.sendNotification(notification);
    }

}
