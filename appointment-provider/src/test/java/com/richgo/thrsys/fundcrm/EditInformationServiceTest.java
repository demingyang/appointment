package com.richgo.thrsys.fundcrm;

import com.richgo.thrsys.entity.fundcrm.ThrsysAuditParam;
import com.richgo.thrsys.service.fundcrm.EditInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by liuxinyu on 2017/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class EditInformationServiceTest {
    @Autowired
    private EditInformationService editInformationService;
    // todo 将修改的客户资料同步到多金
    @Test
    public void test_addAndAuditCustinfoChangeReq(){
      /*  查询测试数据：1 查询成交或者潜客表的客户 取客户编号 2， select *   from tcustmodifyaudit t
        where t.c_custno = '000007173752'
        and t.c_businflag ='01'
        and t.c_status in ('a','0','2')  将查询出来的这条数据删除   3 开始执行以下测试步骤 */
        ThrsysAuditParam param=new ThrsysAuditParam();
        param.setCustno("200007224996");
        param.setBusinflag("01");
        param.setSubmitter("0");
        param.setCusttype("1");
        param.setCbrokeraccount("0");
        param.setReqstatus("0");
        param.setCustname("地球防卫队");
        param.setIdentitytype("B");
        param.setIdentityno("110226198501272000");
        param.setMobileno("15801330421");
        param.setPhone("15801330421");
        param.setEmail("12@qq.com");
        param.setFaxno("000000");
        param.setZipcode("054500");
        param.setAddress("xxxxxx");
        param.setVocation("1");
        param.setBeneficary("我");
        param.setAuditor("0");
        param.setAuditflag("1");
        param.setAuditoption("刘鑫宇测试");
        Map<String,Object> result = editInformationService.addAndAuditCustinfoChangeReq(param);
        System.out.println(result);
    }

}
