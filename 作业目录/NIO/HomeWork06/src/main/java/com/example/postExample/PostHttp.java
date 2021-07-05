package com.example.postExample;

import okhttp3.*;

import java.io.IOException;

public class PostHttp {
    public static final MediaType JSON = MediaType.parse("application/json");
    private static OkHttpClient client = new OkHttpClient();


    public static void main(String[] args) {
        HelloExample helloExample = new HelloExample();
        helloExample.setAge(11);
        helloExample.setContent("hello world");
        helloExample.setName("sunshine");
        try {
            String response = post("http://127.0.0.1:8801", com.alibaba.fastjson.JSON.toJSONString(helloExample));
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
