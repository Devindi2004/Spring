package org.example.newBean;

import org.springframework.stereotype.Component;

@Component
public class NewTestBean {
    public NewTestBean() {
        System.out.println("New test Bean is called");
    }
    public void print(){
        System.out.println("New test bean create");
    }
}
