package com.sun.main.config;




import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={"classpath:dubbo-consumer.xml"})
public class ConfigClass {
    
}