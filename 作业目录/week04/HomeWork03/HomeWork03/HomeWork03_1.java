package HomeWork03;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方式一：线程实现callable方法，采用FutureTask.get()方法阻塞主线程获取结果
 */
public class HomeWork03_1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        // 1.实现callable接口
        MyThread myThread = new MyThread();
        FutureTask<Integer> fTask = new FutureTask<Integer>(myThread);
        Thread thread = new Thread(fTask);
        thread.start();
        // 2.阻塞获取结果
        int result = fTask.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    static class MyThread implements Callable {

        int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }

        @Override
        public Integer call() throws Exception {
            return fibo(36);
        }
    }
}
