package com.fima;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class FimaApplication {
    public static void main(String[] args) {
        SpringApplication.run(FimaApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dbyjvd1i4",
                "api_key", "924518564742149",
                "api_secret", "mm5Yezxlah-MvtpiNPxwaWrSdps",
                "secure", true
        ));
        return cloudinary;
    }
}
