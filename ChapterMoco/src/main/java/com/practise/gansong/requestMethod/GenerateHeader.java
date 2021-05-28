package com.practise.gansong.requestMethod;

import java.util.HashMap;
import java.util.Map;

public class GenerateHeader {

    public static Map<String, Object> getCookie(String token){
        Map<String, Object> cookieMap = new HashMap<String, Object>();
        cookieMap.put("token", token);
        return cookieMap;
    }
    public static Map<String, Object> getheader(){
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("content-type", "application/json; charset=utf-8");
        return headerMap;
    }
}
