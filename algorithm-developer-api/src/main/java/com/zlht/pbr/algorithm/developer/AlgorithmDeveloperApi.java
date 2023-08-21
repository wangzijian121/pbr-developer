package com.zlht.pbr.algorithm.developer;


import com.zlht.pbr.algorithm.developer.tools.service.EnvService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class AlgorithmDeveloperApi implements ApplicationRunner {


    private  Logger logger = LogManager.getLogger(AlgorithmDeveloperApi.class);
    @Autowired
    private EnvService envService;



    public static void main(String[] args) {
        SpringApplication.run(AlgorithmDeveloperApi.class, args);

    }

    @Override
    public void run(ApplicationArguments args) {
        envService.printSwaggerAddress();
    }
}