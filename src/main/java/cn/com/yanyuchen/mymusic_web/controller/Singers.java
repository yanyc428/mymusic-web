package cn.com.yanyuchen.mymusic_web.controller;

import cn.com.yanyuchen.mymusic_web.service.SingerService;
import cn.com.yanyuchen.mymusic_web.util.ParseJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 获得歌手列表
 */
@WebServlet(name = "singers", value = "/api/singers")
public class Singers extends HttpServlet {

    /**
     * 一些service实例
     */
    private final SingerService singerService = new SingerService();

    public void init() {
    }

    /**
     * post方法
     *
     * @param request  固有参数
     * @param response 固有参数
     * @throws IOException 固有参数
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //基本设置
        response.setContentType("text/json; charset=utf-8");
        Writer writer = response.getWriter();

        // 拿到请求参数
        JSONObject json = ParseJson.getJsonObject(request);
        String gender = json.getString("gender"); //性别
        String firstLetter = json.getString("firstLetter"); //首字母
        String platform = json.getString("platform"); //平台
        String type = json.getString("type"); //类型
        Integer page = json.getInteger("page"); //页数

        //参数检查
        if ((gender == null) | (firstLetter == null) | (platform == null) | (type == null) | (page == null)) {
            response.sendError(401, "错误的参数");
        } else {
            response.setStatus(200);
            Map<String, Object> map = new HashMap<>();
            // 返回数据
            map.put("data", singerService.getSingers(gender, firstLetter, platform, type, page));
            // 计算总页数并返回
            map.put("totalPage", Math.ceil(singerService.countSingers(gender, firstLetter, platform, type) / 100f));
            // 写入json
            writer.write(JSON.toJSONString(map));
        }
    }

    public void destroy() {
    }

}
