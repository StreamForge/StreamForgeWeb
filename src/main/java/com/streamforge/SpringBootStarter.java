package com.streamforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SpringBootStarter {

    @GetMapping("/")
    public String getInitialPage() {
        return "index.html";
    }

    public static void main(String... args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
