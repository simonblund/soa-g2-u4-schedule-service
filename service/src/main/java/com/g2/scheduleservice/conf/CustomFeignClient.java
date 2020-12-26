package com.g2.scheduleservice.conf;

import feign.Client;
import feign.Request;
import feign.Response;
import lombok.val;
import org.springframework.util.StreamUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CustomFeignClient extends Client.Default {


    public CustomFeignClient(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier) {
        super(sslContextFactory, hostnameVerifier);
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {

        Response response = super.execute(request, options);


        val headerMap = response.headers();

        System.out.println(headerMap);

        List<String> v = new ArrayList<>();
        v.add("application/json");

        Map<String, Collection<String>> newMap = new HashMap<String, Collection<String>>();

        headerMap.forEach((k, a) -> {
            if(k.contains("content-type")){
                newMap.put("content-type", v);
            } else {
                newMap.put(k, a);
            }
        });


        System.out.println("new "+ newMap);


        //TODO do whatever you want with the responseBody - parse and modify it

        return response.toBuilder().body(response.body()).headers(newMap).build();
    }
}