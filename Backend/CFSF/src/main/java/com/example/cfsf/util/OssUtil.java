package com.example.cfsf.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.example.cfsf.config.OssConfig;

import java.io.InputStream;

public class OssUtil {
    private static final String ACCESS_KEY_ID = OssConfig.getAccessKeyId();
    private static final String ACCESS_KEY_SECRET = OssConfig.getAccessKeySecret();
    private static final String BUCKET_NAME = OssConfig.getBucketName();
    private static final String ENDPOINT = OssConfig.getEndpoint();

    public static String uploadFile(String objectName, InputStream in) throws Exception {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, "files/" + objectName, in);
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            String _url = "https://cfsf-bucket.oss-cn-hangzhou.aliyuncs.com/files/" + objectName;
            return _url;
        } catch (Exception e) {
            throw e;
        } finally {
            ossClient.shutdown();
        }
    }
}
