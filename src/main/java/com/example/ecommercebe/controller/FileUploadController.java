package com.example.ecommercebe.controller;

import com.example.ecommercebe.models.response.FileUploadResponse;
import com.example.ecommercebe.util.FileDownloader;
import com.example.ecommercebe.util.FileUploader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
public class FileUploadController {
    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileCode = FileUploader.uploadFile(file);
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(file.getOriginalFilename());
        response.setFileDownloadUri("http://localhost:8081/api/v1/file/download/" + fileCode);
        response.setFileType(file.getContentType());
        response.setSize(file.getSize());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileCode) throws IOException {
        return ResponseEntity.ok(FileDownloader.downloadFile(fileCode));
    }
}
