package com.example.api;


import com.example.tools.service.EnvService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;
import org.apache.logging.log4j.LogManager;

@ComponentScan("com.example")
@SpringBootApplication
@EnableOpenApi
public class Main implements ApplicationRunner {


    private  Logger logger = LogManager.getLogger(Main.class);
    @Autowired
    private EnvService envService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(ApplicationArguments args) {
        envService.printSwaggerAddress();
        logger.info("Hello World!!info ");
        logger.warn("Hello World!! ww");
        logger.error("Hello World!!error");
    }
}