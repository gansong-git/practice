package com.practise.gansong.employeeTest;

import com.practises.gansong.requestMethod.GenerateHeader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetUerInfoTest {
    private static String name = "wangwu";
    private static String userId = "66666";
    private static String token = "testUserToken";
    private static String getUserInfourl = "http://localhost:8999/user/getUserInfo";


    @Test
    public static void testSearchEemployeeInfo() {
        searchEmployee(name, userId);
    }

    public static Response searchEmployee(String name, String userId) {
        Map<String, Object> cookieMap = GenerateHeader.getCookie(token);
        Map<String, Object> headerMap = GenerateHeader.getheader();
        Map<String, Object> paramsMap = generateParams(name, userId);
        Response response = given().headers(headerMap).cookies(cookieMap).params(paramsMap).log().all()
                .get(getUserInfourl).then().statusCode(200).extract().response();
        System.out.println("重新打印返回值：" + response.asString());
        return response;
    }

    private static Map<String, Object> generateParams(String name, String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("userId", userId);
        return params;
    }

}
