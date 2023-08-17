package com.zlht.pose.developer.remote;


import com.zlht.pose.developer.remote.management.Configuration.ManagementConfiguration;
import com.zlht.pose.developer.remote.management.client.ManagementClient;
import com.zlht.pose.developer.tools.service.EnvService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;
import org.apache.logging.log4j.LogManager;

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
        logger.info("Hello World!!info ");
        logger.warn("Hello World!! ww");
        logger.error("Hello World!!error");
    }
}