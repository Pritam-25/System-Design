
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Notification & Decorators
interface INotification {

    String getContent();
}

// Concrete Notification simple text notification
class SimpleNotification implements INotification {

    private String text;

    public SimpleNotification(String msg) {
        this.text = msg;
    }

    @Override
    public String getContent() {
        return text;
    }

}

// Abstract Decorator: wraps a Notification object.
abstract class INotificationDecorator implements INotification {

    protected INotification notification;

    public INotificationDecorator(INotification notification) {
        this.notification = notification;
    }
}

// Decorator to add a timestamp to the message;
class TimestampDecorator extends INotificationDecorator {

    public TimestampDecorator(INotification notification) {
        super(notification);
    }

    @Override
    public String getContent() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        return "[" + formattedDateTime + "] " + notification.getContent();
    }
}

// Decorator to add signature to the message
class SignatureDecorator extends INotificationDecorator {

    private String signature;

    public SignatureDecorator(INotification notification, String signature) {
        super(notification);
    }

    @Override
    public String getContent() {
        return notification.getContent() + "\n-- " + signature + "\n\n";
    }
}


//___________________________
// Observer Pattern Components

// observer interface: each observer gets an update with notification reference
interface IObserver{
    void update();
}

interface IObservable{
    void addObserver(IObserver observer);
}

public class NotificationService {

}
