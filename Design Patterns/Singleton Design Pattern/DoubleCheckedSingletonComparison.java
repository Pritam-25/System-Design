// Double-Checked Locking for better performance
public class DoubleCheckedSingletonComparison {

    // -----------------------------
    // ✅ Version 1: Textbook version (Classic)
    // -----------------------------
    static class ClassicSingleton {

        private static volatile ClassicSingleton instance;

        private ClassicSingleton() {
            System.out.println("ClassicSingleton Constructor called");
        }

        public static ClassicSingleton getInstance() {
            if (instance == null) {
                synchronized (ClassicSingleton.class) { // 🔐 lock only the critical section, not use local instance variable
                    if (instance == null) {
                        instance = new ClassicSingleton();
                    }
                }
            }
            return instance;
        }
    }

    // -----------------------------
    // ✅ Version 2: Optimized with local variable (Your Version)
    // -----------------------------
    static class OptimizedSingleton {

        private static volatile OptimizedSingleton instance;

        private OptimizedSingleton() {
            System.out.println("OptimizedSingleton Constructor called");
        }

        public static OptimizedSingleton getInstance() {
            OptimizedSingleton localRef = instance; //✅  cache volatile read, use local instance variable
            if (localRef == null) {
                synchronized (OptimizedSingleton.class) {
                    localRef = instance;
                    if (localRef == null) {
                        instance = localRef = new OptimizedSingleton();
                    }
                }
            }
            return localRef;
        }
    }

    public static void main(String[] args) {
        System.out.println("== Optimized Singleton Test ==");
        OptimizedSingleton o1 = OptimizedSingleton.getInstance();
        OptimizedSingleton o2 = OptimizedSingleton.getInstance();
        System.out.println("OptimizedSingleton same instance? " + (o1 == o2)); // true

        System.out.println("\n== Classic Singleton Test ==");
        ClassicSingleton c1 = ClassicSingleton.getInstance();
        ClassicSingleton c2 = ClassicSingleton.getInstance();
        System.out.println("ClassicSingleton same instance? " + (c1 == c2)); // true
    }
}
