package cn.com.yanyuchen.mymusic_web.filter;

import cn.com.yanyuchen.mymusic_web.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员拦截 拦截admin.jsp
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin.jsp")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 拦截方法
     * @param servletRequest 固有
     * @param servletResponse 固有
     * @param filterChain 固有
     * @throws IOException 固有
     * @throws ServletException 固有
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            //如果没登录  拦截
            response.sendRedirect("index.jsp");
        }else if(!user.isAuth()){
            //如果不是管理员，拦截
            response.sendRedirect("index.jsp");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
