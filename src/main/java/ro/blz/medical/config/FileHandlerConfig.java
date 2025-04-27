package ro.blz.medical.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.blz.medical.service.FileHandler;
import ro.blz.medical.service.LocalFileHandler;

@Configuration
public class FileHandlerConfig {

    @Value("${file.handler.type}")
    private String fileHandlerType;

    @Bean
    public FileHandler fileHandler() {
        if (fileHandlerType.equalsIgnoreCase("local")) {
            return new LocalFileHandler();
        } else {
            return new LocalFileHandler();
        }
    }
}
