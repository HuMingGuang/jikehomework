package HomeWork03;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方式二：采用Thread中的join方法，阻塞主线程，先让子线程运行结束
 */
public class HomeWork03_2 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();
        int result = myThread.getResult();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    static class MyThread extends Thread {
        private int result;

        int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }

        public int getResult() {
            return result;
        }

        @Override
        public void run() {
            result = fibo(36);
        }
    }
}
