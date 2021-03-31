package cn.com.yanyuchen.mymusic_web.controller;

import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.service.SongService;
import cn.com.yanyuchen.mymusic_web.util.ParseJson;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 得到当前用户的喜爱的歌曲
 */
@WebServlet(name = "lovedSongs", value = "/api/loved")
public class LovedSongs extends HttpServlet {

    /**
     * 一些service 实例
     */
    private final SongService songService = new SongService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void init(){}

    /**
     * post方法
     *
     * @param request  固有参数
     * @param response 固有参数
     * @throws IOException 固有参数
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 基本设置
        response.setContentType("text/json; charset=utf-8");
        Writer writer = response.getWriter();

        // 拿到请求中的page参数
        JSONObject json = ParseJson.getJsonObject(request);
        Integer page = json.getInteger("page");

        // 如果请求不含page参数  返回401
        if (page == null) {
            response.sendError(401, "错误的参数");
            return;
        }

        // 如果当前并没有登录  返回403
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(403);
            return;
        }

        // 获取喜欢的歌曲的总数  用于计算页数
        Integer total = songService.countLovedSongs(user.getId());

        // 根据page参数来计算要返回的索引切片点
        Integer start = (page - 1) * 10;
        Integer end = page * 10;
        // 考虑最后的数量不到一页
        if (end > total) {
            end = total;
        }

        // 返回json
        Map<String, Object> map = new HashMap<>();
        // 返回数据
        map.put("data", songService.getLovedSongs(user.getId(), start, end));
        // 返回总页数
        map.put("totalPage", Math.ceil(total / 10f));
        writer.write(objectMapper.writeValueAsString(map));
        response.setStatus(200);
    }


    public void destroy() {
    }
}
