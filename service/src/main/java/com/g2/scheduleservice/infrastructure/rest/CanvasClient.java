package com.g2.scheduleservice.infrastructure.rest;


import com.g2.scheduleservice.infrastructure.rest.canvas.CanvasResource;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@FeignClient(name = "real-canvas-client", url = "${integration.services.real-canvas.url}", configuration = CanvasClient.Config.class)
public interface CanvasClient extends CanvasResource {

    @Configuration
    class Config {

        @Bean
        public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }
}
