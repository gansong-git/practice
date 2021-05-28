package com.practise.gansong.employeeTest;

import com.practises.gansong.requestMethod.GenerateHeader;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class GetDepartmentInfoTest {
    private static String name = "wangwu";
    private static String userId = "66666";
    private static String companyName;
    private static String companyAddress;
    private static String companyId;
    private static String department;
    private static String token = "testUserToken";
    private static Map<String, Object> headerMap;
    private static Map<String, Object> cookieMap;
    private static String getDepartmentInfoUrl = "http://localhost:8999/info/getDepartmentInfo";
    private static Response userInfoRs = null;
    private static Response conmpanyInfoRs = null;

    @BeforeMethod
    public void beforeMethod() {
        userInfoRs = GetUerInfoTest.searchEmployee(name, userId);
        companyName = userInfoRs.jsonPath().get("data.companyName").toString();
        companyAddress = userInfoRs.jsonPath().get("data.workingCity").toString();
        conmpanyInfoRs = GetCompanyInfoTest.getCompanyInfo(companyName, companyAddress);
        department = userInfoRs.jsonPath().get("data.department").toString();
        companyId = conmpanyInfoRs.jsonPath().get("data.companyId").toString();
    }

    @Test
    public void testGetCompanyInfo() {
        getCompanyInfo(companyId, department);
    }

    /**
     * 获取部门信息
     *
     * @param companyId
     * @param department
     * @return
     */
    public static Response getCompanyInfo(String companyId, String department) {
        headerMap = GenerateHeader.getheader();
//        headerMap = SendRequest.getRequestHeader(token);
        cookieMap = GenerateHeader.getCookie(token);
        Map<Object, Object> paramsMap = getParameter(companyId, department);
        Response response = given().headers(headerMap).cookies(cookieMap).body(paramsMap).log().all()
                .post(getDepartmentInfoUrl).then().statusCode(200).extract().response();
        response.asString();
        System.out.println("返回值：" + response.asString());
        return response;
    }

    /**
     * 构建获取部门信息的参数
     *
     * @param companyId
     * @param department
     * @return
     */
    private static Map<Object, Object> getParameter(String companyId, String department) {
        Map<Object, Object> paramsMap = new HashMap<Object, Object>();
        paramsMap.put("companyId", companyId);
        paramsMap.put("department", department);
        return paramsMap;
    }
}
