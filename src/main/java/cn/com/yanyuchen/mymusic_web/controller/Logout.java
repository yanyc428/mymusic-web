package cn.com.yanyuchen.mymusic_web.controller;

import cn.com.yanyuchen.mymusic_web.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录
 */
@WebServlet(name = "logout", value = "/api/logout")
public class Logout extends HttpServlet {

    public void init() {}

    /**
     * get方法
     *
     * @param request  固有参数
     * @param response 固有参数
     * @throws IOException 固有参数
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 拿到现在登录的user实例
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            // 还未登录无法注销
            response.sendError(403, "未登录");
        } else {
            // 从session中移除user 并返回200
            request.getSession().removeAttribute("user");
            response.setStatus(200);
        }
    }

    public void destroy() {}

}
