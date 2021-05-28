package com.practise.spring.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
public class GetMethodPractise {

    @RequestMapping(value = "/getCookie", method = RequestMethod.GET)
    public String getCookie(HttpServletResponse response){

        Cookie cookie = new Cookie("token", "testUserToken");
        response.addCookie(cookie);
        return "返回一个test信息123！！！！";
    }

    @RequestMapping(value = "/get/withCookie", method = RequestMethod.GET)
    public String getWithCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "cookie 不能为空！！！！！";
        }
        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("token") && cookie.getValue().equals("testUserToken")){
                return "cookie 校验通过。";
            } else if (!cookie.getName().equals("token") || !cookie.getValue().equals("testUserToken")){
                return "cookie 信息错误！！！";
            } else {break;}
        }
        return "请携带cookie发送请求！";
    }
}
