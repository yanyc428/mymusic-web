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
 * 对一个歌曲添加喜欢
 */
@WebServlet(name = "likeSongs", value = "/api/like")
public class LikeASong extends HttpServlet {

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


        // 此操作应该处于登录状态，如果没有登陆，即刻返回403 forbidden
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(403);
            return;
        }

        // 拿到请求参数songId，即要喜欢的歌曲id
        JSONObject json = ParseJson.getJsonObject(request);
        Long songId = json.getLong("songId");

        // 通过session拿到当前用户的id
        Long userId = user.getId();

        if (songId == null) {
            // 无此参数请求无效
            response.sendError(401);
        } else {
            // 调用方法进行设置喜欢
            if (songService.loveASong(userId, songId)) {
                // 成功则返回200
                response.setStatus(200);
            } else {
                // 否则返回400
                response.sendError(400);
            }
        }

    }


    public void destroy() {
    }
}
