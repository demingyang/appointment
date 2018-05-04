package com.richgo;

import com.common.util.DateUtil;
import com.richgo.thrsys.entity.reserve.*;
import com.richgo.thrsys.entity.ThrsysMyReserve;
import com.richgo.thrsys.entity.customer.BlobStorage;
import com.richgo.thrsys.entity.customer.CustomerAppointmentVO;
import com.richgo.thrsys.entity.customer.VcustomerVO;
import com.richgo.thrsys.service.reserve.ThrsysReserveService;
import com.richgo.thrsys.service.reserve.ThrsysSaleProjectService;
import com.richgo.thrsys.service.reserve.ToolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhouxj on 2017/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class reserveTest {
    @Autowired
    private ThrsysReserveService   thrsysReserveService;
    @Autowired
    private ThrsysSaleProjectService thrsysSaleProjectService;
    @Autowired
    private ToolService toolService;
    //selectList
    @Test
    public void  testSelectList() throws Exception{
        List<CustomerAppointmentVO>  list=thrsysReserveService.selectList("H007423",1,10);
        for (CustomerAppointmentVO  cusVo: list) {
            cusVo.getCustomerName();
            cusVo.getReserveNo();
        }
    }
    //getAllReserve
    @Test()
    public void  testgetAllReserve()  throws Exception{
        List<ThrsysMyReserve>   list=thrsysReserveService.getAllReserve(1,10);
        System.out.println(list.size());
    }
    //getReserveFromReserveDate
    @Test()
    public  void   testgetReserveFromReserveDate()  throws  Exception{
        Date date= DateUtil.toDateYmd("20170921");
        List<ThrsysMyReserve>   list=thrsysReserveService.getReserveFromReserveDate(date);
        System.out.println(list.size());
    }
    //addReserve
    @Test()
    public  void  testaddReserve()  throws  Exception{
        Treserve   reserve =new Treserve();
        reserve.setReserveno("001605260000123913");
        reserve.setCustno("000007326443");
        reserve.setFundacco("Z10000025244");
        reserve.setBusinflag("01");
        reserve.setCreator("溜溜梅");
        reserve.setBalance(Long.parseLong("1000000"));
        reserve.setExpirationdate("20171121");
        reserve.setProductcode("0003WT");
        reserve.setProserialno("27052");
        reserve.setStatus("1");
        reserve.setBrokeraccount("H000225");
        reserve.setReservesource("1");
        reserve.setNameinbank("");
        reserve.setBankname("007");
        reserve.setBankacco("415900000010865");
        reserve.setBuytype("0");
        reserve.setRemitdate("20171122");
        reserve.setSalebrokeraccount("H011449");
        reserve.setDeptnodecode("0001000100110004");
        reserve.setProfitclass("0");
        reserve.setSubscriptionratio("0.0100");
        reserve.setSubbranchname("上海某路支行");
        Map<String, Object>  newMap=thrsysReserveService.addReserve(reserve,true);
        System.out.println(newMap.get(0));
    }
    //cancelReserve
    @Test()
    public  void  testcancelReserve()  throws Exception{
        Treserve   reserve =new Treserve();
        reserve.setReserveno("001605260000123913");
        reserve.setCustno("000007326443");
        reserve.setFundacco("Z10000025244");
        reserve.setBusinflag("01");
        reserve.setCreator("溜溜梅");
        reserve.setBalance(Long.parseLong("1000000"));
        reserve.setExpirationdate("20171121");
        reserve.setProductcode("0003WT");
        reserve.setProserialno("27052");
        reserve.setStatus("1");
        reserve.setBrokeraccount("H000225");
        reserve.setReservesource("1");
        reserve.setNameinbank("");
        reserve.setBankname("007");
        reserve.setBankacco("415900000010865");
        reserve.setBuytype("0");
        reserve.setRemitdate("20171122");
        reserve.setSalebrokeraccount("H011449");
        reserve.setDeptnodecode("0001000100110004");
        reserve.setProfitclass("0");
        reserve.setSubscriptionratio("0.0100");
        reserve.setSubbranchname("上海某路支行");
        Map<String, Object>  testMap=thrsysReserveService.cancelReserve(reserve,"自测删除预约接口","H000225",true);
        System.out.println(testMap.get(0));
    }
    //getReservenoByCode
    @Test()
    public  void  testgetReservenoByCode()  throws  Exception{
       String   reserveNo=thrsysReserveService.getReservenoByCode(null,null);
     //  String   reserveNo=thrsysReserveService.getReservenoByCode(null,"1");
     //   String   reserveNo=thrsysReserveService.getReservenoByCode("001001",null);
     //   String   reserveNo=thrsysReserveService.getReservenoByCode("001001","1");
        System.out.println(reserveNo.length());
        System.out.println(reserveNo);
    }
    //getReserveByReserveno
    @Test
    public  void  textgetReserveByReserveno()  throws  Exception{
       // Treserve  reserveInfo=thrsysReserveService.getReserveByReserveno("001605260000123903");
        Treserve  reserveInfo=thrsysReserveService.getReserveByReserveno("201512070000080952");
        System.out.println(reserveInfo);
    }
    //confirmReserve
    @Test()
    public  void  textconfirmReserve()   throws  Exception{
        Treserve   reserve =new Treserve();
        reserve.setReserveno("201601060000086442");
        reserve.setCustno("000007326443");
        reserve.setFundacco("Z10000025244");
        reserve.setBusinflag("01");
        reserve.setCreator("溜溜梅");
        reserve.setBalance(Long.parseLong("1000000"));
        reserve.setExpirationdate("20171121");
        reserve.setProductcode("0003WT");
        reserve.setProserialno("27052");
        reserve.setStatus("");
        reserve.setBrokeraccount("H000223");
        reserve.setReservesource("1");
        reserve.setNameinbank("");
        reserve.setBankname("007");
        reserve.setBankacco("415900000010865");
        reserve.setBuytype("0");
        reserve.setRemitdate("20171122");
        reserve.setSalebrokeraccount("H011449");
        reserve.setDeptnodecode("0001000100110004");
        reserve.setProfitclass("0");
        reserve.setSubscriptionratio("0.0100");
        reserve.setSubbranchname("上海某路支行");
        Map<String, Object>   map=thrsysReserveService.confirmReserve(reserve,"H000223","预约确认成功",true);
        System.out.println(map.get(0));
    }
    //执行自动排号逻辑
    @Test()
    public  void  testautoReserveArrange()  throws  Exception{
     // String  autoNo= thrsysReserveService.autoReserveArrange("20408","");
        String  autoNo= thrsysReserveService.autoReserveArrange("25963","201701060000204796");
        System.out.println(autoNo);
    }
    //根据项目编号查询项目
    @Test()
    public  void  textgetProjectByCode() throws  Exception{
        TsaleprojectVO  saleProject=thrsysSaleProjectService.getProjectByCode("6002");
        System.out.println(saleProject);
    }
    /**
     * 测试根据预约时间查询日期前一日的预约表记录
     * @throws Exception
     */
 /*   @Test
    public void testReserve() throws Exception {
        Date date= DateUtil.toDateYmd("20161115");
        List<TfundAddReserveByDateVO> list= thrsysReserveService.getReserveList(date);
    }*/

    /**
     * 根据成立日期查询前一日的项目表记录
     * @throws Exception
     */
  /*   @Test
   public void testsaleproject() throws Exception {
        Date date= DateUtil.toDateYmd("20151218");
        List<TfundSaleprojectByDateVO> list= thrsysSaleProjectService.getSaleprojectList(date);
       System.out.println(list.size());
    }*/
  //查询不处于待发行状态的预约项目
     @Test
   public void  testSaleProjectList()  throws Exception {
          Map  maplist=thrsysSaleProjectService.fundSaleProjectMapByProjectNo("", null, 1, 10);
         // Map<String,Object>  maplist=thrsysSaleProjectService.fundSaleProjectMapByProjectNo("",Long.parseLong("6502"), 1, 10);
     //Map<String,Object>  maplist=thrsysSaleProjectService.fundSaleProjectMapByProjectNo("财富", null, 2, 10);
      System.out.println(maplist.size());
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println(maplist.get("totalPage"));
      System.out.println(maplist.get("totalPageNo"));
    }
    //根据项目编号查询预约排队成功的用户
   @Test
    public  void  testReserveList() throws  Exception{
        Map<String,Object>   mapList=thrsysReserveService.getReserveMap("6502,5902,5903",2,10);
        System.out.println(mapList.size());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(mapList.get("totalPageNo"));
        System.out.println(mapList.get("totalPageSize"));
    }
    @Test
   public  void  testSalproAndFundRelationList() throws  Exception{
       List<TsaleproAndFundRelationVO>  listProAndCode=thrsysSaleProjectService.getProjectNameAndFundCode("",20);
        //List<TsaleproAndFundRelationVO>  listProAndCode=thrsysSaleProjectService.getProjectNameAndFundCode("恒天",null);

        System.out.println("########################");
       for (TsaleproAndFundRelationVO   saleCode:listProAndCode) {
           System.out.println(saleCode.getFundCode());
           System.out.println(saleCode.getProjectName());
       }
       System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$");

   }
  @Test()
    public void testGetSystemParameter()  throws  Exception{
      String  para=toolService.getSystemParameter("FUNDCRM","System","EnableSaleProjectChange");
      System.out.println(para);
  }
  @Test()
    public  void  testcountOpenDay()  throws  Exception{
      int day=toolService.countOpenDay();
      System.out.println(day);
  }
  @Test
    public  void testgetSysdateToChar()  throws  Exception{
      String sysDay=toolService.getSysdateToChar("yyyy-MM-dd");
      System.out.println(sysDay);
  }
  @Test
    public void  testgetVcustomerByCode()  throws Exception{
      VcustomerVO  customer=toolService.getVcustomerByCode("000007173752");
      System.out.println(customer);
  }
  @Test()
    public  void testgetFundaccoByCustno()  throws  Exception{
      String  fundCode=toolService.getFundaccoByCustno("000007173752");
      System.out.println(fundCode);
  }
  @Test()
    public  void  testgetFundcodeStatusByCode()  throws  Exception{
    //  String codeStatus=toolService.getFundcodeStatusByCode("00048F");
      String codeStatus=toolService.getFundcodeStatusByCode("00027E");
      System.out.println(codeStatus);
  }
  @Test()
    public  void testcountTstaticsharesByCode() throws  Exception{
      Integer  count=toolService.countTstaticsharesByCode("000007183302","0000E2");
      System.out.println(count);
  }

  @Test()
    public  void testgetMaxAvailableriskByCode()  throws  Exception{
      String riskLevel=toolService.getMaxAvailableriskByCode("HT1247");
      System.out.println(riskLevel);
  }
  @Test()
    public  void testgetBlobStorage()  throws  Exception{
      BlobStorage  blo=toolService.getBlobStorage(Long.parseLong("2794"));
      System.out.println(blo);
  }
  @Test()
    public void testinsertBlobStorage()   throws  Exception{
      BlobStorage  bs=new BlobStorage();
      bs.setStorageId(Long.parseLong("8000003606"));
      bs.setCatalog("TFUNDINFO");
      bs.setFilename("【背景】大成岁兰千里并购1号（定稿）.pdf ");
      bs.setCreator("H009785");
      bs.setCreateDate(DateUtil.toDateYmd("20161115"));
      bs.setAccessory("00".getBytes());
      bs.setFilesize(Long.parseLong("1096931"));
      bs.setSecurityLevel("0");
      bs.setSubkeyId("8888");
      //bs.setFilePath("");
     // bs.setFilePathType("");
     toolService.insertBlobStorage(bs);
     System.out.println("########");

  }
  @Test()
    public void testupdateBlobStorage()  throws Exception{
      BlobStorage  bs=new BlobStorage();
      bs.setStorageId(Long.parseLong("8000003606"));
      bs.setCatalog("TACCOBANK");
      bs.setSubkeyId("Z1008000003430");
      bs.setCreator("0000");
      toolService.updateBlobStorage(bs);
  }
    @Test()
    public void testdeleteBlobStorage()  throws Exception{
        toolService.deleteBlobStorage(Long.parseLong("8000003606"));
        System.out.println("########");
    }
    //该功能无法实现
    @Test()
    public void testdeleteBlobStorageBySerialno()  throws Exception{
        toolService.deleteBlobStorageBySerialno(8888);
        System.out.println("########");
    }

    @Test()
    public  void  testgetBlobStorageNextVal()  throws  Exception{
      Long  val=toolService.getBlobStorageNextVal();
      System.out.println(val);
    }
     @Test()
    public void testgetSaleaccByAcc()   throws  Exception{
     String sala=toolService.getSaleaccByAcc("H006069");
     System.out.println(sala);
    }
    @Test()
    public  void  testgetHScreator()  throws Exception{
    String num=toolService.getHScreator();
    System.out.println(num);
    }
    @Test()
    public  void  testgetQualifiedInvestor()  throws  Exception{
      String  num=toolService.getQualifiedInvestor("000007202083",Long.parseLong("3100000"),"");
      // String  num=toolService.getQualifiedInvestor("000007202083",Long.parseLong("3100000"),"1");
        System.out.println(num);
    }
    @Test()
    public  void  testgetDeptnodecode()  throws  Exception{
      String  code=toolService.getDeptnodecode("H004454","");
      System.out.println();
    }
    @Test()
    public void  testjudgeOldCustomer()  throws Exception{
      String isOld=toolService.judgeOldCustomer("000007173754");
      System.out.println(isOld);
    }
    @Test()
    public  void  testgetEpirationdateByNo()   throws  Exception{
      String epNo=toolService.getEpirationdateByNo("6002");
      System.out.println(epNo);
    }
    @Test()
  public  void  testgetReserveBalanceByDate() throws  Exception{

     BigDecimal date=toolService.getReserveBalanceByDate("6750","20161215");
      System.out.println(date);
    }
  @Test()
    public  void  testcountStrategycustom()  throws  Exception{
      int  conCustomer=toolService.countStrategycustom();
      System.out.println(conCustomer);
  }
    @Test()
    public  void  testgetProjectList()  throws  Exception{
        Map<String,Object> map=thrsysSaleProjectService.getProjectList("恒天明泽", 20);
        if(map.size()>1){
            List<TfundProMsgByProjectNoVO> projectList=(List<TfundProMsgByProjectNoVO>)map.get("list");
            for (TfundProMsgByProjectNoVO  vo:projectList) {
                vo.getProjectId();
                vo.getProjectName();
                vo.getFundCode();
                vo.getFundName();
            }
        }
        System.out.println(map.get("status"));
        System.out.println(map.get("message"));
    }
    @Test()
    public  void testgetCustomerMsgByProjectNo() throws Exception{
         Map<String,Object> customerMap=thrsysSaleProjectService.getCustomerMsgByProjectNo("27676");
         if(customerMap.size()>1){
             List<TfundCustomerMsgByProjectNoVO>  customerList=(List<TfundCustomerMsgByProjectNoVO>)customerMap.get("list");
             for(TfundCustomerMsgByProjectNoVO  vo:customerList){
                 vo.getFundCode();
                 vo.getProjectCode();
                 vo.getProjectName();
                 vo.getCustNo();
                 vo.getCustName();
                 vo.getBrokerAccount();
             }
         }
        System.out.println(customerMap.get("status"));
        System.out.println(customerMap.get("message"));
    }
    @Test()
    public  void  testgetProTypeByReserveNo()  throws  Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        map=thrsysReserveService.getProTypeByReserveNo("201512150000082049");
        // map=thrsysReserveService.getProTypeByReserveNo(null);
      //  map=thrsysReserveService.getProTypeByReserveNo(" ");
        if(map.size()>2){
            System.out.println(map.get("list"));
        }
        System.out.println(map.get("status"));
        System.out.println(map.get("message"));
    }
}
