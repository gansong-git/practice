package com.practise.gansong.employeeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeTest {
    public static void main(String[] args) throws Exception {
        System.out.println("返回结果：" + getSala(generateJsonObject()));;
    }

    private static String getSala(Map<Object, Object> map) throws Exception {
        String expectLevel = "9";
        String abc = "";
        Map<Object, Object> dataMap = (Map<Object, Object>) map.get("data");
        List<Map<Object, Object>> actualSalaryList = new ArrayList<Map<Object, Object>>();
        actualSalaryList = (List<Map<Object, Object>>) dataMap.get("salaryList");
        for (Map<Object, Object> object : actualSalaryList) {
            if (object.get("level").toString().compareTo(expectLevel) == 0) {
                abc = object.get("salary").toString();
            }else {
                throw new Exception("未查询到该等级！！！");
            }
        }
        return abc;
    }

    private static Map<Object, Object> generateJsonObject(){
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("status", 1);
        Map<Object, Object> dataMap = new HashMap<Object, Object>();
        dataMap.put("deptId", 16);
        List<Map<Object, Object>> salaListMap = new ArrayList<Map<Object, Object>>();
        Map<Object, Object> sala1Map = new HashMap<Object, Object>();
        sala1Map.put("level", 5);
        sala1Map.put("salary", "20000");
        Map<Object, Object> sala2Map = new HashMap<Object, Object>();
        sala2Map.put("level", 6);
        sala2Map.put("salary", "26000");
        salaListMap.add(sala1Map);
        salaListMap.add(sala2Map);
        dataMap.put("salaryList", salaListMap);
        map.put("data", dataMap);
        return map;
    }
}
