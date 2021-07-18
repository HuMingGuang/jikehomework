package HomeWork03;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 方式七：采用线程池的方式，执行具有实现具有返回值的线程,类似于使用其他的线程池也是可以的
 */
public class HomeWork03_7 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        long start = System.currentTimeMillis();

        Future<Integer> futrue = executorService.submit(() -> {
            return fibo(36);
        });
        executorService.shutdown();
        // 阻塞获取子线程计算结果
        Integer result = futrue.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    static Integer fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
