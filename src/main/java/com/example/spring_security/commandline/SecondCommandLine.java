package com.example.spring_security.commandline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class SecondCommandLine implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("===========  2222  ===========");
    }
}
