package com.practise.gansong.employeeTest;


import com.practises.gansong.requestMethod.GenerateHeader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetCompanyInfoTest {
    private static String name = "wangwu";
    private static String userId = "66666";
    private static String companyName = "test.inc";
    private static String companyAddress = "beijing";
    private static String token = "testUserToken";
    private static Map<String, Object> headerMap;
    private static Map<String, Object> cookieMap;
    private static String getCompanyInfoUrl = "http://localhost:8999/info/getCompanyInfo";
    private Response userInfoRs = null;

    @Test
    public void getCompanyInfo() {
        userInfoRs = GetUerInfoTest.searchEmployee(name, userId);
        companyName = userInfoRs.jsonPath().get("data.companyName").toString();
        companyAddress = userInfoRs.jsonPath().get("data.workingCity").toString();
        getCompanyInfo(companyName, companyAddress);
    }

    /**
     * 查询公司信息
     *
     * @param companyName
     * @param address
     * @return
     */
    public static Response getCompanyInfo(String companyName, String address) {
        headerMap = GenerateHeader.getheader();
        cookieMap = GenerateHeader.getCookie(token);
        Map<String, Object> parameter = getParams(companyName, address);
        Response response = given().headers(headerMap).cookies(cookieMap).params(parameter).log().all()
                .get(getCompanyInfoUrl).then().statusCode(200).extract().response();
        response.asString();
        System.out.println("返回值：" + response.asString());
        return response;
    }

    /**
     * 构建查询公司信息的参数
     *
     * @param companyName
     * @param address
     * @return
     */
    private static Map<String, Object> getParams(String companyName, String address) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("companyName", companyName);
        paramsMap.put("address", address);
        return paramsMap;
    }
}
