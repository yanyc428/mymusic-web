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
 * 更新用户
 */
@WebServlet(name = "updateUser", value = "/api/updateUser")
public class UpdateUser extends HttpServlet {

    // 一些service实例
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


        JSONObject json = ParseJson.getJsonObject(request);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(403);
            return;
        }


        Long id = json.getLong("id");
        if (id == null) {
            response.sendError(401);
            return;
        }

        if (id.equals(user.getId()) && !userService.getUser(id).isAuth()) {
            // 修改自己的信息
            String userName = json.getString("userName");
            String avatar = json.getString("avatar");

            boolean gender = json.getString("gender").equals("1");

            if ((userName == null) | (avatar == null)) {
                response.sendError(401);
                return;
            }

            user.setGender(gender);
            user.setUserName(userName);
            user.setAvatar(avatar);


            userService.updateUser(user);

        } else {
            // 修改别人的信息 或者自己是管理员   这里使用的策略是乐观  就是相信会有正确的参数 因为servlet也会拦截error
            if (!user.isAuth()) {
                response.sendError(403);
                return;
            }

            User cur = userService.getUser(id);

            try {
                boolean gender = json.getString("gender").equals("1");
                cur.setGender(gender);
            } catch (Exception ignored) {
            }

            try {
                boolean auth = json.getString("auth").equals("1");
                cur.setAuth(auth);
            } catch (Exception ignored) {
            }

            try {
                String email = json.getString("email");
                if (email != null) {
                    cur.setEmail(email);
                }
            } catch (Exception ignored) {
            }

            try {
                String avatar = json.getString("avatar");
                if (avatar != null) {
                    cur.setAvatar(avatar);
                }
            } catch (Exception ignored) {
            }
            try {
                String userName = json.getString("userName");
                if (userName != null) {
                    cur.setUserName(userName);
                }
            } catch (Exception ignored) {
            }

            userService.updateUser(cur);

        }
        // 更新当前session中的user
        User user1 = userService.getUser(user.getId());
        request.getSession().setAttribute("user", user1);
        response.setStatus(200);
    }

    public void destroy() {
    }

}
