package com.practise.gansong.requestMethod;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendRequest {

    /**
     * 发送post请求方法，传入header、参数、url，即可返回response；
     *
     * @param headerMap
     * @param url
     * @return
     */
    public static Response sendPostRequest(Map<String, String> headerMap,
                                           String parameter, String url) {
        Response response = given().headers(headerMap).body(parameter).log().all()
                .post(url).then().statusCode(200).extract().response();
        response.asString();
        return response;
    }

    /**
     * 发送不带header的post请求
     * @param parameter
     * @param url
     * @return
     */
    public static Response post(Map<Object, Object> parameter, String url) {
        Response response = given().body(parameter).log().all()
                .post(url).then().statusCode(200).extract().response();
        response.asString();
        return response;
    }

    /**
     * 发送post请求方法，传入header、参数、url，即可返回response；
     *
     * @param headerMap
     * @param url
     * @return
     */
    public static Response postRequest(Map<String, Object> headerMap,
                                       Map<Object, Object> parameter, String url) {
        Response response = given().headers(headerMap).body(parameter).log().all()
                .post(url).then().statusCode(200).extract().response();
        response.asString();
        return response;
    }

    /**
     * 发送post请求方法，传入header、参数、url，即可返回response；
     *
     * @param headerMap
     * @param url
     * @return
     */
    public static Response postRequest(Map<String, Object> headerMap,
                                       String parameter, String url) {
        Response response = given().headers(headerMap).body(parameter).log().all()
                .post(url).then().statusCode(200).extract().response();
        response.asString();
        return response;
    }

    /**
     * 发送get请求
     * @param headerMap
     * @param parameter
     * @param url
     * @return
     */
    public static Response get(Map<String, Object> headerMap,
                                             String parameter, String url) {
        Response response = given().headers(headerMap).body(parameter).log().all()
                .get(url).then().statusCode(200).extract().response();
        response.asString();
        return response;
    }

    /**
     * 原来的发送get请求
     * @param headerMap
     * @param parameter
     * @param url
     * @return
     */
    public static Response getRequestyuanlai(Map<String, Object> headerMap,
                                      String parameter, String url) {
        Response response = given().headers(headerMap).body(parameter).log().all()
                .get(url).then().statusCode(200).extract().response();
        response.asString();
        return response;
    }

    /**
     * 发送不带header的get请求
     * @param parameter
     * @param url
     * @return
     */
    public static Response get(String parameter, String url) {
        Response response = given().body(parameter).log().all()
                .get(url).then().statusCode(200).extract().response();
        response.asString();
        return response;
    }

    /**
     * 生成headerMap，区分contentType("application/x-www-form-urlencoded" 或
     * "application/x-www-form-urlencoded")
     *
     * @param userToken
     * @param contentType
     * @return
     */
    public static Map<String, Object> generateHeaderMap(String userToken,
                                                        String contentType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Cookie", userToken);
        map.put("Content-Type", contentType);
//        map.put("X-Requested-With", "XMLHttpRequest");
        return map;
    }

    /**
     * get请求header
     * @param userToken
     * @return
     */
    public static Map<String, Object> getRequestHeader(String userToken) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Content-Type", "application/json;charset=UTF-8");
        map.put("token", userToken);
//        map.put("X-Requested-With", "XMLHttpRequest");
        map.put("Connection", "keep-alive");
        return map;
    }
}
