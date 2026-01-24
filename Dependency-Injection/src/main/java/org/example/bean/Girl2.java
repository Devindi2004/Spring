package org.example.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Girl2 implements Agreement,  BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean, InitializingBean {
    public Girl2() {
        System.out.println("Girl 2 object created");
    }
    @Override
    public  void chat(){
        System.out.println("Girl 2 chat");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanNameAware setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initializingBean afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext setApplicationContext");
    }
}
