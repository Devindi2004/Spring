package org.example.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // Di wala wenasak wenne na. bean eka object eka atule tiyaganne na, heap ekaa atule tiyagannawa. scope eka prototype karath Di wada karanawa.
public class Test1 implements DiInterface {

    // property injection
    //@Autowired
    DI di ;

    // constructor through injection
    // constructor through injection waladi Autowired eka aniwarenma oni na
//    @Autowired
//    public Test1(DI di) {
//        this.di = di;
//    }

    // Setter method through injection

//@Autowired
//public Test1(DI di){
//    setTestOne(di);
//}
//    public void setTestOne(DI di) {
//        this.di = di;
//    }


    // Interface through injection
    @Autowired
    @Override
    public void inject(DI di) {
        this.di = di;
    }

    public void chatWithTest2() {
        di.chat();
    }
}

