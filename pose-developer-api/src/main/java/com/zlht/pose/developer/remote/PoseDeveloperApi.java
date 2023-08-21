package com.zlht.pose.developer.remote;


import com.zlht.pose.developer.tools.service.EnvService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@ComponentScan("com.zlht.pose.developer")
@SpringBootApplication
@EnableOpenApi
public class PoseDeveloperApi implements ApplicationRunner {


    private  Logger logger = LogManager.getLogger(PoseDeveloperApi.class);
    @Autowired
    private EnvService envService;



    public static void main(String[] args) {
        SpringApplication.run(PoseDeveloperApi.class, args);

    }

    @Override
    public void run(ApplicationArguments args) {
        envService.printSwaggerAddress();
    }
}