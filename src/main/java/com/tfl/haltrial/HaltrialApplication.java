package com.tfl.haltrial;

import com.tfl.haltrial.config.MessagingProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({MessagingProperties.class})
@SpringBootApplication
public class HaltrialApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaltrialApplication.class, args);
    }

}
