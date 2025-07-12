
public class ThreadSafeLockingSingleton {

    // Holds the single instance (lazy initialization)
    private static ThreadSafeLockingSingleton instance;

    //  Private constructor to prevent instantiation from other classes
    private ThreadSafeLockingSingleton() {
        System.out.println("ThreadSafeSingleton Constructor called");
    }

    // Returns the same instance every time, ensuring thread safety
    // by synchronizing the method
    public static ThreadSafeLockingSingleton getInstance() {
        synchronized (ThreadSafeLockingSingleton.class) { // 🔐 lock only the critical section
            if (instance == null) {
                instance = new ThreadSafeLockingSingleton();
            }
            return instance;
        }
    }

    //! ❌ Cons:
    // Still synchronizes on every call, even if the instance is already created, slow performance


    // Main method to test the singleton behavior
    public static void main(String[] args) {
        ThreadSafeLockingSingleton s1 = ThreadSafeLockingSingleton.getInstance();
        ThreadSafeLockingSingleton s2 = ThreadSafeLockingSingleton.getInstance();

        System.out.println(s1 == s2); // ✅ true — same instance
    }
}
