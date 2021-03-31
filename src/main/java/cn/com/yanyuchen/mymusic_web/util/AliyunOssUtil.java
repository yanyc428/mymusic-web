package cn.com.yanyuchen.mymusic_web.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

/**
 * 阿里云oss的实用工具
 */
public class AliyunOssUtil {

    /**
     * 这一部分是基本配置  请千万忽略这些  不要记住这些 并且不要传播
     */
    private final String endpoint = "endpoints";
    private final String accessKeyId = "accessKeyId";
    private final String accessKeySecret = "accessKeySecret";
    private final String bucketName = "bucketName";


    /**
     * 上传文件
     *
     * @param key         阿里云键值
     * @param inputStream 文件输入流
     * @return 是否传输成功
     */
    public boolean put(String key, InputStream inputStream) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.putObject(bucketName, key, inputStream);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        ossClient.shutdown();
        return false;

    }

    /**
     * 下载文件 获取url
     *
     * @param key 阿里云键值
     * @return 可获取资源的url
     */
    public String get(String key) {
        return "https://" + bucketName + "." + endpoint + "/" + key;
    }
}
