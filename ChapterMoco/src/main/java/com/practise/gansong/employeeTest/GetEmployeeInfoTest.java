package com.practise.gansong.employeeTest;

import com.practises.gansong.requestMethod.GenerateHeader;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetEmployeeInfoTest {
    private static String name = "wangwu";
    private static String userId = "66666";
    private static String companyName = "test.inc";
    private static String companyAddress = "beijing";
    private static String companyId;
    private static String token = "testUserToken";
    private static Map<String, Object> headerMap;
    private static Map<String, Object> cookieMap;
    private static String getEmployeeInfoUrl = "http://localhost:8999/info/getEmployeeInfo";
    private Response userInfoRs = null;
    private Response companyInfoRs = null;

    @BeforeMethod
    public void beforeMethod(){
        userInfoRs = GetUerInfoTest.searchEmployee(name, userId);
        companyName = userInfoRs.jsonPath().get("data.companyName").toString();
        companyAddress = userInfoRs.jsonPath().get("data.workingCity").toString();
        companyInfoRs = GetCompanyInfoTest.getCompanyInfo(companyName, companyAddress);
        companyId = companyInfoRs.jsonPath().get("data.companyId");
    }

    @Test
    public void testGetEmployeeInfo(){
        getEmployeeInfo(companyId, userId);
    }

    /**
     * 获取员工信息
     * @param companyId
     * @param userId
     * @return
     */
    public static Response getEmployeeInfo(String companyId, String userId){
        Map<Object, Object> paramsMap = genereateParams(companyId, userId);
        headerMap = GenerateHeader.getheader();
        cookieMap = GenerateHeader.getCookie(token);
        Response response = given().headers(headerMap).cookies(cookieMap).body(paramsMap).log().all()
                .post(getEmployeeInfoUrl).then().statusCode(200).extract().response();
        response.asString();
        System.out.println("返回值：" + response.asString());
        return response;
    }

    /**
     * 生成请求参数
     * @param companyId
     * @param userId
     * @return
     */
    public static Map<Object, Object> genereateParams(String companyId, String userId){
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return map;
    }
}
