package base.service;

import base.config.MinioConfig;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final MinioConfig minioProperties;

    public MinioService(MinioClient minioClient, MinioConfig minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

//
    public String upload(MultipartFile file, String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if(!found){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .contentType(file.getContentType())
                        .object(file.getOriginalFilename())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build()
        );
        String filePath = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .build()
        );

        return filePath;

    }




    public List<String> listObjectNames(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException  {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        }else{
            listObjectNames.add(" Bucket does not exist ");
        }
        return listObjectNames;
    }



    @SneakyThrows
    public Iterable<Result<Item>> listObjects(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            return minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return null;
    }


    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (found) {
            System.out.println(bucketName + " exists");
        } else {
            System.out.println(bucketName + " does not exist");
        }
        return found;
    }


}
