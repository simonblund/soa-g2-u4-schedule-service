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
        val body = response.body();

        System.out.println("TE headers before change"+headerMap);

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


        // These are the lovely Time Edit Request debugging strings.
        // When active they will disable the responses from being forwarded to the application
        //System.out.println("TE request: "+request.httpMethod()+request.url());
        //System.out.println("TE response headers (after change): "+ newMap);
        //System.out.println("TE response body: "+ body);


        //TODO do whatever you want with the responseBody - parse and modify it

        return response.toBuilder().body(body).headers(newMap).build();
    }
}