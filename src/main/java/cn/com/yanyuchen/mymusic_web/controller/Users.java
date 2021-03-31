package cn.com.yanyuchen.mymusic_web.controller;


import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * 所有用户信息  仅用于管理员
 */
@WebServlet(name = "users", value = "/api/users")
public class Users extends HttpServlet {

    /**
     * service实例
     */
    private final UserService userService = new UserService();
    ObjectMapper objectMapper = new ObjectMapper();

    public void init() {
    }

    /**
     * post方法
     *
     * @param request  固有参数
     * @param response 固有参数
     * @throws IOException 固有参数
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 固定设置
        response.setContentType("text/json; charset=utf-8");
        Writer writer = response.getWriter();

        // 检测登陆状态
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(403);
            return;
        }

        // 检查管理员状态
        if (!user.isAuth()) {
            response.sendError(403);
            return;
        }

        //返回相关信息
        List<User> users = userService.getUsers();
        writer.write(objectMapper.writeValueAsString(users));
        response.setStatus(200);

    }

    public void destroy() {
    }
}
