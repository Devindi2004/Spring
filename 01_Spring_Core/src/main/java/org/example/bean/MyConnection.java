package org.example.bean;

import org.springframework.stereotype.Component;

@Component("myConnection")
public class MyConnection  {
    public MyConnection(){
        System.out.println("create MyConnection");
    }
}
