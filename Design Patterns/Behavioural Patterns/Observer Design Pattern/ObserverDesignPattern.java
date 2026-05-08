//! Observer Design Pattern:
// Define a one-to-many relationship between objects so that when one object changes state, all of its dependents are notified, and updated automatically.

import java.util.ArrayList;
import java.util.List;

interface ISubscriber {
    void update();
}

// Observable interface: a YouTube channel interface
interface IChannel {
    void subscribe(ISubscriber subscriber);

    void unSubscribe(ISubscriber subscriber);

    void notifySubscribers();
}

// Concrete Subject: a YouTube channel that observers can subscribe to
class Channel implements IChannel {
    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        if (!subscribers.contains(subscriber))
            subscribers.add(subscriber);
    }

    @Override
    public void unSubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (ISubscriber sub : subscribers) {
            sub.update();
        }
    }

    public void uploadVideo(String videoTitle) {
        latestVideo = videoTitle;
        System.out.println("\n[ " + name + " uploaded \"" + videoTitle + "\"");
        notifySubscribers();
    }

    public String getVideo() {
        return "\nCheckout our new Video: " + latestVideo + "\n";
    }
}

// Concrete Observer: represents a subscriber to the channel
class Subscriber implements ISubscriber {
    private String name;
    private Channel channel;

    public Subscriber(String name, Channel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update() {
        System.out.println("Hey" + name + ", " + channel.getVideo());
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        Channel channel1 = new Channel("CoderArmy");

        Subscriber subs1 = new Subscriber("Pritam", channel1);
        Subscriber subs2 = new Subscriber("Sunny", channel1);

        // Pritam and Sunny subscribe to CoderArmy
        channel1.subscribe(subs1);
        channel1.subscribe(subs2);

        // Upload a video: both Pritam and Sunny are notified
        channel1.uploadVideo("Observer Pattern Tutorial");

        // Sunny unsubscribes: Pritam remain subscribed
        channel1.unSubscribe(subs2);

        // Upload another video: only Pritam gets the notification
        channel1.uploadVideo("Decorator Pattern Tutorial");
    }
}


/*
[ CoderArmy uploaded "Observer Pattern Tutorial"
HeyPritam, 
Checkout our new Video: Observer Pattern Tutorial

HeySunny,
Checkout our new Video: Observer Pattern Tutorial


[ CoderArmy uploaded "Decorator Pattern Tutorial"
HeyPritam,
Checkout our new Video: Decorator Pattern Tutorial
 */