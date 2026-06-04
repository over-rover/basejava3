package webapp.util;

public class MainDeadLock {
    private static final Object OBJ_1 = new Object();
    private static final Object OBJ_2 = new Object();

    static void main(String[] args) {
        new Thread(() -> {
            synchronized (OBJ_1) {
                System.out.println(Thread.currentThread().getName() + " захватил obj1");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (OBJ_2) {
                    System.out.println(Thread.currentThread().getName() + " захватил obj2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (OBJ_2) {
                System.out.println(Thread.currentThread().getName() + " захватил obj2");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (OBJ_1) {
                    System.out.println(Thread.currentThread().getName() + " захватил obj1");
                }
            }
        }).start();
    }
}