package HomeWork03;


import java.util.concurrent.locks.LockSupport;

/**
 * 方式六：采用LockSupport，现将主线程阻塞，然后在子线程执行完run方法后，唤醒主线程
 */
public class HomeWork03_6 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 当前主线程
        Thread thread = Thread.currentThread();

        MyThread1 myThread1 = new MyThread1();
        myThread1.setMainThread(thread);
        myThread1.start();
        // 将当前主线程阻塞
        LockSupport.park();
        System.out.println("异步计算结果为：" + myThread1.result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    public static class MyThread1 extends Thread {
        private int result;
        private Thread mainThread;

        @Override
        public void run() {
            result = fibo(36);
            // 将主线程的锁释放
            LockSupport.unpark(mainThread);
        }

        public int getResult() {
            return result;
        }

        public void setMainThread(Thread mainThread) {
            this.mainThread = mainThread;
        }

        private int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}
