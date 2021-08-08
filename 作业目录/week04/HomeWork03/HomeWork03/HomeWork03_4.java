package HomeWork03;


/**
 * 方式四：采用synchronized同步代码块，采用对象o进行加锁（降低锁的粒度），然后设置wait(1000)，主线程释放锁1000ms，此时子线程运行出结果，
 */
public class HomeWork03_4 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Object o = new Object();
        MyThread myThread = new MyThread();
        myThread.setObject(o);
        myThread.start();
        synchronized (o) {
            // 释放对myThread锁的持有，即让子线程先运行
            o.wait(1000);
            int result = myThread.getResult();
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        }


    }

    static class MyThread extends Thread {
        private int result;
        private Object o;

        int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }

        public int getResult() {
            return result;
        }

        public void setObject(Object o) {
            this.o = o;
        }

        @Override
        public void run() {
            synchronized (o) {
                System.out.println("====运行子线程===");
                result = fibo(36);
            }
        }
    }
}
