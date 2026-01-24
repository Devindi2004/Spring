package org.example.config;

import org.example.bean.SpringBean1;
import org.example.bean.SpringBean2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.bean")
public class AppConfig {
    // full mode - inter bean dependency satisfied karanawa.
    // spring wla full mode eka use karanawa kiyanne cofiguration class ekka atulen bean method use krana ekt.
@Bean
    public SpringBean1 springBean1(){
    // inter bean dependency
    // bean method ekak athule bean method use karanawa.
    SpringBean2 springBean1 = springBean2();
    SpringBean2 springBean2 = springBean2();
    System.out.println(springBean1);
    System.out.println(springBean2);

        return new SpringBean1();
    }
    @Bean
    public SpringBean2 springBean2(){
        return new SpringBean2();
    }
}
