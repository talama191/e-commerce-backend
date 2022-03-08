package base.controller;

import base.service.MinioService;
import io.minio.credentials.AssumeRoleBaseProvider.Response;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @ResponseBody
    public ResponseEntity<String> upload(@PathVariable("bucketName") String bucketName, @RequestPart(value = "file") MultipartFile files ) throws Exception {
        String storedPath = minioService.upload(files, bucketName);
        return ResponseEntity.ok().body(storedPath);
    }
    
    @GetMapping("/list/bucket/{bucketName}")
    public List<String> getListBucket(@PathVariable String bucketName) throws InvalidKeyException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidResponseException, XmlParserException, InternalException, IOException{
    	return minioService.listObjectNames(bucketName);
    }

}
