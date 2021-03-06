package com.zzqa.ds7000;

import com.alibaba.fastjson.JSON;
import com.zzqa.ds7000.dau_cfg.DAU_ALL_CFG;
import com.zzqa.ds7000.dau_cfg.DAU_CHL_VIB_CFG;
import com.zzqa.ds7000.pojo.Head7000;
import com.zzqa.ds7000.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class Ds7000ApplicationTests {

    @Test
    void contextLoads() throws Exception{
        Map<String,Object> map = new HashMap<>();
//        map.put("id", 1);
//        map.put("name", "张三");
        map.put("high", 1.8);
        JSON json = (JSON) JSON.toJSON(map);
        Student stu = json.toJavaObject(Student.class);
        System.out.println(stu.getId());
    }

    @Test
    void test01(){
        Map<String,Object> map = new HashMap<>();
        map.put("f", "1");
        map.put("a", "2");
        map.put("g", "3");

        Set<String> set = map.keySet();
        for (String s : set) {
            System.out.println(s);
        }
    }

    @Autowired
    private DAU_ALL_CFG dau_all_cfg;
    @Test
    void test02(){
        DAU_CHL_VIB_CFG vib_chl = dau_all_cfg.getVib_chl();
//        vib_chl.setDwStructLen(123);
//        setDau(vib_chl);
        DAU_CHL_VIB_CFG dau_chl_vib_cfg = new DAU_CHL_VIB_CFG();
        dau_chl_vib_cfg.setDwStructLen(1);
        vib_chl = dau_chl_vib_cfg;
        dau_all_cfg.setVib_chl(dau_chl_vib_cfg);
        System.out.println(dau_all_cfg.getVib_chl().getDwStructLen());
    }

    void setDau(DAU_CHL_VIB_CFG vib_chl){
        vib_chl.setDwStructLen(234);
    }

}
