package org.example;

import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Properties;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        context.registerShutdownHook();

        //=========Environment Variables===============//
        //System Variables - OS related
        Map<String,String> systemVariables = System.getenv();
        for (String name : systemVariables.keySet()) {
            System.out.println(name+":"+systemVariables.get(name));
        }
      Properties properties = System.getProperties();
        for (String key : properties.stringPropertyNames()) {
            System.out.println(key+":"+properties.getProperty(key));
        }
        //Java properties - java language related
        //Resource bundle - (.env, .properties, .yml, .yaml) - Application related
    }
}