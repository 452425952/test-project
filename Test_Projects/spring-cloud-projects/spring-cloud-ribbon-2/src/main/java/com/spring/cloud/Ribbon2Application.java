package com.spring.cloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @author 赵小超
 */
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker//在SpringCloud中使用Hystrix
@EnableHystrixDashboard//断路器Hystrix仪表盘
public class Ribbon2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ribbon2Application.class, args);
    }

    /**
     * Spring Boot 2.0 下 hystrix.stream 404 问题
     * 在Application类中增加如下代码可以解决404问题
     *
     * @return
     */
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
