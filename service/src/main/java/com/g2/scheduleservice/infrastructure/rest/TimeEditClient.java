package com.g2.scheduleservice.infrastructure.rest;

import com.g2.scheduleservice.conf.CustomFeignClient;
import com.g2.scheduleservice.infrastructure.rest.timeedit.TimeEditResource;
import feign.Client;
import feign.Contract;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@FeignClient(name = "timeedit-client", url = "${integration.services.time-edit.url}", configuration = {TimeEditClient.Config.class})
public interface TimeEditClient extends TimeEditResource {

    @Configuration
    class Config{

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Decoder springDecoder() {
            return new ResponseEntityDecoder(new SpringDecoder(messageConverters));
        }

        @Bean
        public Contract feignContract() {
            return new SpringMvcContract();
        }

        @Bean
        public Client client() {
            return new CustomFeignClient(null, null);
        }
    }
}
