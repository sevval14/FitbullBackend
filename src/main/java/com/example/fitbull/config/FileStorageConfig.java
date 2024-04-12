package com.example.fitbull.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {
	

    private final Path fileStorageLocation = Paths.get("upload-dir").toAbsolutePath().normalize();

    @Bean
    public Path fileStorageLocation() {
        return this.fileStorageLocation;
    }

}
