package com.hu.homework02;


import okhttp3.*;

import java.io.IOException;

/**
 * 客户端服务，调用网关，经过以下几步
 * (1)Filter：只有/hello形式的才可以通过
 * (2)Router: 根据不同的url，进行分配到不同的后端backEndUrl
 * (3)BackEndServer：后端服务器，返回结果
 */
public class OkHttpService {
    public static final MediaType JSON = MediaType.parse("application/json");
    public static OkHttpClient client = new OkHttpClient();


    public static void main(String[] args) {
        HelloExample helloExample = new HelloExample();
        helloExample.setAge(11);
        helloExample.setContent("hello world");
        helloExample.setName("sunshine");
        try {
            // 修改不同的url，可以路由到不同的服务器
            String response = post("http://127.0.0.1:8888/hello/1", com.alibaba.fastjson.JSON.toJSONString(helloExample));
            System.out.println("response result:" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
