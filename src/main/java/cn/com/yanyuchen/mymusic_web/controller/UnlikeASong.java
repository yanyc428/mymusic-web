package cn.com.yanyuchen.mymusic_web.controller;


import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.service.SongService;
import cn.com.yanyuchen.mymusic_web.util.ParseJson;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 取消喜欢一个歌曲
 */
@WebServlet(name = "unlikeSongs", value = "/api/unlike")
public class UnlikeASong extends HttpServlet {

    /**
     * service 实例
     */
    private final SongService songService = new SongService();

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
        // 先检查是否登录  没有登录就返回
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(403);
            return;
        }

        // 拿到songId参数
        JSONObject json = ParseJson.getJsonObject(request);
        Long songId = json.getLong("songId");

        // 拿到当前的userId
        Long userId = user.getId();

        if (songId == null) {
            response.sendError(401);
        } else {
            // 取消喜欢一首歌曲
            if (songService.unloveASong(userId, songId)) {
                response.setStatus(200);
            } else {
                response.sendError(400);
            }
        }

    }


    public void destroy() {
    }
}
