package Projects.ZomatoClone.strategies;

public class PushNotificationSender implements  INotificationSender{
    @Override
    public void sendNotification(String userName, String message) {
        System.out.println("🔔 Push to " + userName + ": \"" + message + "\"");
    }
}
