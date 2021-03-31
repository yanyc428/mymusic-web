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
 * 登录api
 */
@WebServlet(name = "login", value = "/api/login")
public class Login extends HttpServlet {

    /**
     * service实例
     */
    private final UserService userService = new UserService();

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

        //获取请求的userName和password参数
        JSONObject json = ParseJson.getJsonObject(request);
        String userName = (String) json.get("userName");
        String password = (String) json.get("password");

        if ((userName == null) || (password == null)) {
            // 无这俩参数应报错
            response.sendError(401, "错误的参数");
        } else {
            // 查询该用户， 提取实体
            User user = userService.checkUser(userName, password);
            if (user == null) {
                // 对应不上返回404
                response.sendError(404, "密码错误");
            } else {
                // 对应上了  把user写入session 并且返回200
                request.getSession().setAttribute("user", user);
                response.setStatus(200);
            }
        }
    }

    public void destroy() {
    }
}
