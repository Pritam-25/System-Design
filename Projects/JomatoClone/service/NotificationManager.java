package Projects.ZomatoClone.service;

import Projects.ZomatoClone.strategies.INotificationSender;

public class NotificationManager {
    private static final NotificationManager instance = new NotificationManager();

    private NotificationManager() {
    }

    public static NotificationManager getInstance() {
        return instance;
    }

    public void notifyUser(String userName, String message, INotificationSender sender) {
        // Logic to send notification to the user
        sender.sendNotification(userName, message);
    }
}
