package org.example;

import org.example.bean.MyConnection;
import org.example.newBean.NewTestBean;
import org.example.bean.SpringBean;
import org.example.bean.TestBean;
import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();// configuration ready it to use

        // Bean ID
        SpringBean springBean=(SpringBean) context.getBean("springBean"); // class name eka simple wenna oni
        System.out.println("Bean"+ springBean);
        //by class Name
        SpringBean springBean2= context.getBean(SpringBean.class);
        System.out.println(springBean2);
        //by Bean ID
        TestBean testBean=(TestBean) context.getBean("exampleBean"); // test bean ekee component ekata id ekak dunnoth meke call karana onii ee id ekee name eken
        System.out.println("Test"+ testBean);
        //by Bean ID & Class Name
        TestBean testBean2=(TestBean) context.getBean("exampleBean", TestBean.class);
        System.out.println("bean"+ testBean2);

        TestBean testBean3=(TestBean) context.getBean("exampleBean", TestBean.class);
        System.out.println("bean"+ testBean3);

        NewTestBean newTestBean=(NewTestBean) context.getBean("newTestBean");
        System.out.println("New"+newTestBean);

        MyConnection myConnection=(MyConnection) context.getBean("myConnection");
        System.out.println("MyConnection"+ myConnection);
        MyConnection myConnection2=(MyConnection) context.getBean("myConnection", MyConnection.class);
        System.out.println("MyConnection"+ myConnection);


        context.registerShutdownHook();// configuration close karanna
    }
}