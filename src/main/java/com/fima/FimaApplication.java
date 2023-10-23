package com.fima;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FimaApplication {

    private final String CLOUD_NAME = "dbyjvd1i4";

    private final String API_KEY = "924518564742149";

    private final String API_SECRECT = "***************************";

    public static void main(String[] args) {
        SpringApplication.run(FimaApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRECT));
    }

}
