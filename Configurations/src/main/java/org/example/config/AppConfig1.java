package org.example.config;

import org.example.bean.A;
import org.example.bean.B;
import org.example.bean.SpringBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.bean")
public class AppConfig1 {
public AppConfig1(){
    System.out.println("AppConfig1 created");

    }
    @Bean
    public A a() {
        return new A();
    }
    @Bean
    public B b() {
        return new B();
    }
}
