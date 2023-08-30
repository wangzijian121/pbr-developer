package com.zlht.pbr.algorithm.developer;

import com.zlht.pbr.algorithm.developer.tools.service.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author zi jian Wang
 */
@SpringBootApplication
@EnableOpenApi
public class AlgorithmDeveloperApi implements ApplicationRunner {

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