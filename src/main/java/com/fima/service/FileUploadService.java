package com.fima.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public String uploadImage(MultipartFile multipartFile) throws Exception;

    public String uploadImage(String imagePath, Object... option) throws Exception;

    public void deleteImage(String... publicId) throws Exception;

    public void setCloudinary(String cloudName, String apiKey, String apiSecret);

    public void setCloudinary(String Cloudinary_url);
}
