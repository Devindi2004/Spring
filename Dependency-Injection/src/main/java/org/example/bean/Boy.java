package org.example.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy {
    @Autowired // dependency satisfied karaganna puluwan
    @Qualifier("girl2") // apita oni component eka vitharak select kaarala gannawa.
    Agreement agreement;
    public Boy() {
        System.out.println("Boy object created");
    }

    public void chatWithGirl(){

        agreement.chat();

    }
}
