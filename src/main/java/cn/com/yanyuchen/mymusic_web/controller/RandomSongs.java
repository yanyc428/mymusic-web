package cn.com.yanyuchen.mymusic_web.controller;

import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 获取随机的歌曲
 */
@WebServlet(name = "randomSongs", value = "/api/random")
public class RandomSongs extends HttpServlet {
    /**
     * service实例
     */
    private final SongService songService = new SongService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void init() {
    }

    /**
     * get方法
     *
     * @param request  固有参数
     * @param response 固有参数
     * @throws IOException 固有参数
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 一些基本设置
        response.setContentType("text/json; charset=utf-8");
        Writer writer = response.getWriter();

        // 拿到当前的user 如果没有登录返回null
        User user = (User) request.getSession().getAttribute("user");

        // 获取随机10首歌曲
        writer.write(objectMapper.writeValueAsString(songService.getRandomSongs(10, user)));
        //返回200
        response.setStatus(200);
    }


    public void destroy() {
    }
}
