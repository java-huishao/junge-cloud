package com.zhny.starter.oss;

import com.zhny.starter.oss.common.FileUploadResult;
import com.zhny.starter.oss.minio.MinioConfig;
import com.zhny.starter.oss.minio.MinioUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author houqj
 * 2021-07-06 22:11
 */
@Component
public class UploadFileService {

    @Resource
    private MinioUtil minioUtil;
    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioConfig minioConfig;

    /**
     * 上传文件
     *
     * @return
     */
    public FileUploadResult uploadFile(String originalFilename, Long fileSize, String contentType, InputStream inputStream) throws IOException {
        try {
            if (!minioUtil.bucketExists(minioConfig.getBucketName())) {
                minioUtil.makeBucket(minioConfig.getBucketName());
            }
            // 生成新文件名，确保唯一性
            String objectName = new SimpleDateFormat("yyyy/MM/dd/").format(new Date()) + UUID.randomUUID().toString().replaceAll("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
            //文件大小
            PutObjectArgs args = new PutObjectArgs.Builder()
                    .contentType(contentType)
                    .object(objectName)
                    .stream(inputStream, fileSize, 5242881)
                    .bucket(minioConfig.getBucketName())
                    .build();

            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(args);
            // 得到文件 url
            String fileUrl = minioUtil.getObjectUrl(minioConfig.getBucketName(), objectName);
            // 下载链接
            String downloadUrl = minioConfig.getDownloadPrefix() + objectName;
            String[] split = fileUrl.split("9000");
            fileUrl = "http://" + minioConfig.getEndpoint() + ":9000" + split[1];
            return new FileUploadResult(originalFilename, fileSize, contentType, objectName, fileUrl, downloadUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}

