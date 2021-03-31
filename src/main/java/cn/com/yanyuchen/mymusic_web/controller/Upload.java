package cn.com.yanyuchen.mymusic_web.controller;


import cn.com.yanyuchen.mymusic_web.service.FileService;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;


/**
 * 上传文件、头像
 */
@WebServlet(name = "upload", value = "/api/upload")
@MultipartConfig
public class Upload extends HttpServlet {

    /**
     * service实例
     */
    private final FileService fileService = new FileService();

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
        Writer writer = response.getWriter();
        try {
            // 拿到传过来的文件流
            Part part = request.getPart("file");
            String disposition = part.getHeader("Content-Disposition");
            String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);
            //随机的生存一个32的字符串
            String filename = RandomStringUtils.randomAlphanumeric(24) + suffix;
            //获取上传的文件名
            InputStream inputStream = part.getInputStream();
            // 上传头像
            writer.write(fileService.uploadAvatar(inputStream, filename));
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(502, "错误");
        }
    }

    public void destroy() {
    }
}
