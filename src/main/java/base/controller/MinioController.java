package base.controller;

import base.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/minio")
public class MinioController {
    @Autowired
    private final MinioService minioService;

    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }


    @PostMapping(value = "/upload/{bucketName}/files", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String upload(@PathVariable("bucketName") String bucketName, @RequestPart(value = "file") MultipartFile files) throws Exception {
        String storedPath = minioService.upload(files, bucketName);
        return storedPath;
    }

}
