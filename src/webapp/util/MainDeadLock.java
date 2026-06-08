package webapp.util;

public class MainDeadLock {
    public static final String WAITS = "ожидает";
    public static final String HOLDS = "удерживает";

    static void main(String[] args) {
        final String res1 = "resource 1";
        final String res2 = "resource 2";
        deadLock(res1, res2);
        deadLock(res2, res1);
    }

    private static void deadLock(String res1, String res2) {
        new Thread(() -> {
            printThreadInfo(WAITS, res1);
            synchronized (res1) {
                printThreadInfo(HOLDS, res1);

                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                printThreadInfo(WAITS, res2);
                synchronized (res2) {
                    printThreadInfo(HOLDS, res2);
                }
            }
        }).start();
    }

    private static void printThreadInfo(String operation, String res) {
        System.out.println(Thread.currentThread().getName() + " " + operation + " " + res);
    }
}