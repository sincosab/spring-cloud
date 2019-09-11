package com.bootdo.clouddoadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableFeignClients(basePackages = {"com.bootdo"})
@EnableCaching
@EnableScheduling
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.bootdo.clouddoadmin", "com.bootdo.clouddocommon"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ClouddoAdminApplication  extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(ClouddoAdminApplication.class, args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
