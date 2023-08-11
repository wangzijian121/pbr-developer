package com.example.tools.service;

import com.example.tools.helper.EnvHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvService {

    @Autowired
    private EnvHelper envHelper;
    /**
     * Print Address
     */
    public void printSwaggerAddress() {

        String info = "Swagger： " + "http://%s:%s/swagger-ui/";
        String ip = envHelper.getIp();
        String port = envHelper.getPort();
        System.out.println(String.format(info, ip, port));
    }
}
