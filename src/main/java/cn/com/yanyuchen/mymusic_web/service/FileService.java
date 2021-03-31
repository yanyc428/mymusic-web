package cn.com.yanyuchen.mymusic_web.service;

import cn.com.yanyuchen.mymusic_web.util.AliyunOssUtil;

import java.io.InputStream;

/**
 * 文件的service
 */
public class FileService {

    /**
     * 实例化阿里云oss
     */
    private final AliyunOssUtil ossUtil = new AliyunOssUtil();

    /**
     * 上传一个用户头像
     * @param inputStream 输入流
     * @param key 文件名
     * @return 能够获取资源的url
     */
    public String uploadAvatar(InputStream inputStream, String key) {
        ossUtil.put(key, inputStream);
        return ossUtil.get(key) + "?x-oss-process=image/resize,m_fill,h_144,w_144,limit_0";
    }

}
