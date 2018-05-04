package com.richgo.thrsys.emp;

import com.richgo.thrsys.entity.ThrsysEmp;
import com.richgo.thrsys.service.emp.ThrsysEmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuxinyu on 2017/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysEmpServiceTest {
    @Autowired
    private ThrsysEmpService thrsysEmpService;

    //todo 根据员工编号查询员工信息
    @Test
    public  void  test_selectByCode(){
        //查询测试数据：select *  from htcf_org_psndoc_view 取 code 字段
        ThrsysEmp thrsysEmp = thrsysEmpService.selectByCode("H013571");
        System.out.println(thrsysEmp.getName()+"----"+thrsysEmp.getAge());
        //结果  调用接口失败 没有 EDU 字段

    }
    //todo 查询在职人员的证件号数量
    @Test
    public void  test_getCountByIdCard(){
        //查询测试数据：select * from htcf_org_psndoc_view where trnsevent!=4  取 id 字段
        Integer result = thrsysEmpService.getCountByIdCard("210211197409251421");
        System.out.println(result);
        //输出结果：1

    }
}
