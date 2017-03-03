package com.github.vendigo.l2f.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(scanBasePackageClasses = FrontEndLauncher.class)
@EnableZuulProxy
public class FrontEndLauncher {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FrontEndLauncher.class, args);
    }
}
