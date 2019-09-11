package com.bootdo.clouddobase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author bootdo
 */
/*
 * @EnableOAuth2Sso
 * 
 * @EnableWebSecurity
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled = true)
 */
@MapperScan(basePackages = {"com.bootdo.*.dao"})
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class ClouddoBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClouddoBaseApplication.class, args);
	}
}
