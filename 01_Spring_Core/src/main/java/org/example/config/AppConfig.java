package org.example.config;

import org.example.bean.MyConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //component container ekata beans tika daganna kiyala kiyanne @Configuration eken
@ComponentScan(basePackages = {"org.example.bean","org.example.newBean",}) // beans tika koheda tiyenne kiyala kiyanne @ComponentScan eken
public class AppConfig {
    public AppConfig() { // create object
        System.out.println("AppConfig is created");
    }
    @Bean
    MyConnection myConnection(){
        return new MyConnection();

    }
}
