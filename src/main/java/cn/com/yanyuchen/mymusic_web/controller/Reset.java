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
 * 重设密码
 */
@WebServlet(name = "reset", value = "/api/reset")
public class Reset extends HttpServlet {

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
         * 拿到请求参数  password要重新设置的密码   userName  vercode（用来控制大量修改密码行为）
         */
        JSONObject json = ParseJson.getJsonObject(request);
        String password = (String) json.get("password");
        String userName = (String) json.get("userName");
        String verCode = json.getString("verCode");

        // 检查验证码合法性
        if (!verCode.equals(request.getSession().getAttribute("verCode"))){
            response.sendError(403);
            return;
        }

        //参数检查
        if ((password == null) | (userName == null)) {
            response.sendError(401, "错误的参数");
        } else {
            // 更新user  根据结果返回不同的状态码 失败是402
            try{
                // 拿到对应userName的User实例
                User user = userService.getUser(userName);
                // 设置密码
                user.setPassword(password);
                if (userService.updateUser(user)){
                    response.setStatus(200);
                    // 验证码失效
                    request.getSession().removeAttribute("verCode");
                }else{
                    response.sendError(502);
                }
            }catch (Exception e){
                e.printStackTrace();
                response.sendError(502);
            }
        }
    }

    public void destroy() {
    }
}
