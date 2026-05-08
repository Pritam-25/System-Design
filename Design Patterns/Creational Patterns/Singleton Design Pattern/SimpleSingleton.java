public class SimpleSingleton {

    // ✅ Holds the single instance (lazy initialization)
    private static SimpleSingleton instance;

    // ✅ Private constructor to prevent instantiation from other classes
    private SimpleSingleton() {
        System.out.println("Singleton Constructor called");
    }

    // ✅ Returns the same instance every time
    public static SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        SimpleSingleton s1 = SimpleSingleton.getInstance();
        SimpleSingleton s2 = SimpleSingleton.getInstance();

        System.out.println(s1 == s2); // ✅ true — same instance
    }
}
