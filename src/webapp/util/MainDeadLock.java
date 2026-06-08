package webapp.util;

public class MainDeadLock {
    private static final String RES_1 = "resource 1";
    private static final String RES_2 = "resource 2";

    static void main(String[] args) {
        new Thread(() -> checkResources(RES_1, RES_2)).start();
        new Thread(() -> checkResources(RES_2, RES_1)).start();
    }

    private static void checkResources(String res1, String res2) {
        synchronized (res1) {
            printInfo(res1);

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            synchronized (res2) {
                printInfo(res2);
            }
        }
    }

    private static void printInfo(String res) {
        System.out.println(Thread.currentThread().getName() + " захватил " + res);
    }
}