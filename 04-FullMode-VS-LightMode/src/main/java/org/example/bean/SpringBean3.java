package org.example.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBean3 {
    // Lihgt Mode
    @Bean
    public SpringBean1 springBean1(){
        // inter bean dependency - not satisfied(inject wenne na. inject wenawa plane object ekak,spring bean ekak newei)
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
