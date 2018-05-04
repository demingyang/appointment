package com.richgo.thrsys.activity;

import com.richgo.thrsys.entity.activity.Tcustomerinfo;
import com.richgo.thrsys.entity.activity.Tunregister;
import com.richgo.thrsys.service.activity.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuxinyu on 2017/11/15.
 * 测试活动类接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ActivityServiceTest {
    @Autowired
    private ActivityService activityService;
    @Test
    // todo 判断新用户是否实名认证和银行卡认证
    public  void  test_isAuthCard(){
        // 查询测试数据 ：select * from TUNREGISTER where C_ISDELETE = '0' and C_IDNOCHECKFLAG = 1 取 c_custno
       boolean result  =  activityService.isAuthCard("200007352364");
        System.out.println(result);
       // 输出 true
    }
    @Test
   // todo 判断用户是否进行问卷调查
    public  void test_isAnswerQuestionnaire(){
        //查询测试数据：select *  from TSURVEY_ANSWERINFO where F_SCORE>0; 取c_objno
     boolean result = activityService.isAnswerQuestionnaire("200007352188");
        System.out.println(result);
        //输出 true
    }
    // todo 判断用户是否进行合格投资者认证
    @Test
    public  void test_isAuthoInvestor(){
        // 查找测试数据： select * from TQUALIFYINVESTOR_REQ where C_STATUS = 2;   取c_custno 字段
        boolean  result  = activityService.isAuthoInvestor("000007283331");
        System.out.println(result);
        //输出 true
    }
  // todo  判断用户是否第一次投资
    @Test
    public  void  test_isFirstIverst(){
        // 查询测试数据 ：SELECT  * from  TUNREGISTER  where C_ISDELETE = 0 and  C_FACTCUSTNO is not null;  取 c_suctno
        boolean  result  = activityService.isFirstIverst("200007227711");
        System.out.println(result);
        // 输出 true
    }
    // todo 查询潜客表，该新用户是否存在
    @Test
    public void test_selectIsExistByCustno(){
        // 查询测试数据：select * from TUNREGISTER ; 取 c_custno
        Integer result = activityService.selectIsExistByCustno("200007228110");
        System.out.println(result);
        // 输出：1
    }

    //todo 查询成交客户表，该老客户是否存在
    @Test
    public void  test_selectCustomerExistByCustno(){
        // 查询测试数据：select * from TCUSTOMERINFO ; 取c_custno
        Integer result = activityService.selectCustomerExistByCustno("000007173752");
        System.out.println(result);
        //输出结果：1
   }
  // todo 查询浅客用户姓名
    @Test
    public  void  test_selectNameByCode(){
        //查询测试数据：select * from TUNREGISTER ; 取c_custno
        String result  = activityService.selectNameByCode("200007228110");
        System.out.println(result);
        //输出结果：陈志勇
    }
  // todo 查询潜客用户信息
    @Test
      public void  test_selectTunregister(){
        //查询测试数据：select * from TUNREGISTER ; 取c_custno
        Tunregister tunregister = activityService.selectTunregister("200007228110");
        System.out.println(tunregister.getcMobileno()+"---------"+tunregister.getcIdentityno());
        //输出结果：13601381668---------null
      }

     //todo 查询客户表客户信息
    @Test
     public void test_selectCustomerInfo(){
          // 查询测试数据：select * from TCUSTOMERINFO ; 取c_custno
         Tcustomerinfo tcustomerinfo = activityService.selectCustomerInfo("000007173752");
         System.out.println(tcustomerinfo.getcMobileno()+"----"+tcustomerinfo.getcIdentityno());
         //输出结果：15715702951----51126201-0
     }

   //todo 查询客户是否购买非稳金稳裕产品
    @Test
    public  void  test_selectNotWJWY(){
        //查询测试数据：select * from TSHARECURRENTS ts,TFUNDINFO tf where ts.C_FUNDCODE=tf.C_FUNDCODE and tf.C_FUNDNAME not like '%稳金%' and tf.C_FUNDNAME not like '%稳裕%'
        // 取 c_custno 字段
        Integer result = activityService.selectNotWJWY("000007202710");
        System.out.println(result);
        //输出结果：2
    }

     // todo 根据成客户编号查询该客户的证件号码
    @Test
    public void  test_selectIdentitynoByCustno(){
        // 查询测试数据：select C_CUSTNO from TCUSTOMERINFO
        String  result = activityService.selectIdentitynoByCustno("000007202710");
        System.out.println(result);
        // 输出结果：310101197005191229
    }

}
