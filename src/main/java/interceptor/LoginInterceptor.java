package main.java.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final String LOGIN_URL = "/home/login";


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
//        HttpSession session = req.getSession(true);
        // 从session 里面获取用户名的信息
//        User user = (User) session.getAttribute("UserObj");
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
//        if (user == null || "".equals(user.toString())) {
//            res.sendRedirect(LOGIN_URL);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
