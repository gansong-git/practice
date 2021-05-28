package com.practise.gansong.employeeTest;

import com.practise.gansong.requestMethod.GenerateHeader;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetSalaryTest {
    private static String name = "wangwu";
    private static String userId = "66666";
    private static String companyName = "test.inc";
    private static String companyAddress = "beijing";
    private static String token = "testUserToken";
    private static String companyId;
    private static String deptId;
    private static String department;
    private static Map<String, Object> headerMap;
    private static Map<String, Object> cookieMap;
    private static String getSalaryListUrl = "http://localhost:8999/salary/getSalaryList";
    private Response userInfoRs = null;
    private Response companyInfoRs = null;
    private Response departmentRs = null;

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("-------------------开始执行测试前的方法，准备请求参数-------------------");
        userInfoRs = GetUerInfoTest.searchEmployee(name, userId);
        companyName = userInfoRs.jsonPath().get("data.companyName").toString();
        companyAddress = userInfoRs.jsonPath().get("data.workingCity").toString();
        department = userInfoRs.jsonPath().get("data.department");
        companyInfoRs = GetCompanyInfoTest.getCompanyInfo(companyName, companyAddress);
        companyId = companyInfoRs.jsonPath().get("data.companyId");
        departmentRs = GetDepartmentInfoTest.getCompanyInfo(companyId, department);
        deptId = departmentRs.jsonPath().get("data.deptId").toString();
        System.out.println("部门ID：" + deptId);
        System.out.println("-------------------beforeMethod执行完成-------------------");

    }

    @Test
    public static void testGetSalaryList(){
        getsSalaryList(companyId, deptId);
    }

    /**
     * 获取薪水列表
     * @param companyId
     * @param deptId
     * @return
     */
    public static Response getsSalaryList(String companyId, String deptId){
        headerMap = GenerateHeader.getheader();
        cookieMap = GenerateHeader.getCookie(token);
        Map<Object, Object> paramsMap = generateParams(companyId, deptId);
        Response response = given().headers(headerMap).cookies(cookieMap).body(paramsMap).log().all()
                .post(getSalaryListUrl).then().statusCode(200).extract().response();
        response.asString();
        System.out.println("返回值：" + response.asString());
        return response;
    }

    /**
     * 生成请求参数
     * @param companyId
     * @param deptId
     * @return
     */
    private static Map<Object, Object> generateParams(String companyId, String deptId){
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("companyId", companyId);
        map.put("deptId", deptId);
        return map;
    }
}
