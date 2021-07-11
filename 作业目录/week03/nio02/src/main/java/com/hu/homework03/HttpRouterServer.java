package com.hu.homework03;

import io.github.kimmking.gateway.router.HttpEndpointRouter;

import java.util.Collections;
import java.util.List;

public class HttpRouterServer implements HttpEndpointRouter {
    public static HttpRouterServer newInstance() {
        return new HttpRouterServer();
    }

    @Override
    public String route(List<String> endpoints) {
        if (null != endpoints && endpoints.size() > 0) {
            String[] url = endpoints.get(0).split("/");
            // 若url中最后输入的是1，则调用8088端口，否则调用默认8089端口；
            // 注：这里当然可以采用随机的方法访问任意端口
            if (url[url.length - 1].equals("1")) {
                return "http://localhost:8088";
            }
        }
        return "http://localhost:8089";
    }
}
