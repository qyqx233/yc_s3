package com.yuancore.s3;

import com.yuancore.radosgw.sdk.RGWPassport;

public class Config {
    private final static String rgwUrl = "";
    private final static String userAccessKey = "111";
    private final static String userSecretKey = "222";
    public final static RGWPassport rgwPassport = new RGWPassport(rgwUrl, userAccessKey, userSecretKey);
    public final static String defaultBucketName = "bk3";
}
