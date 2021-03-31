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
 * 注册api
 */
@WebServlet(name = "register", value = "/api/register")
public class Register extends HttpServlet {

    /**
     * 一些service实例
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

        /*
        拿到请求的参数 包括userName password  gender  avatar  email  verCode（用来控制大量的调用注册api）
         */
        JSONObject json = ParseJson.getJsonObject(request);
        String userName = json.getString("userName");
        String password = json.getString("password");
        boolean gender = json.getString("gender").equals("1");
        String avatar = json.getString("avatar");
        String email = json.getString("email");
        String verCode = json.getString("verCode");

        /*
         * 检验当前用户的vercode是否正确
         */
        if (!verCode.equals(request.getSession().getAttribute("verCode"))){
            response.sendError(403);
            return;
        }

        // 实例化user
        User user = new User(null, userName, gender, avatar, email, password, false, null, null, null);

        // 参数检查
        if ((userName == null) || (password == null)||  (avatar == null)|| (email == null)) {
            response.sendError(401, "错误的参数");
        } else {
            // 根据注册的结果返回不同的状态码  失败为502
            if (userService.registerUser(user)) {
                // 让验证码失效
                request.getSession().removeAttribute("verCode");
                response.setStatus(200);
            } else {
                response.sendError(502, "注册失败");
            }

        }
    }


    public void destroy() {
    }
}
