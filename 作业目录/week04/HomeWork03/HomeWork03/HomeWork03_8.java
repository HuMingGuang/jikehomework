package HomeWork03;


import java.util.Map;
import java.util.concurrent.*;

/**
 * 方式八：采用CountDownLatch先让祝线程阻塞，等待子线程执行出结果
 */
public class HomeWork03_8 {
    private static  int result ;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                result =fibo(36);
                countDownLatch.countDown();
            }
        };
        new Thread(task).start();
        countDownLatch.await();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    static Integer fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
