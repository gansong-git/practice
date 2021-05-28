package com.practise.gansong.employeeTest;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetEmployeeSalaryTest {
    private static String name = "wangwu";
    private static String userId = "66666";
    private static String companyName = "test.inc";
    private static String companyAddress = "beijing";
    private static String token = "testUserToken";
    private static String companyId;
    private static String deptId;
    private static String department;
    private static String salary;
    private static String staffLevel;
    private static String getSalaryListUrl = "http://localhost:8999/salary/getSalaryList";
    private static Response userInfoRs = null;
    private static Response companyInfoRs = null;
    private static Response departmentRs = null;
    private static Response salaryRs = null;
    private static Response employeeRs = null;
    private static List<Map<Object, Object>> salaryList;

    @BeforeMethod
    public void beforeMethod(){
        userInfoRs = GetUerInfoTest.searchEmployee(name, userId);
        companyName = userInfoRs.jsonPath().get("data.companyName").toString();
        companyAddress = userInfoRs.jsonPath().get("data.workingCity").toString();
        department = userInfoRs.jsonPath().get("data.department").toString();
        companyInfoRs = GetCompanyInfoTest.getCompanyInfo(companyName, companyAddress);
        companyId = companyInfoRs.jsonPath().get("data.companyId").toString();
        employeeRs = GetEmployeeInfoTest.getEmployeeInfo(companyId, userId);
        staffLevel = employeeRs.jsonPath().get("data.staffLevel").toString();
        departmentRs = GetDepartmentInfoTest.getCompanyInfo(companyId, department);
        deptId = departmentRs.jsonPath().get("data.deptId").toString();
        System.out.println("部门ID：" + deptId);
        salaryRs = GetSalaryTest.getsSalaryList(companyId, deptId);
        salaryList = salaryRs.jsonPath().get("data.salaryList");
    }

    @Test
    public void testgetSalary() throws Exception {
        getEmployeeSalary();
        System.out.println("salary等于：" + salary);
    }

    /**
     * 通过姓名和用户id查薪水
     * @return
     */
    public static void getEmployeeSalary() throws Exception {
        int times = 0;
        for (Map<Object, Object> object : salaryList) {
            times ++;
            if (Integer.parseInt(object.get("level").toString()) == Integer.parseInt(staffLevel)) {
                salary = object.get("salary").toString();
            } else{
                continue;
            }
        }
        if(times >= salaryList.size()){
            if (salary == null){
                throw new Exception("can not find level to match the stafflevel from salary list。。");
            }
        } else {
            return;
        }
    }
}
