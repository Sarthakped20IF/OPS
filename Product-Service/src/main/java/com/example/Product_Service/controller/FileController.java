package com.example.Product_Service.controller;

import com.example.Product_Service.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private S3Service s3Service;


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return s3Service.UploadFile("product-bucket",file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        var stream = s3Service.DownloadFile("product-bucket",fileName);
        byte[] bytes = stream.readAllBytes();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .header("Content-Type", "application/octet-stream")
                .body(bytes);
    }
}
