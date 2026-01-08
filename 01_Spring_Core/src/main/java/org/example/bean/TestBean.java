package org.example.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("exampleBean")
@Scope("prototype")
public class TestBean {
    public TestBean(){
        System.out.println("test bean is created");
    }
    public void printMassage(){
        System.out.println("printMassage() is print");
    }
}
