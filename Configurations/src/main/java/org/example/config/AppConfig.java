package org.example.config;

import org.example.bean.A;
import org.example.bean.B;
import org.example.bean.SpringBean;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "org.example.bean")
@Import({AppConfig1.class, AppConfig2.class})
@ImportResource("classpath:hibernate.cfg.xml") // inna project ekee class path eka
// /@ImportResource("file:C:/Config/hibernate.cfg.xml") Computer OS path ekee folder path eka
public class AppConfig {
public AppConfig() {
    System.out.println("AppConfig created");
}
}
