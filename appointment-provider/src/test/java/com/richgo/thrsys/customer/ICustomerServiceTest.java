package com.richgo.thrsys.customer;



import com.common.util.DateUtil;
import com.richgo.thrsys.entity.customer.*;
import com.richgo.thrsys.service.customer.ICustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by zhouxj on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ICustomerServiceTest {
    @Autowired
    private ICustomerService iCustomerService;

    @Test
    public void testgetCustomerList() throws Exception {
        List<CustomerTypeVO>  list=iCustomerService.getCustomerList("H004985","",1,10);
        /*  for(CustomerTypeVO  vo:list){
           System.out.println(vo.getEmpNo());
        }*/
        System.out.println(list.size());
    }
    @Test
    public  void  testsearchCustomerList()  throws  Exception{
        Map<String, Object>  map=iCustomerService.searchCustomerList("H000109","张",1,10);
        List<CustomerTypeVO> searchCustomerList=(List)map.get("list");
       /* for(CustomerTypeVO  vo:searchCustomerList){
            System.out.println(vo.getCustomerName());
            System.out.println(vo.getEmpNo());
        }*/
        System.out.println(map.get("total"));
        System.out.println(searchCustomerList.size());
    }
    @Test
    public  void  testgetCustomerInfo()  throws Exception{
        CustomerInfoVO  vo=iCustomerService.getCustomerInfo("H002187","000007173201");
        System.out.println(vo.getCustomerName()+","+vo.getCustomerId());
    }
    @Test
    public   void  testgetAllCustomerList()   throws  Exception{
        Map<String, Object>  map=iCustomerService.getAllCustomerList(1,10);
        System.out.println(map.get("list"));
    }
    @Test
    public  void  testsearchCustomerVO()   throws  Exception{
        CustomerVO  vo=new CustomerVO();
        Map<String, Object>  map=iCustomerService.searchCustomerVO(vo,1,10);
        List<CustomerVO> customerVOList=(List<CustomerVO>)map.get("list");
      /*  for(CustomerVO  cus:customerVOList){
            System.out.println(cus.getCustNo()+","+cus.getName());
        }*/
        System.out.println(map.get("total"));
        System.out.println(customerVOList.size());
    }

    @Test
    public  void  testsearchCustomerVO1()  throws  Exception{
        Map<String, Object>  map=iCustomerService.searchCustomerVO(null,null,null,null,null,null,null,null,1,10);
        List<CustomerVO> customerVOList=(List<CustomerVO>)map.get("list");
     /*   for(CustomerVO  cus:customerVOList){
            System.out.println(cus.getCustNo()+","+cus.getName());
        }*/
        System.out.println(customerVOList.size());
        System.out.println(map.get("total"));
    }

    @Test
    public  void  testgetCustomerVOInfo()  throws  Exception{
        CustomerVO  vo=new CustomerVO();
        vo.setCustNo("210007261724");
        vo.setAccountType("1");
        CustomerVO  cusvo=iCustomerService.getCustomerVOInfo(vo);
        System.out.println(cusvo.getCustNo());
    }
    @Test
    public  void  testgetCustomerVONameInfo()  throws  Exception{
        CustomerVO  vo=iCustomerService.getCustomerVONameInfo("210007261724");
        System.out.println(vo.getCustNo()+","+vo.getName());
    }

    @Test
    public  void  testgetCustomerHomeInfo()  throws  Exception{
        CustomerHomeInfoVO  vo=new CustomerHomeInfoVO();
        vo.setCustNo("000007173698");
        CustomerHomeInfoVO  info=iCustomerService.getCustomerHomeInfo(vo);
        System.out.println(info.getCustNo()+","+info.getCustName());
    }
    @Test
    public  void  testgetOwnCustomers()  throws  Exception{
        Map<String,Object>  map=iCustomerService.getOwnCustomers("H000109",1,10);
        List<CustomerVO> list = (List<CustomerVO>) map.get("list");
        System.out.println(list.size());
        System.out.println(map.get("total"));
    }
    @Test
    public  void  testgetCustomerByBirthday()  throws  Exception{
        List<CustomerVO>  list=iCustomerService.getCustomerByBirthday(DateUtil.toDateYmd("20170613"));
     /*   for(CustomerVO  vo:list){
            System.out.println(vo.getCustNo());
        }*/
        System.out.println(list.size());
    }
    // todo 根据时间获取当天过生日用户量
    @Test
    public  void  test_getBirthdayCustomerCount()throws Exception{
        // 查询测试数据：SELECT C_BIRTHDAY from vcustomerinfo t where  t.C_BIRTHDAY is not null and t.C_VIPLEVEL is not null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse("19521221");
        Integer result = iCustomerService.getBirthdayCustomerCount(date);
        System.out.println(result);
        //输出测试结果：186
    }

    //todo  根据时间获取当天过生日用户列表
    @Test
    public  void  test_getBirthdayCustomerList()throws Exception{
        // 查询测试数据：SELECT t.C_BIRTHDAY from vcustomerinfo t where  t.C_BIRTHDAY is not null and t.C_VIPLEVEL is not null
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse("19521221");
        List<CustomerVO> result = iCustomerService.getBirthdayCustomerList(date,1,10);
        System.out.println(result.size());
        //输出结果：10
    }

    // todo  获取一年内客户的资产
    @Test
    public  void   test_getTotalAssetsByYear(){
        //查询测试数据：select c_custno  from  tstaticshares
        Double result = iCustomerService.getTotalAssetsByYear("000007216413");
        System.out.println(result);
        //输出结果：100000
    }
    // todo 获取客户总资产 专属查所有金额
    @Test
    public  void  test_getTotalAssetsIsMain(){
        //查询测试数据：select t.c_custno from  tstaticshares t
        Double result =  iCustomerService.getTotalAssetsIsMain("000007216413");
        System.out.println(result);
        // 输出结果：100000
    }

    // todo 获取一年内客户的交易量（合同）
    @Test
    public  void  test_getCustomerContractByYear(){
        //查询测试数据：select t.C_CUSTNO from tsharecurrents t;
        Double result  = iCustomerService.getCustomerContractByYear("000007209289");
        System.out.println(result);
        //输出结果：0.0
    }

    // todo 获取客户资产列表
    @Test
    public  void test_getCustomerAssetsList(){
        // 查询测试数据：
       /* select c.C_CUSTNO,c.C_BROKERACCOUNT
        from (select DISTINCT  C_BROKERACCOUNT,c_custno,C_FUNDCODE from  treserve  ) c,
                tstaticshares t
        left  join tfundinfo s on  s.c_fundcode=t.c_fundcode
        left join tsaleprofundrelation tf on s.c_fundcode=tf.c_fundcode
        left join  tsaleproject j on  tf.l_proserialno=j.l_proserialno
        where  j.c_deptnodecode like '00010001%' and  t.c_custno=c.c_custno and t.c_fundcode = c.c_fundcode
        and f_realshares>0
        order by t.d_lastmodify desc */
        List<CustomerAssetsVO> result = iCustomerService.getCustomerAssetsList("000007213304","H003394",1,10);
        System.out.println(result.size());
        //输出结果：3
    }

    // todo 原getCustomerAssetsList增强接口，除客户id和理顾id外参数外，通过离职理顾集合，是否专属查询获取客户资产列表
    @Test
    public  void  test_getCustomerAssetsByQuitEmpsList(){
         // 查询测试数据：
      /*  select t.c_custno,c.C_BROKERACCOUNT
        from (select DISTINCT  c_custno,C_FUNDCODE,C_BROKERACCOUNT from  treserve
                where  c_status in ('0','3','4','7','b','d') ) c,
                tstaticshares t
        left  join tfundinfo s on  s.c_fundcode=t.c_fundcode
        left join tsaleprofundrelation tf on s.c_fundcode=tf.c_fundcode
        left join  tsaleproject j on  tf.l_proserialno=j.l_proserialno
        where  j.c_deptnodecode like '00010001%' and  t.c_custno=c.c_custno and t.c_fundcode = c.c_fundcode
        and f_realshares>0
        order by t.d_lastmodify desc*/
        List<String> list = new ArrayList<String>();
        list.add("H014551");
        List<CustomerAssetsVO> result = iCustomerService.getCustomerAssetsByQuitEmpsList("000007323225","H014551",list,true,1,10);
        System.out.println(result.size());
        // 输出结果：3

    }

    // todo 原getTotalAssetsByCustomerId 增强接口，除客户id和理顾id外参数外，通过离职理顾集合，是否专属查询获取客户资产总值
    @Test
    public  void  test_getTotalAssetsByQuitEmpsList(){
        List<String> list = new ArrayList<String>();
        list.add("H014551");
        Double result = iCustomerService.getTotalAssetsByQuitEmpsList("H014551","000007323225",list,false);
        System.out.println(result);
        // 输出结果：0.0
    }

    // todo 获取客户银行卡列表
    @Test
    public void test_getBankCardList(){
        // 查询测试数据：
        /* select t.c_custno from taccobank t,tdictionary d  where  t.c_bankno=d.c_keyvalue and d.l_keyno=3010
        order by t.c_state desc , t.d_createdate desc nulls last */
        List<BankCardInfoVO>  result = iCustomerService.getBankCardList("000007173698",null,1,10);
        System.out.println(result.size());
        //输出结果：2
    }

    // todo  获取客户银行卡列表，比getBankCardList多加了操作人字段查询
    @Test
    public  void   test_getBankCardListByEmpNo(){
       //查询测试数据：
        /*  select t.c_custno from taccobank t,tdictionary d  where  t.c_bankno=d.c_keyvalue and d.l_keyno=3010
        order by t.c_state desc , t.d_createdate desc nulls last  */
        List<BankCardInfoVO> result = iCustomerService.getBankCardListByEmpNo("000007174653",null,null,1,10);
        System.out.println(result.size());
        //输出结果：1
    }

    // todo  获取客户预约列表
    @Test
    public  void  test_getAppointmentList(){
        // 查询测试数据：  取 s.c_custno,s.c_status
        /* select  distinct s.c_reserveno, j.c_name c_fundname, v.c_custname, s.c_custno,s.c_status , s.f_balance ,
                s.d_reservedate
        from treserve s  left join vcustomerinfo v on v.c_custno=s.c_custno
        left join  tsaleproject j on  s.l_proserialno=j.l_proserialno
        order by s.d_reservedate desc*/
        List<CustomerAppointmentVO> result = iCustomerService.getAppointmentList(null,"200007296936","1",null,1,10);
        System.out.println(result.size());
        //输出结果：10
    }

    // todo 获取客户预约详情
    @Test
    public  void  test_getAppointmentInfo(){
        // 查询测试数据：
       /* select distinct s.c_reserveno,j.c_name,s.c_mobileno,d.c_caption c_bankname,s.c_subbranchname c_nameinbank, s.c_bankacco ,s.f_balance,s.d_reservedate,
                s.d_remitdate, s.c_status ,nvl(j.f_subscriptionratio,0)*100||'%' rates,s.d_expirationdate,nvl(s.c_docfillstatus,'0') c_docfillstatus,s.d_contractsigndate,
                S.f_balance*j.f_subscriptionratio ratesFee,s.d_reservationsuccessdate,s.f_totalpaybalance , s.c_capitalstatus c_paystatus
        from  treserve s
        left join tdictionary d on d.c_keyvalue =s.c_bankname and l_keyno=3010 and d.c_sysname='FUNDCRM'
        left join  tsaleproject j on  s.l_proserialno=j.l_proserialno    取c_reserveno 字段*/
        CustomerAppointmentInfoVO result = iCustomerService.getAppointmentInfo("201512150000082049");
        System.out.println(result.getTsaleName());
        //输出结果：中泰-惠盈8号集合资金信托计划
    }

    // todo 查询需要视频面签的项目
    @Test
    public  void  test_getIsVideoInterview(){
        //查询测试数据：
       /*  select r.c_reserveno from treserve r,tsaleproject t
        where r.l_proserialno=t.l_proserialno
        and t.c_isinterview  = '1'
        and r.c_status in('4','7','d') */
         Integer result = iCustomerService.getIsVideoInterview("201605050000117262");
         System.out.println(result);
        //输出结果：1
    }

    // todo  获取大容量存储的文件名及仓库编号列表
  @Test
    public  void  test_getBlobStorageDataList(){
        //查询测试数据：select  s.C_CATALOG,s.C_SUBKEYID   from  tblobstorage s
      List<BlobStorageVO> result = iCustomerService.getBlobStorageDataList("TACCOBANK","Z1008000002838");
      System.out.println(result.size());
      //输出结果：1
  }

  // todo  获取视频签约的状态
    @Test
    public  void  test_getVideoStatus(){
       //查询测试数据：select tv.c_objno  from tvideorequest  tv where  d_reservetime is not null
        String result = iCustomerService.getVideoStatus("201705170000257236");
        System.out.println(result);
        // 输出结果：1
    }

    // todo 获取客户合同信息
    @Test
    public  void  test_getCustomerContractList(){
        // 查询测试数据：  取 s.c_custno
       /* select  s.l_serialno, s.c_custno,s.c_bankname, s.c_bankacco,s.f_contractsignbalance ,j.c_name,
                f.d_setupdate,f.d_contractenddate,
                decode(f.c_fundstatus, '0', '发行',  '6', '已结束', '9', '已结束', '存续') c_fundstatus
        from ttrustcontractdetails s left join  tfundinfo f on s.c_fundcode=f.c_fundcode
        left join tsaleprofundrelation tf on f.c_fundcode=tf.c_fundcode
        left join  tsaleproject j on  tf.l_proserialno=j.l_proserialno
        where  j.c_deptnodecode like '00010001%'
        order by c_fundstatus, s.d_contractsigndate  desc*/
        List<CustomerContractInfoVO> result = iCustomerService.getCustomerContractList("000007306071",null,1,10);
        System.out.println(result.size());
        // 输出结果：2
    }

    // todo 获取我的客户信息
    @Test
    public  void  test_getMyCustomerList(){
        //查询测试数据：
       /* SELECT
        td.c_brokeraccount,
                tcd.l_proserialno,
                td.c_fundcode
        FROM ttrustcontractdetails td
        LEFT JOIN tcapitalcurrents tcd ON td.c_brokeraccount = tcd.c_brokeraccount AND td.c_custno = tcd.c_custno
        LEFT JOIN tdictionary d ON d.c_keyvalue = tcd.c_bankname
        WHERE d.l_keyno = 3010 AND d.c_sysname = 'FUNDCRM'
        ORDER BY tcd.d_capitaldate desc*/
        List<MyCustomerInfoVO>  result = iCustomerService.getMyCustomerList("H006042","26764",1,10);
        System.out.println(result.size());
        // 输出结果：2
    }

    // todo 获取客户总资产
    @Test
    public  void  test_getTotalAssetsByCustomerId(){
        // 查询测试数据：
        /* select c.c_custno,c.C_BROKERACCOUNT from  tstaticshares t,
                (select DISTINCT s.c_custno, s.C_FUNDCODE,s.C_BROKERACCOUNT from  treserve s  ) c
        where t.c_custno=c.c_custno and t.C_FUNDCODE = c.C_FUNDCODE and t.f_realshares is not null */
        Double result = iCustomerService.getTotalAssetsByCustomerId("H004421","000007198599");
        System.out.println(result);
        // 输出结果：2427588.07
    }

    // todo 获取理顾下所有成交客户总数
    @Test
    public  void  test_getCustomerSumByEmpNo(){
         // 查询测试数据：
       /*  select f.c_brokeraccount
        from vcustomerinfo t , tfundbrokerrelation f
        where  t.c_custno=f.c_custno and t.c_custstatus in ('Y','S') */
        Integer result = iCustomerService.getCustomerSumByEmpNo("H000004");
        System.out.println(result);
         // 输出结果：82
    }

    // todo 根据卡号获取用户银行卡信息
    @Test
    public  void   test_getBankCardByBankAccount(){
        // 查询测试数据：select t.c_custno,t.c_bankacco from taccobank t,tdictionary d  where  t.c_bankno=d.c_keyvalue and d.l_keyno=3010
        BankCardInfoVO result = iCustomerService.getBankCardByBankAccount("6217001210039956014","200007272386");
        System.out.println(result.getBankCardName());
        //输出结果：姚丹
    }

    // todo 根据姓名模糊搜索客户列表
    @Test
    public  void  test_searchCustomerListByName(){
        //查询测试数据：
        /* select f.C_BROKERACCOUNT,t.C_CUSTNAME
        from vcustomerinfo t left join tfundbrokerrelation f  on t.c_custno=f.c_custno
        where t.c_custstatus in ('S','Y','A','N','R')
        order by t.c_custno desc */
        Map<String, Object> result = iCustomerService.searchCustomerListByName("H004324","咸杰","1",1,10);
        System.out.println(result.size());
        //输出结果：total :1 list 的 size 1
    }

    // todo 根据客户ID、审核状态、合格投资者认定申请ID 查询 合格投资者认定申请列表
    @Test
    public  void  test_searchQualifyInvestorReqList(){
        //查询测试结果：
       /* select
        t.l_serialno,
                t.c_custno,
                tc.C_CUSTNAME as c_custname,
        t.c_provetype,
                t.f_provebalance,
                t.l_storageid,
                t.d_provevaliddate as c_provevaliddate,
        t.c_status,
                t.c_creator,
                tf1.C_BROKERNAME as c_creatorname,
        t.D_CREATEDATE as c_createdate,
                t.c_auditor,
                tf.C_BROKERNAME as c_aduitorname,
        t.c_auditoption,
                t.D_AUDITDATE as c_auditdate,
        t.c_systemfrom
        from tqualifyinvestor_req t LEFT JOIN VCUSTOMERINFO tc on t.C_CUSTNO = tc.C_CUSTNO
        LEFT JOIN tfundbroker tf on t.C_AUDITOR = tf.C_BROKERACCOUNT
        LEFT JOIN TFUNDBROKER tf1 on t.C_CREATOR = tf1.C_BROKERACCOUNT
        where 1=1
        order by t.l_serialno desc;*/
        Map<String, Object> result = iCustomerService.searchQualifyInvestorReqList("200007359611","1",8000002841L,"H000138",null,1,10);
        System.out.println(result.size());
        //输出结果：total：1 list 的size 1
    }

    // todo  根据合格投资者认定申请ID 查询 合格投资者认定申请对象
    @Test
    public  void  test_getQualifyInvestorReq(){
        //查询测试数据：select t.L_SERIALNO from tqualifyinvestor_req t
        QualifyInvestorReq result  = iCustomerService.getQualifyInvestorReq(12L);
        System.out.println(result.getStatus());
        //输出结果：3
    }

    // todo  根据客户ID  查询 合格投资者认定申请列表
    @Test
    public  void  test_getQualifyInvestorReqByCustNo(){
        //查询数据：select t.C_CUSTNO from tqualifyinvestor_req t
        List<QualifyInvestorReq> result = iCustomerService.getQualifyInvestorReqByCustNo("000007210750");
        System.out.println(result.size());
        // 输出结果：1
    }

    // todo 查询 合格投资者认定申请的自增主键ID
    @Test
    public  void  test_getQualifyInverstorReqNextVal(){
        long  result = iCustomerService.getQualifyInverstorReqNextVal();
        System.out.println(result);
        //输出结果：8000002843
    }

     //todo 新增 合格投资者认定申请业务方法 涉及 大数据存储对象，合格投资者认定申请，投资者认证操作记录
    @Test
    public  void   test_insertQualifyInvestorReq(){
        //查询测试数据：1 :  select t.C_CATALOG,t.C_SUBKEYID from tblobstorage t where C_CATALOG = 'TQUALIFYINVESTOR_REQ'
        /*2:select l_serialno ,c_custno ,c_provetype ,f_provebalance ,
        c_status , c_creator ,d_createdate ,c_systemfrom,D_OPERATEDATE,C_OPERATOR from tqualifyinvestor_req*/
        List<BlobStorage> bslist = new ArrayList<BlobStorage>();
        BlobStorage blobStorage = new BlobStorage();
        blobStorage.setCatalog("TQUALIFYINVESTOR_REQ");
        blobStorage.setSubkeyId("7152");
        blobStorage.setCreator("000002");
        blobStorage.setStorageId(190188);
        bslist.add(blobStorage);

        QualifyInvestorReq qualifyInvestorReq = new QualifyInvestorReq();
        qualifyInvestorReq.setProveBalance(101010101);
        qualifyInvestorReq.setProveType("1");
        qualifyInvestorReq.setCustomerId("000007210750");
        qualifyInvestorReq.setSerialNo(iCustomerService.getQualifyInverstorReqNextVal());
        qualifyInvestorReq.setStatus("1");
        qualifyInvestorReq.setCreator("000000");
        qualifyInvestorReq.setCreateDate(new Date());
        qualifyInvestorReq.setOperator("000000");
        qualifyInvestorReq.setOperateDate(new Date());
        qualifyInvestorReq.setOfferVerifyCompany("新增测试公司");
        qualifyInvestorReq.setSubmitter("999999");
        QualifyInvestorChange qic = new QualifyInvestorChange();
        long id = iCustomerService.getQualifyInverstorReqNextVal();
        qic.setId(id);
        qic.setSerialNo(qualifyInvestorReq.getSerialNo());
        //storageId
        qic.setProveType(qualifyInvestorReq.getProveType());
        qic.setProveValidate(qualifyInvestorReq.getProveValidate());
        qic.setStatus(qualifyInvestorReq.getStatus());
        qic.setAuditOption("");
        qic.setOperator("000000");
        qic.setMemo("测试用例新增");

        iCustomerService.insertQualifyInvestorReq(bslist,qualifyInvestorReq,qic);
        //结果：数据库显示新增成功。
     /*   select l_serialno ,c_custno ,c_provetype ,f_provebalance ,
        c_status , c_creator ,d_createdate ,c_systemfrom,D_OPERATEDATE,C_OPERATOR from tqualifyinvestor_req order by  D_CREATEDATE desc

        select t.C_CATALOG,t.C_SUBKEYID,t.C_CREATOR,t.L_STORAGEID from tblobstorage t where C_CATALOG = 'TQUALIFYINVESTOR_REQ'

        select * from tqualifyinvestorchange ORDER BY D_OPERATEDATE DESC*/
    }

    // todo 修改合格投资者认定申请业务方法
    @Test
    public  void test_updateQualifyInvestorReq(){

        List<BlobStorage> bslist = new ArrayList<BlobStorage>();
        BlobStorage blobStorage = new BlobStorage();
        blobStorage.setCatalog("TQUALIFYINVESTOR_REQ");
        blobStorage.setSubkeyId("7152");
        blobStorage.setCreator("000000");
        blobStorage.setStorageId(190179);
        bslist.add(blobStorage);

        QualifyInvestorReq qualifyInvestorReq = new QualifyInvestorReq();
        qualifyInvestorReq.setProveBalance(1100110011);
        qualifyInvestorReq.setProveType("1");
        qualifyInvestorReq.setCustomerId("000007210750");
        qualifyInvestorReq.setSerialNo(14335L);
        qualifyInvestorReq.setStatus("1");
        qualifyInvestorReq.setCreator("000002");
        qualifyInvestorReq.setCreateDate(new Date());
        qualifyInvestorReq.setOperator("000002");
        qualifyInvestorReq.setOperateDate(new Date());
        qualifyInvestorReq.setSubmitter("100100");
        qualifyInvestorReq.setOfferVerifyCompany("测试公司1");
        QualifyInvestorChange qic = new QualifyInvestorChange();
        long id = iCustomerService.getQualifyInverstorReqNextVal();
        qic.setId(id);
        qic.setSerialNo(qualifyInvestorReq.getSerialNo());
        //storageId
        qic.setProveType(qualifyInvestorReq.getProveType());
        qic.setProveValidate(qualifyInvestorReq.getProveValidate());
        qic.setStatus(qualifyInvestorReq.getStatus());
        qic.setAuditOption("");
        qic.setOperator("000002");
        qic.setMemo("测试用例修改");
        iCustomerService.updateQualifyInvestorReq(bslist,qualifyInvestorReq,qic);
        // 结果：成功

       /* select l_serialno ,c_custno ,c_provetype ,f_provebalance ,
        c_status , c_creator ,d_createdate ,c_systemfrom,D_OPERATEDATE,C_OPERATOR from tqualifyinvestor_req order by  D_CREATEDATE  desc

        select t.C_CATALOG,t.C_SUBKEYID,t.C_CREATOR,t.L_STORAGEID from tblobstorage t where C_CATALOG = 'TQUALIFYINVESTOR_REQ'

        select * from tqualifyinvestorchange ORDER BY D_OPERATEDATE DESC */
    }

    // todo 根据理顾编号查询待办流程列表
    @Test
    public  void  test_searchWaitFlowList(){
        //查询：select t.C_CREATOR from TQUALIFYINVESTOR_REQ t where C_STATUS in ('0','3')
        Map<String, Object>  result  =  iCustomerService.searchWaitFlowList("H011449",1,10);
        System.out.println(result.size());
        //输出结果：total 5 list 的size 5
    }

    // todo 根据客户手机号查询是否存在此客户
    @Test
    public  void  test_getCustnoByMobileno(){
        //查询：select t.c_mobileno  from vcustomerinfo t  where  t.C_MOBILENO is not null;
        String  result = iCustomerService.getCustnoByMobileno("015021692494");
        System.out.println(result);
        //输出结果：000007180642
    }

    // todo  获取客户投资者类型列表
    @Test
      public  void  test_getCustomerInvestorTypeList(){
        // 查询：SELECT t.c_custno,t.C_CREATOR FROM tinvestor_req t, VCUSTOMERINFO v WHERE  v.C_CUSTNO=t.C_CUSTNO
        Map<String, Object> result = iCustomerService.getCustomerInvestorTypeList("000007173210","H000138",1,10);
        System.out.println(result.size());
        // 输出结果：total 2 list size 2
    }

    // todo 根据申请编号查询合格投资者申请详情
    @Test
    public  void  test_getQualifyInvestorReqInfo(){
       // 查询：SELECT a.L_SERIALNO from TQUALIFYINVESTOR_REQ a LEFT JOIN VCUSTOMERINFO b   ON a.C_CUSTNO = b.C_CUSTNO LEFT JOIN TBLOBSTORAGE c ON a.L_STORAGEID = c.L_STORAGEID
        QualifyInvestorReqInfoVO  result  =  iCustomerService.getQualifyInvestorReqInfo(14335L);
          System.out.println(result.getCustomerName());
          // 输出结果：艾莱斯资产管理顾问（深圳）有限公司
    }

    // todo 根据申请编号查询合格投资者附件信息
    @Test
    public  void test_getblobStorageList(){
        // 查询：SELECT c_subkeyid FROM TBLOBSTORAGE WHERE c_catalog='TQUALIFYINVESTOR_REQ'
        List<BlobStorageVO> result = iCustomerService.getblobStorageList(100021L);
        System.out.println(result.size());
        //输出结果：1
    }

    // todo 获取多金客户表ID列表
    @Test
    public void test_getCustNo1s(){
        // 查询：select c_custno from tcustomerinfo order by c_custno
        List<String> result = iCustomerService.getCustNo1s("000007007262",10);
        System.out.println(result.size());
        // 输出结果：10
    }

    // todo 获取多金客户表ID列表
    @Test
    public  void  test_getCustNo1sb(){
        // 查询：select c_custno from tcustomerinfo order by c_custno
        List<String>  result  =  iCustomerService.getCustNo1sb(000007007262L,000007173123L);
        System.out.println(result.size());
        //输出结果: 0  方法出错 以0 开头 long值改变 1838770(Long), 1898067(Long)
    }

    // todo 获取多金潜客表ID列表;
    @Test
    public  void   test_getCustNo2s(){
        // 查询：select c_custno  from tunregister  order by c_custno
        List<String> result = iCustomerService.getCustNo2s("200007147318",10);
        System.out.println(result.size());
        //输出结果：10
    }

     // todo 获取多金潜客表ID列表
    @Test
    public void  test_getCustNo2sb(){
        // 查询：select c_custno  from tunregister  order by c_custno
        List<String> result = iCustomerService.getCustNo2sb(200007147318L,200007147326L);
        System.out.println(result.size());
        //输出结果：8
    }

    // todo  根据多金客户ID查找对应的潜客ID
    @Test
    public  void  test_getCustNo2ByCustNo1(){
        //查询:SELECT C_FACTCUSTNO FROM TUNREGISTER where C_FACTCUSTNO is not null ORDER BY C_MEMO DESC
        String result  = iCustomerService.getCustNo2ByCustNo1("000007347624");
        System.out.println(result);
        // 输出结果：200007373813
    }

    // todo 根据多金客户ID获取客户状态
     @Test
    public void  test_getCustStatusByCustNo(){
         //查询：SELECT C_CUSTNO FROM VCUSTOMERINFO
         String result = iCustomerService.getCustStatusByCustNo("000007007262");
         System.out.println(result);
         //结果：Y
  }

  // todo 根据多金客户ID查找对应的潜客ID列表
    @Test
    public  void  test_getCustNo2sByCustNo1(){
        //查询：SELECT C_FACTCUSTNO FROM TUNREGISTER  WHERE C_FACTCUSTNO is not null
        List<String> result = iCustomerService.getCustNo2sByCustNo1("000007239710");
        System.out.println(result.size());
        //输出结果：2
    }
  // todo 根据多金潜客ID查找对应的客户ID
    @Test
    public void test_getCustNo1ByCustNo2(){
        //查询：SELECT C_CUSTNO FROM TUNREGISTER
        String result = iCustomerService.getCustNo1ByCustNo2("200007224501");
        System.out.println(result);
        // 输出结果：000007225477
    }

    // todo 根据客户编号获取客户手机号
    @Test
    public  void  test_getMobileByCustNo(){
        // 查询：select v.c_custno from vcustomerinfo v where v.C_MOBILENO_INTERNET is not null ;
        String  result =  iCustomerService.getMobileByCustNo("000007007262");
        System.out.println(result);
        // 输出结果：13982182079
   }

    // todo 根据合同编号获取客户编号
    @Test
    public  void  test_getPubProfessionByOrderCode(){
        // 查询：
        /* SELECT t.C_RESERVENO FROM vcustomerinfo v INNER JOIN treserve t ON t.C_CUSTNO = v.C_CUSTNO
        where v.c_pubprofessionflag = '1'  */
        Integer result  = iCustomerService.getPubProfessionByOrderCode("201711238000002802");
        System.out.println(result);
        //输出结果：1
    }

    // todo  多金个人用户注册
    @Test
    public  void  test_registerCustomer()throws Exception{
        //查询数据：
        RegisterCustomer registerCustomer = new RegisterCustomer();
        registerCustomer.setCustType(1);
        registerCustomer.setClientName("测试用例1");
        registerCustomer.setShortName("测试用例1");
        registerCustomer.setPassword("dsada");
        registerCustomer.setPhoneTel("12355");
        registerCustomer.setMobileTel("11111");
        String result = iCustomerService.registerCustomer(registerCustomer);
        System.out.println(result);
        //输出结果：200007380608
    }
    // todo  注册用户信息查询
    @Test
    public void  test_dcreguserquery() throws Exception{

        // 查询：select C_MOBILENO_INTERNET,C_CUSTTYPE from TUNREGISTER where C_MOBILENO_INTERNET is not null
        String result  =  iCustomerService.dcreguserquery("13601094934",1);
        System.out.println(result);
        // 输出结果：200007224611
    }

    // todo 根据客户名称或注册手机号或多金编号查询客户信息
    @Test
    public void  test_getLowerCustomerList(){
        //查询：
      /*  SELECT am.C_CUSTNO,cm.C_BROKERACCOUNT
        FROM TFUNDBROKERRELATION am,
                TBROKERMANAGE       bm,
                TBROKERMANAGE       cm
        WHERE bm.C_NODECODE like cm.C_NODECODE || '%'
        and cm.c_managerflag in ('1', '2', '3')
        and am.C_BROKERACCOUNT = bm.c_brokeraccount
        union
        select tf.c_custno,tf.c_brokeraccount
        from tfundbrokerrelation tf */
        Map<String,Object> result = iCustomerService.getLowerCustomerList("","","","H000069",1,10);
        System.out.println(result.size());
        //输出结果：total 18255  list size 10
    }

    // todo 根据产品编号获取客户信息
    @Test
    public  void  test_getCustomerByFundcode(){
        // 查询：
       /* select t.c_fundcode
        from  TTRUSTCONTRACTDETAILS t
        left join vcustomerinfo c on t.c_custno = c.c_custno  */
        List<CustomerFundInfoVo> result =  iCustomerService.getCustomerByFundcode("HT0686");
        System.out.println(result.size());
        //输出结果：68
    }

    // todo 根据认证手机号和客户类型返回符合条件的潜客表和成交表的客户编号的集合
    @Test
    public  void  test_getCustNoByMobileInternet(){
        List<String> resultList = iCustomerService.getCustNoByMobileInternet("13601094934","1");
        System.out.println(resultList.size());
    }
    // todo 根据客户编号和银行卡号  查询符合条件的数据总条数
    @Test
    public  void  test_countOfHadBindBankCard(){
        // select C_CUSTNO,C_BANKACCO  from TACCOBANK
        Long  result = iCustomerService.countOfHadBindBankCard("200007258737","6217001210023351081");
        System.out.println(result);
    }

    // todo 根据理顾编号，客户编号，是否专属  返回符合条件的数据总数量
    @Test
    public  void  test_countOfRelation(){
        //select C_CUSTNO,C_BROKERACCOUNT from TFUNDBROKERRELATION;
        Long  result = iCustomerService.countOfRelation("200007291708","6011","0");
        System.out.println(result);
    }
    // todo 根据四要素和实名认证状态查询符合条件的客户的客户编号集合。
   @Test
    public  void  test_getCustNoByFourParamAndIsRealName(){
        //select t.C_IDENTITYNO,t.C_IDENTITYTYPE,t.C_CUSTNAME,t.C_CUSTTYPE,t.C_IDNOCHECKFLAG from TUNREGISTER t
        List  result = iCustomerService.getCustNoByFourParamAndIsRealName("0","42010619740328484X","柯岚","1","1");
        System.out.println(result.size());
    }
    @Test
    public void test_updateCustomerRialPerfactByCustNo(){
        //查询sql  select * from TUNREGISTER t where t.C_CUSTNAME like '%测试接口刘鑫宇%'    000007347728 夏浩给的测试数据
        Integer result = iCustomerService.updateCustomerRialPerfactByCustNo("000007347728","H018725","1");
        System.out.println(result);
    }

    // todo 通过离职理顾集合，是否专属查询获取客户资产列表
    @Test
    public  void  testgetCustomerAssetsByQuitEmpsList(){
        // 查询测试数据：
        /* select t.f_realshares,t.d_lastmodify, j.c_name ,t.c_custno
        from (select DISTINCT  c_custno,C_FUNDCODE from  treserve  where c_brokeraccount='H014551' and c_custno='000007323225' )c,
          tstaticshares t
          left  join tfundinfo s on  s.c_fundcode=t.c_fundcode
          left join tsaleprofundrelation tf on s.c_fundcode=tf.c_fundcode
          left join  tsaleproject j on  tf.l_proserialno=j.l_proserialno
        where  j.c_deptnodecode like '00010001%' and  t.c_custno=c.c_custno and t.c_fundcode = c.c_fundcode
               and f_realshares>0
        order by t.d_lastmodify desc*/
        List<String> list = new ArrayList<String>();
        list.add("H014551");
        List<CustomerAssetsVO> result = iCustomerService.getCustomerAssetsByQuitEmpsList("000007323225","H014551",false,list,true,1,10);
        System.out.println(result.size());
        // 输出结果：3
    }
    // todo 获取客户合同信息
    @Test
    public  void  testgetCustomerContractList(){
        // 查询测试数据：  取 s.c_custno
       /* select  s.l_serialno, s.c_custno,s.c_bankname, s.c_bankacco,s.f_contractsignbalance ,j.c_name,
                f.d_setupdate,f.d_contractenddate,
                decode(f.c_fundstatus, '0', '发行',  '6', '已结束', '9', '已结束', '存续') c_fundstatus
        from ttrustcontractdetails s left join  tfundinfo f on s.c_fundcode=f.c_fundcode
        left join tsaleprofundrelation tf on f.c_fundcode=tf.c_fundcode
        left join  tsaleproject j on  tf.l_proserialno=j.l_proserialno
        where  j.c_deptnodecode like '00010001%'
        order by c_fundstatus, s.d_contractsigndate  desc*/
        List<CustomerContractInfoVO> result = iCustomerService.getCustomerContractList("000007306071",null,true,1,10);
        System.out.println(result.size());
        // 输出结果：2
    }
    // todo  获取客户投资者类型列表
    @Test
    public  void  testgetCustomerInvestorTypeList(){
        // 查询：SELECT t.c_custno,t.C_CREATOR FROM tinvestor_req t, VCUSTOMERINFO v WHERE  v.C_CUSTNO=t.C_CUSTNO
        Map<String, Object> result = iCustomerService.getCustomerInvestorTypeList("000007173210","H000138",true,1,10);
        System.out.println(result.size());
        // 输出结果：total 2 list size 2
    }
    // todo  获取客户预约列表
    @Test
    public  void  testgetAppointmentList(){
        // 查询测试数据：  取 s.c_custno,s.c_status
        /* select  distinct s.c_reserveno, j.c_name c_fundname, v.c_custname, s.c_custno,s.c_status , s.f_balance ,
                s.d_reservedate
        from treserve s  left join vcustomerinfo v on v.c_custno=s.c_custno
        left join  tsaleproject j on  s.l_proserialno=j.l_proserialno
        order by s.d_reservedate desc*/
        List<CustomerAppointmentVO> result = iCustomerService.getAppointmentList(null,"200007225766","1",null,true,1,10);
        System.out.println(result.size());
        //输出结果：10
    }
    // 统一客户1.4.7 需求，根据时间和客户来源查询潜客表客户列表
    @Test
    public void test_getCallCenterCustomerMsg()throws Exception{
        List list = new ArrayList();
        list.add("a");
        list.add("hf");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map map = iCustomerService.getCallCenterCustomerMsg(sdf.parse("2016-05-30 13:17:15"),list);
        System.out.println(map.size());
    }
 // 统一客户1.4.7 需求，根据多金客户编号 查询对应理顾关系信息
    @Test
    public void test_getCustomerRelationMsg(){
        Map map = iCustomerService.getCustomerRelationMsg("000007211576");
        System.out.println(map.size());
    }
    /* 通过客户编号查询潜客转成交客户信息，参数 起始成交客户编号，页码，数量 */
    @Test
    public void test_registerBeChangedByCustNo(){
        //查询sql     select  C_FACTCUSTNO,C_CUSTNO,C_STATUS  from TUNREGISTER where C_FACTCUSTNO > '000007007262' order by C_FACTCUSTNO,C_CUSTNO ASC
        Map<String,Object> result = iCustomerService.getRegisterBeChangedByCustNo("000007007262",1,20);
        result.get("status");
        result.get("list");
        System.out.println(result);
    }

    // 查询400回访注册客户信息
    @Test
    public void test_searchCallCenterMsg(){
       Map<String,Object> map =  iCustomerService.searchCallCenterMsg(1,20);
        System.out.println(map.size());
    }
    //根据银行账号和客户编号查询是否存在银行卡信息
    @Test
    public void  test_IsBankByCustNoAndBankAccount(){
        Integer  sum=iCustomerService.IsBankByCustNoAndBankAccount("6217001210039956014","200007272386");
        System.out.println(sum);
    }
 }

