package com.zlht.pose.developer.remote.management.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "remote.management")
public class ManagementConfiguration {

    private String ip;
    private Integer port;

}
