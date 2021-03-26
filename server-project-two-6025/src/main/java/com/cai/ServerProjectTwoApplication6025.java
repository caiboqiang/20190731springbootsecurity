package com.cai;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cai.*"})
@EnableDistributedTransaction //开启分布式事物
@EnableCircuitBreaker //服务降级
@ComponentScan("com.cai")
@EnableHystrixDashboard
@EnableResourceServer
public class ServerProjectTwoApplication6025 {

    public static void main(String[] args) {
        SpringApplication.run(ServerProjectTwoApplication6025.class, args);
        System.err.println ( ServerProjectTwoApplication6025.class.getClassLoader ().toString () );
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
