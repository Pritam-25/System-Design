public class WrongSingleton {

    // ❌ This does not implement the Singleton pattern correctly

    public WrongSingleton() {
        System.out.println("Constructor called");
    }

    public static WrongSingleton getInstance() {
        return new WrongSingleton(); // ❌ Returns a new instance every time
    }

    public static void main(String[] args) {
        WrongSingleton s1 = WrongSingleton.getInstance();
        WrongSingleton s2 = WrongSingleton.getInstance();

        System.out.println(s1 == s2); // ❌ false — different instances
    }
}
