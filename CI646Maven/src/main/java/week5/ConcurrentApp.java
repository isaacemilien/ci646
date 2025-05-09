package week5;

public class ConcurrentApp {

    public static void main(String[] args) {
        // start on main

        Runnable codeToRun = () -> {
            // code to start on a bg thread
            System.out.println(Thread.currentThread().getName());
        };

        Thread t = new Thread(codeToRun);
        t.start();

        int i = 1;
        int i2 = i * 5;

        System.out.println(i2);
        System.out.println(Thread.currentThread().getName());
    }
}
