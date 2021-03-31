package cn.com.yanyuchen.mymusic_web.controller;


import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.service.SingerService;
import cn.com.yanyuchen.mymusic_web.service.SongService;
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
 * 根据检索词返回歌曲列表
 */
@WebServlet(name = "songs", value = "/api/songs")
public class Songs extends HttpServlet {

    /**
     * 一些service实例
     */
    private final SongService songService  = new SongService();

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

        // 固有参数
        response.setContentType("text/json; charset=utf-8");
        Writer writer = response.getWriter();

        //拿到word和page参数   word检索词  page 页面
        JSONObject json = ParseJson.getJsonObject(request);
        String word = json.getString("word");
        Integer page = json.getInteger("page");

        //拿到当前的user  用于返回是否喜欢
        User user = (User) request.getSession().getAttribute("user");

        if((word==null) | (page==null)){
            response.sendError(401, "错误的参数");
        }else{
            response.setStatus(200);
            Map<String, Object> map = new HashMap<>();
            // 返回数据
            map.put("data", songService.getSongs(word, user, page));
            // 返回总页数  一页10条
            map.put("totalPage", Math.ceil(songService.countSongs(word) / 10f));
            writer.write(JSON.toJSONString(map));
        }
    }



    public void destroy() {
    }
}
