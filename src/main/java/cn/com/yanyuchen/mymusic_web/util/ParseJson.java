package cn.com.yanyuchen.mymusic_web.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 用于处理前端来的json
 */
public class ParseJson {

    /**
     * 获取指定request生成的json
     * @param servletRequest request请求
     * @return json对象
     * @throws IOException request需要抛出这个异常
     */
    public static JSONObject getJsonObject(ServletRequest servletRequest) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(servletRequest.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb);
        //将json字符串转换为json对象
        return JSONObject.parseObject(sb.toString());
    }
}
