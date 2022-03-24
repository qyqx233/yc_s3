package com.yuancore.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.StringInputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

public class Demo {
    private AmazonS3 s3Client;

    @Before
    public void initClient() {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setProtocol(Protocol.HTTP);
        conf.setSignerOverride("S3SignerType");
//        conf.setMaxConnections(100); // 可以在此设置最大连接数
        AWSCredentials credentials = new BasicAWSCredentials(Config.rgwPassport.getAccessKey(),
                Config.rgwPassport.getSecretKey());

        s3Client = new AmazonS3Client(credentials, conf);
        s3Client.setEndpoint(Config.rgwPassport.getEndPoint());

        System.out.println("初始化成功");
    }

    @Test
    public void uploadObject() throws IOException {
        try {
//            initClient();
            String objID = UUID.randomUUID().toString();
            System.out.println(objID);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.addUserMetadata("x-ycore-filename", URLEncoder.encode("中文名", "UTF-8"));
            PutObjectResult r = s3Client.putObject(Config.defaultBucketName, objID, new StringInputStream("中文名需要url encode"), metadata);
            for (int i = 0; i < 20; i++) {
                S3Object obj = s3Client.getObject(Config.defaultBucketName, objID);
                obj.close(); // 此处需要close
            }
            System.out.println("finish");
        } catch (Exception ex) {
            throw ex;
        }
    }
}
