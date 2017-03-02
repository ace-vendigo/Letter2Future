package com.github.vendigo.l2f.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = BackEndLauncher.class)
public class BackEndLauncher {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackEndLauncher.class, args);
    }
}
