package week5;

import java.util.concurrent.CountDownLatch;

public class ConcurrentApp2 {

    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(1);

        // start on main

        Runnable codeToRun = () -> {
            // code to start on a bg thread
            System.out.println(Thread.currentThread().getName());

            latch.countDown();
        };

        Thread t = new Thread(codeToRun);
        t.start();
        
        int i = 1;
        int i2 = i * 5;

        latch.await();

        System.out.println(i2);
        System.out.println(Thread.currentThread().getName());
    }
}
