package com.example.urlShortener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class URLConfig {

    @Bean
    CommandLineRunner commandLineRunner(URLRepository repository) {
        return args -> {
            URL google = new URL(
                "https://www.google.com",
                    "wHxP"
            );
            URL bing = new URL(
                    "https://www.bing.com",
                    "PdDc"
            );
            repository.saveAll(List.of(google,bing));
        };
    }
}
