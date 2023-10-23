package com.fima.service.impl;

import java.util.Map;
import java.util.UUID;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fima.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class CloudinaryServiceImpl implements FileUploadService {
    private Cloudinary cloudinary;

    public CloudinaryServiceImpl(String cloudName, String apiKey, String apiSecret) {
        cloudinary = new Cloudinary(
                ObjectUtils.asMap("cloud_name", cloudName
                        , "api_key", apiKey
                        , "api_secret", apiSecret
                        , "secure", true));
    }

    public CloudinaryServiceImpl(String Cloudinary_url) {
        cloudinary = new Cloudinary(Cloudinary_url);
    }

    public String uploadImage(MultipartFile multipartFile) throws Exception {
        return cloudinary.uploader().upload(multipartFile.getBytes(), Map.of("public_id", UUID.randomUUID().toString())).get("url").toString();
    }

    public String uploadImage(String imagePath, Object... option) throws Exception {
        try {
            Map uploadResult = cloudinary.uploader().upload(imagePath, ObjectUtils.asMap(option));
            return (String) uploadResult.get("url");
        } catch (Exception e) {
            throw new Exception("Lỗi khi tải lên hình ảnh: " + e.getMessage());
        }
    }

    public void deleteImage(String... publicId) throws Exception {
        try {
            // Xóa hình ảnh khỏi Cloudinary dựa trên 'public_id'
            for (String x : publicId) {
                cloudinary.uploader().destroy(x, ObjectUtils.emptyMap());
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi xóa hình ảnh: " + e.getMessage());
        }
    }

    public void setCloudinary(String cloudName, String apiKey, String apiSecret) {
        cloudinary = new Cloudinary(
                ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));
    }

    public void setCloudinary(String Cloudinary_url) {
        cloudinary = new Cloudinary(Cloudinary_url);
    }
}
