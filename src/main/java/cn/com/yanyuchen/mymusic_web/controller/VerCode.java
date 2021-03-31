package cn.com.yanyuchen.mymusic_web.controller;

import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.service.MailService;
import cn.com.yanyuchen.mymusic_web.service.UserService;
import cn.com.yanyuchen.mymusic_web.util.ParseJson;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 获取验证码
 */
@WebServlet(name = "verCode", value = "/api/verCode")
public class VerCode extends HttpServlet {

    /**
     * 一些service实例
     */
    private final MailService mailService = new MailService();
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

        // 固定设置
        Writer writer = response.getWriter();
        JSONObject json = ParseJson.getJsonObject(request);

        //拿到type参数和userName参数
        String type = (String) json.get("type");
        String userName = (String) json.get("userName");
        // 获得随机验证码
        String verCode = RandomStringUtils.randomAlphanumeric(6);

        if (type == null) {
            response.sendError(401, "错误的参数");
        } else {
            // 注册验证码
            if (type.equals("register")) {
                try {
                    mailService.sendRegister(verCode, userName);
                    response.setStatus(200);
                    writer.write(verCode);
                    // 验证码写入session
                    request.getSession().setAttribute("verCode", verCode);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(502, "发送失败");
                }
            } else if (type.equals("reset")) {
                // 重设密码验证码
                try {
                    User user = userService.getUser(userName);
                    if (user == null) {
                        response.sendError(404, "无此用户");
                        return;
                    }
                    try {
                        mailService.sendReset(verCode, user);
                        response.setStatus(200);
                        writer.write(verCode);
                        // 验证码写入session
                        request.getSession().setAttribute("verCode", verCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendError(502, "发送失败");
                    }
                } catch (Exception e) {
                    response.sendError(502);
                }
            }
            // 返回的是验证码
        }
    }

    public void destroy() {
    }

}
