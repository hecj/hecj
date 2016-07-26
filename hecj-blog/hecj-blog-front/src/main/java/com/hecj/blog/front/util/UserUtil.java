package com.hecj.blog.front.util;


import javax.servlet.http.HttpSession;

import com.hecj.blog.service.model.User;

public class UserUtil {

    //SessionKey
    public static final String USER_SESSION_KEY = "BLOG_USER_SESSION_KEY";

    /**
     * 检测是否登录
     *
     * @param httpSession
     * @return
     */
    public static boolean isLogin(HttpSession httpSession) {
        User user = getUser(httpSession);
        if (null == user) {
            return false;
        }
        return true;
    }

    /**
     * 从Session中获取用户
     *
     * @param httpSession
     * @return
     */
     public static User getUser(HttpSession httpSession) {
         return (User) httpSession.getAttribute(USER_SESSION_KEY);
    }

    /**
     * 登录后设置User至session中.
     *
     * @param u
     * @param httpSession
     */
    public static void setUser(User u, HttpSession httpSession) {
        httpSession.setAttribute(USER_SESSION_KEY, u);
    }

    /**
     * 用户登出.
     * @param httpSession
     */
    public static void removeUser(HttpSession httpSession) {
        httpSession.removeAttribute(USER_SESSION_KEY);
        httpSession.invalidate();
    }

}