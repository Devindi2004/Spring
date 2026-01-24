package org.example.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


public class SpringBean2 implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean, InitializingBean {

    public SpringBean2() {
        System.out.println("created Spring bean 2");
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext setApplicationContext");
    }
}
