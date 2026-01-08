package org.example.config;

import org.example.bean.SpringBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.bean")
public class AppConfig {
    public AppConfig() { // create object
        System.out.println("AppConfig is created");
    }
@Bean
    SpringBean springBean(){
        return new SpringBean();
}
}
