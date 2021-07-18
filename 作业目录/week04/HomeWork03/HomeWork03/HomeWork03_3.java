package HomeWork03;


/**
 * 方式三：采用synchronized同步代码块，给myThread加，当主线程优先获取到锁时，则进行wait释放掉该锁，即让子线程运行run方法
 */
public class HomeWork03_3 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        MyThread myThread = new MyThread();
        myThread.start();
        synchronized (myThread) {
            // 释放对myThread锁的持有，即让子线程先运行
            myThread.wait();
            int result = myThread.getResult();
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        }


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
            System.out.println("====运行子线程===");
            result = fibo(36);
        }
    }
}
