package com.richgo.thrsys.fundcrm;

import com.richgo.thrsys.entity.fundcrm.RegistVO;
import com.richgo.thrsys.service.fundcrm.ThrsysFundcrmService;
import com.sun.xml.internal.ws.api.server.SDDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuxinyu on 2017/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysFundcrmServiceTest {
    @Autowired
    private ThrsysFundcrmService thrsysFundcrmService;
    //todo 用户注册和实名认证
    @Test
    public void test_addCustRegister()throws Exception{
        // 模拟新用户注册和实名
        RegistVO registVO = new RegistVO();
        registVO.setMobileno("15545534954");
        registVO.setCustname("刘鑫宇接口测试适当");
        registVO.setCusttype("1");
        registVO.setIdentitytype("0");
        registVO.setIdentityno("232331199105041013");
        registVO.setFundbrokerID("H000109");
        registVO.setPassword(String.valueOf((Math.random()*9+1)*100000).substring(0,6));
        registVO.setStatus("1");// 提交注册客户申请
        registVO.setResult("1");//审核结果(通过)
        Map<String,Object> resultMap = thrsysFundcrmService.addCustRegister(registVO);
        System.out.println(resultMap);
    }

    //todo 绑定银行卡
    @Test
    public void  test_bindBankCard(){
         //以新注册的客户编号为例  进行绑定银行卡  客户编号：200007380155
        String bankno = "012";
        String custname = "刘鑫宇接口测试适当";
        String bankacco = "6217002710000684874";// 银行卡号
        String custno = "200007380155";
        String from = "3"; //来源 DATACNT | 1119
        String sysparam = "00000000";//第一位表示是否限制注册客户才能绑卡 其它待定*//*
        Map<String,Object> map= thrsysFundcrmService.bindBankCard(custno,  custname,  null,  null,  null,  bankno,  null,
                bankacco,  null,  null,  null,  from,  sysparam);
        System.out.println(map);
    }

    //todo 实名认证接口
    @Test
    public  void  test_realNameAuthentication(){
        /* 查询测试数据 57 库 之前做过注册测试  有数据，拿这些数据来比较
         select C_IDNOCHECKFLAG,C_CUSTNAME from TUNREGISTER where C_CUSTNAME like '%刘鑫宇%' and C_IDNOCHECKFLAG is null;
         取出客户信息*/

        // TODO 说明:数据库中潜客表：tunregister 表中字段:c_etradeauth,是实名接口：SP_BIND_BANKCARD 存储的验证字段，该字段为空，
        // todo  会报错：找不到注册客户。按照多金代码逻辑来讲，潜客表中 c_etradeauth 是不可能为空的。字段为空的原因目前只发现是调用api时候
        // todo /openapi/fundrestful/account/dcpotentialcustregist  必填入参：trade_prove_result 没有填写，但是调用api没有报错，并且返回成功，造成的。
        String custno = "200007380156";
        String custname = "刘鑫宇测试实名";
        String custtype = "1";
        String idtype = "0";
        String idno = "232331199205041014";
        String mobileno = "13601094934";
        Map<String,Object> resultMap = thrsysFundcrmService.realNameAuthentication(custno, custname, custtype, idtype, idno,
                 mobileno, null);
        System.out.println(resultMap);
          // map参数   status :0000  message :实名认证成功
    }

     // todo 调整理顾关系
    @Test
    public  void  test_assignFundBrokerRelations(){
        //查询测试数据：SELECT  a.C_CUSTNO,b.C_BROKERACCOUNT from TCUSTOMERINFO a,TFUNDBROKERRELATION b where a.C_CUSTNO = b.C_CUSTNO ORDER BY a.C_CUSTNO
        Map<String,Object>  map = thrsysFundcrmService.assignFundBrokerRelations("000007173118","H009493,H005443",  "000002","0,0",null);
        System.out.println(map.size());
        //输出测试结果：status:0000 message:移除关系成功
    }


}
