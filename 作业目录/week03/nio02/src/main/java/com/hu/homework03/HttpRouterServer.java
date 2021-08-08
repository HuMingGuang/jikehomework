package com.hu.homework03;

import io.github.kimmking.gateway.router.HttpEndpointRouter;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HttpRouterServer implements HttpEndpointRouter {
    public static HttpRouterServer newInstance() {
        return new HttpRouterServer();
    }

    @Override
    public String route(List<String> endpoints) {
        // 随机返回一个服务器路由地址
        int size = endpoints.size();
        Random random = new Random(System.currentTimeMillis());
        return endpoints.get(random.nextInt(size));
    }
}
