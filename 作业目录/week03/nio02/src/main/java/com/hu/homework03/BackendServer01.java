package com.hu.homework03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 后端服务器：端口为8089
 */
public class BackendServer01 {
    public static final AtomicBoolean Flag = new AtomicBoolean(true);

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
        // 默认后端服务器，与backEndServer区别是端口号不同，返回内容不同
        try (final ServerSocket serverSocket = new ServerSocket(8089)) {
            while (Flag.get()) {
                final Socket socket = serverSocket.accept();
                executorService.execute(() ->
                        service(socket)
                );
            }
        }
        executorService.shutdown();
    }

    private static void service(Socket socket) {
        String responseContent = "hello，my port:8089";
        // 1. 解析消息，并拼接返回内容
        try {
            InputStream inputStream = socket.getInputStream();
            //HelloExample helloExample = JSON.parseObject(inputStream, HelloExample.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2.构建返回消息
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println("Content-Length:" + responseContent.getBytes(StandardCharsets.UTF_8).length);
            printWriter.println();
            printWriter.write(responseContent);
            System.out.println("返回结果" + responseContent);
            printWriter.flush();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
