package cn.com.yanyuchen.mymusic_web.controller;

import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.service.UserService;
import cn.com.yanyuchen.mymusic_web.util.ParseJson;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否存在，用于判断用户名是否可用api
 */
@WebServlet(name = "checkExist", value = "/api/checkExist")
public class CheckExist extends HttpServlet {


    /**
     * 用户的服务
     */
    private final UserService userService = new UserService();

    public void init() {
    }

    /**
     *  post方法
     * @param request 固有参数
     * @param response 固有参数
     * @throws IOException 固有参数
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
         * 接收参数userName
         */
        JSONObject json = ParseJson.getJsonObject(request);
        String userName = json.getString("userName");

        try{
            User user = (User) request.getSession().getAttribute("user");
            if (userName.equals(user.getUserName())){
                response.setStatus(200);
                return;
            }
        }catch (Exception ignored){}


        try {
            // 查询userName  得到user实例，不存在会返回null
            User user = userService.getUser(userName);
            // 如果是null 表明该用户不存在，返回200状态
            if (user == null) {
                response.setStatus(200);
            } else {
                // user不为null，则说明已经存在此用户名，返回403 forbidden
                response.sendError(403);
            }
        } catch (Exception e) {
            // 服务器错误返回502
            response.sendError(502);
        }
    }

    public void destroy() {
    }


}
