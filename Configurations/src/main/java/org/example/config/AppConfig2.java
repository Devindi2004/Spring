package org.example.config;

import org.example.bean.C;
import org.example.bean.D;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.bean")
public class AppConfig2 {
public AppConfig2(){
    System.out.println("AppConfig2");
}
@Bean
public C getC(){
    return new C();
}
@Bean
    public D getD(){
    return new D();
}
}
