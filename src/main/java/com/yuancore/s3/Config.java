package com.yuancore.s3;

import com.yuancore.radosgw.sdk.RGWPassport;

public class Config {
    private final static String rgwUrl = "http://124.222.161.83:13000";
    private final static String userAccessKey = "aaa";
    private final static String userSecretKey = "bbb";
    public final static RGWPassport rgwPassport = new RGWPassport(rgwUrl, userAccessKey, userSecretKey);
    public final static String defaultBucketName = "bk1";
}
