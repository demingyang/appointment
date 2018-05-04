package com.richgo.thrsys.org;

import com.richgo.thrsys.entity.ThrsysOrg;
import com.richgo.thrsys.service.org.ThrsysOrgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxinyu on 2017/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysOrgServiceTest {
    @Autowired
    private ThrsysOrgService thrsysOrgService;
    // todo 根据机构编号查询机构信息
    @Test
    public void  test_selectByCode(){
        // 查询测试数据：select * from htcf_org_orgs_view  取code
        ThrsysOrg thrsysOrg = thrsysOrgService.selectByCode("0101");
        System.out.println(thrsysOrg.getName()+"---"+thrsysOrg.getOrgType());
        //输出结果 高层领导---部门
    }
  // todo 根据人员编码关联查询组织信息
    @Test
    public void test_getOrgsInfo(){
        //查询测试数据：select * from htcf_org_orgs_view  取code
        ThrsysOrg thrsysOrg = thrsysOrgService.getOrgsInfo("0101");
        System.out.println(thrsysOrg.getName()+"---"+thrsysOrg.getOrgType());
        //输出结果 高层领导---部门
    }
    // todo 获取已启用的大区部门编码列表
    @Test
    public void  test_getOrgList(){
        // 无参数 直接调用
        List<ThrsysOrg> resultList = thrsysOrgService.getOrgList();
        System.out.println(resultList.size());
       // 输出结果：22
    }
    // todo 获取已启用的大区部门编码列表 分页 按照部门名称和部门编号可以模糊查询
    @Test
    public void test_getOrgListByPage(){
        /* 插叙你测试数据:select
        org.CODE as code,
        org.NAME as name
        FROM HTCF_ORG_ORGS_VIEW org
        WHERE org.DEPTLEVEL LIKE '%大区%'
        AND org.ENABLESTATE = '已启用'  025051  广佛区 */
        Map<String, Object>  resultMap =  thrsysOrgService.getOrgListByPage("广","",1,10);
        System.out.println(resultMap.size());
        //输出结果：total：4 list的size 4
    }

}
