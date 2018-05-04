package com.richgo.thrsys.customer;

import com.richgo.thrsys.entity.customer.*;
import com.richgo.thrsys.service.customer.IInvestorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxinyu on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class IInvestorServiceTest {
    @Autowired
    private IInvestorService iInvestorService;
    // todo 新增投资者转换申请
    @Test
    public void  test_saveInvestorReqSwitch()throws Exception{
        // 模拟插入数据：都为非必填
        Long result = iInvestorService.saveInvestorReqSwitch(null,null,null,null,null,null,null,"0");
        System.out.println(result);
        //输出结果：8000003638
    }

    // todo 查询投资者转换申请
    @Test
    public void test_queryInvestorReqSwitch(){
       /* 查询数据：  SELECT
        t.l_serialno,
                t.c_custno,
                c.c_custname,
                t.c_reqtype,
                t.c_provetype,
                t.f_provebalance,
                t.l_storageid,
                t.d_provevaliddate,
                t.c_status,
                t.c_creator,
                (select c_brokername FROM tfundbroker where c_usercode = t.c_creator) c_creatorname,
                t.d_createdate,
                t.c_auditor,
                (select c_brokername FROM tfundbroker where c_usercode = t.c_auditor) c_auditorname,
                t.c_auditoption,
                t.d_auditdate,
                t.c_systemfrom
        from tinvestor_req t LEFT JOIN vcustomerinfo c on t.c_custno = c.c_custno where t.c_reqtype in ('2',3)*/
        // 2017.12.20 liuyu 理顾宝v1.3 适当性二期 修改方法，获取投资者转换已办流程 普转专 取成功和驳回的，专转普取成功，驳回，撤销的状态
        Map<String,Object> result = iInvestorService.queryInvestorReqSwitch(null,null,null,1,10,"000000","2");
        System.out.println(result.size());
        //输出结果：total ；1 size：1

    }

    // todo 根据客户编号查询该客户是否存在并得到客户类型
    @Test
    public void test_getCustType(){
        //查询测试数据：SELECT C_CUSTNO FROM vcustomerinfo;
        String result = iInvestorService.getCustType("000007173116");
        System.out.println(result);
        //输出结果：0

    }
    // todo 根据客户编号和申请状态为1判断客户申请是否审核中
    @Test
    public void  test_getInvestorReqCount(){
        //查询测试数据：select C_CUSTNO from tinvestor_req t where t.c_status in('0','1','2','3') and t.c_reqtype in ('2','3')
        Integer result = iInvestorService.getInvestorReqCount("200007233414");
        System.out.println(result);
        //输出结果：2
    }
    // todo 根据客户编号判断是否非法转换请求
    @Test
    public void test_getInvestorReqCount1(){
      //查询测试数据：select C_CUSTNO from tinvestor_req where t.c_pubprofessionflag = '0'
        Integer result = iInvestorService.getInvestorReqCount1("200007233414","0");
        System.out.println(result);
        //输出测试结果：1
    }

    // todo getPubProfessionFlag
    @Test
    public  void  test_getPubProfessionFlag(){
        //查询测试数据：select t.C_CUSTNO  From vcustomerinfo t where   t.c_pubprofessionflag in ('0','1','2')
        Integer result = iInvestorService.getPubProfessionFlag("000007173758");
        System.out.println(result);
        // 输出结果：1
    }

    // todo 新增普通、专业投资者申请
    @Test
    public  void  test_saveInvestorReq()throws Exception{
        // 模拟插入数据：
        Integer result = iInvestorService.saveInvestorReq(null,null,null,null,null,null,null,null);
        System.out.println(result);
        //输出结果：1
    }
    // todo  查询申请编号是否存在
    @Test
    public void test_getSerialnoIsExist(){
        // 查询测试数据：select t.l_serialno  from tinvestor_req t ;
        Integer result = iInvestorService.getSerialnoIsExist(2L);
        System.out.println(result);
        //输出结果：1
    }
    // todo 通过序列号查询客户号，审核状态，系统来源
    @Test
    public void  test_getSystemFromBySerialno(){
        //查询测试数据：
       /* select l_serialno,c_custno,c_reqtype,c_provetype,f_provebalance,l_storageid,d_provevaliddate,c_status,c_creator,d_createdate,c_auditor,
        c_auditoption,d_auditdate,nvl(c_systemfrom,'0') c_systemfrom  from tinvestor_req t  取*/
        InvestorReqVO investorReqVO = iInvestorService.getSystemFromBySerialno(2L);
        System.out.println(investorReqVO.getAuditoption());
        //输出测试结果：个人客户普通投资者申请，默认审核通过!
    }

    // todo 通过客户号，申请编号查询用户状态是不是审核中
    @Test
    public void  test_getCStatusCount(){
        //查询测试数据：select t.c_custno,t.l_serialno  from tinvestor_req t where t.c_status = '1'
        Integer result = iInvestorService.getCStatusCount("200007348029",690L);
        System.out.println(result);
        //输出结果：1
    }

    //todo 修改普通、专业投资者申请
    @Test
    public void  test_editInvestorReq() throws Exception{
        //查询测试数据：select * from tinvestor_req;
        Integer result  = iInvestorService.editInvestorReq("000002","200007347602",2L, "1", "", null, "","","2");
        System.out.println(result);
        // 输出结果：1
    }

    // todo 根据申请编号查询客户编号,申请状态,系统来源,附件id,请求类型
    @Test
    public void  test_getInvestorReqSwitch(){
        // 查询测试数据：
        /*SELECT t.l_serialno,t.c_custno,t.c_status,nvl(t.c_systemfrom,'5') c_systemfrom,
                t.l_storageid,decode(t.c_reqtype,'2','0','3','1') c_reqtype,c_provetype FROM tinvestor_req t*/
        InvestorReqSwitchVO investorReqSwitchVO = iInvestorService.getInvestorReqSwitch(2L);
        System.out.println(investorReqSwitchVO.getCustNo());
        //输出结果：200007347602
    }

    // todo 修改投资者转换申请
    @Test
    public void test_updateInvestorReqSwitch()throws Exception{
        // 查询测试数据：select * from tinvestor_req;
        Integer result = iInvestorService.updateInvestorReqSwitch("000000","200007347602",2L,"1","",null,"","","1");
        System.out.println(result);
        //输出结果：1
    }

    //todo 修改投资者申请
    @Test
    public void test_updateInvestorReq()throws Exception{
        //查询测试数据:select * from tinvestor_req;
        Integer result = iInvestorService.updateInvestorReq("000000","200007347602",2L,"1","",null,"","","1","");
        System.out.println(result);
        //输出结果： 接口报错，对空字符串操作，storageid 可以为空，后台没判定
    }

    // todo removeInvestorReqSwitch
    @Test
    public  void  test_removeInvestorReqSwitch()throws Exception{
        // 查询测试数据：select * from tinvestor_req;
        InvestorReqSwitchVO investorReqSwitchVO = new InvestorReqSwitchVO();
        investorReqSwitchVO.setSerialNo(5L);
        Integer result = iInvestorService.removeInvestorReqSwitch("000000",investorReqSwitchVO);
        System.out.println(result);
        //输出结果：1
    }

    // todo 撤销申请
    @Test
    public void  test_cancelInvestorReq() throws Exception{
         // 查询测试数据： select * from tinvestor_req;
        Integer result =  iInvestorService.cancelInvestorReq("000000",9L);
        System.out.println(result);
         // 输出结果：1
    }

    // todo 查询投资者申请
    @Test
    public  void   test_queryInvestorReq(){
        // 查询测试数据：
      /*  SELECT
  t.l_serialno,
  t.c_custno,
  c.c_custname,
  t.c_reqtype,
  t.c_provetype,
  t.f_provebalance,
  t.l_storageid,
  t.d_provevaliddate,
  t.c_status,
  t.c_creator,
  (select c_brokername FROM tfundbroker where c_usercode = t.c_creator) c_creatorname,
  t.d_createdate,
  t.c_auditor,
  (select c_brokername FROM tfundbroker where c_usercode = t.c_auditor) c_auditorname,
  t.c_auditoption,
  t.d_auditdate,
  t.c_systemfrom
from tinvestor_req t LEFT JOIN vcustomerinfo c on t.c_custno = c.c_custno where t.c_reqtype in ('0','1')*/
        Map<String,Object> result = iInvestorService.queryInvestorReq(102L,"200007347630","",1,10,"H006970","");
        System.out.println(result);
        // 输出结果：total；1 data 的size 1

    }

     // todo 根据客户编号和申请状态为0判断客户申请是否以保存信息
    @Test
    public void  test_getInvestorReqCount2(){
        //查询测试数据：select l_serialno,C_CUSTNO from tinvestor_req t where t.c_status = '0' and t.c_reqtype in ('2','3')
        Long result = iInvestorService.getInvestorReqCount2("000007239702");
        System.out.println(result);
       // 输出结果：8000000794
    }

    // todo
    @Test
    public void  test_getInvestorReqCount3(){
        //查询测试数据：select C_CUSTNO  from tinvestor_req t where t.c_status in( '0','1','2','3') and t.c_reqtype in ('0','1')
        Integer result =  iInvestorService.getInvestorReqCount3("200007347602");
        System.out.println(result);
        //输出结果：1
    }
    // todo 提交请求则将保存的申请修改申请状态为1变为待审核状态
    @Test
    public void test_updateInvestorReqStatus(){
        // 查询测试数据: select t.l_serialno from tinvestor_req
        Integer result = iInvestorService.updateInvestorReqStatus(153L);
        System.out.println(result);
        //输出测试结果：1
    }

    // todo 根据客户类型将保存修改的申请修改为审核或完成状态
    @Test
    public void  test_updateInvestorReqStatus2(){
        //查询测试数据：select t.l_serialno from tinvestor_req
        Integer result = iInvestorService.updateInvestorReqStatus2(153L,"","");
        System.out.println(result);
        //输出结果：1
    }

    // todo 查询投资者申请详情
    @Test
    public void test_getInvestorReqInfo(){
        //查询测试数据：
        /*SELECT a.L_SERIALNO from TINVESTOR_REQ a LEFT JOIN VCUSTOMERINFO b ON a.C_CUSTNO = b.C_CUSTNO
        LEFT JOIN TBLOBSTORAGE c ON a.L_STORAGEID = c.L_STORAGEID */
        InvestorReqInfoVO investorReqInfoVO = iInvestorService.getInvestorReqInfo(171L);
        System.out.println(investorReqInfoVO.getCustomerName());
        //输出结果：北京浩正医药科技发展有限公司
    }

    // todo 根据申请编号查询附件信息
    @Test
    public  void  test_getblobStorageList(){
        // 查询测试数据：SELECT c_subkeyid FROM TBLOBSTORAGE WHERE  c_catalog='TINVESTOR_REQ'
        List<BlobStorageVO>  result = iInvestorService.getblobStorageList(1013L);
        System.out.println(result.size());
        //输出结果：2
    }

    // todo 根据客户编号判断是否为投资者
    @Test
    public  void  test_getInvestorReqCount4(){
        // 查询测试数据：SELECT c_custno FROM vcustomerinfo WHERE  c_pubprofessionflag in('0','1')
        Integer result = iInvestorService.getInvestorReqCount4("000007173196");
        System.out.println(result);
        // 输出结果：1
    }
    @Test
    public  void  test_getInvestorReqCountForLGB(){
      // 查询的sql select C_CUSTNO from tinvestor_req t where t.c_reqtype = '2'
        Integer integer = iInvestorService.getInvestorReqCountForFitness2("200007233414");
        System.out.println(integer);
    }

    @Test
    //根据客户编号查询对应的投资者、合格投资者信息
    public void test_getInvestorMsgByCustNo(){
        // 测试查询sql SELECT t.C_CUSTNO,t.C_ENDURE,t2.C_REQTYPE,t2.D_PROVEVALIDDATE,t1.C_STATUS,t1.D_PROVEVALIDDATE  FROM VCUSTOMERINFO t ,TQUALIFYINVESTOR_REQ t1,TINVESTOR_REQ t2  where t.C_CUSTNO = t1.C_CUSTNO and t1.C_CUSTNO = t2.C_CUSTNO
       // 000007173960
        Map<String,Object> map = iInvestorService.getInvestorMsgByCustNo("000007197138");
        System.out.println(map.size());
    }
    @Test
    public  void test_getAuditPassInvestorByDate(){
      //  Map<String,Object> map= iInvestorService.getAuditPassInvestorByDate("");
       // Map<String,Object> map= iInvestorService.getAuditPassInvestorByDate(null);
        // Map<String,Object> map= iInvestorService.getAuditPassInvestorByDate("20170801");
        Map<String,Object> map= iInvestorService.getAuditPassInvestorByDate("2017-08-01 00:00:00");
        System.out.println(map.size());
    }
}
