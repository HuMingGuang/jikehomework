package HomeWork03;


/**
 * 方式五：使用Thraed.sleep(1000)，将主线程先休眠1000ms，即让子线程先运行完
 */
public class HomeWork03_5 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
        Thread.sleep(1000);
        System.out.println("异步计算结果为：" + myThread1.result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    public static class MyThread1 extends Thread {
        private int result;

        @Override
        public void run() {
            result = fibo(36);
        }

        public int getResult() {
            return result;
        }

        private int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}
