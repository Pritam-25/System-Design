package Projects.ZomatoClone.strategies;

public class SMSNotificationSender implements INotificationSender{

    @Override
    public void sendNotification(String userName, String message) {
        System.out.println("📩 SMS to " + userName + ": \"" + message + "\"");
    }
}
