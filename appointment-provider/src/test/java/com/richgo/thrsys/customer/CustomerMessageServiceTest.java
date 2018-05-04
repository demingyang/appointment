package com.richgo.thrsys.customer;



import com.richgo.thrsys.entity.customer.CustomerEmp;
import com.richgo.thrsys.entity.customer.CustomerInfoVO;
import com.richgo.thrsys.entity.message.Broker;
import com.richgo.thrsys.entity.message.QualifyInvestor;
import com.richgo.thrsys.entity.message.RealNameCustomer;
import com.richgo.thrsys.service.customer.CustomerMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;


/**
 * Created by zhouxj on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class CustomerMessageServiceTest {
    @Autowired
    private CustomerMessageService customerMessageService;
    /**
     *客户实名认证信息抓取
     * @throws Exception
     */
    @Test
    public void testgetRealNameAuthCustomersByModifyTime() throws Exception {
        List<RealNameCustomer>  list=customerMessageService.getRealNameAuthCustomersByModifyTime("20170918");
 /*      以后全量测试for循环耗费时间
    for(RealNameCustomer  custom:list){System.out.println(custom.getCustomerNo());
        System.out.println(custom.getMobile());
    }*/
        System.out.println(list.size());
    }
    @Test
    public  void  testgetQualifyInvestorAuditInfoByOperationTime()  throws  Exception{
        List<QualifyInvestor>  list=customerMessageService.getQualifyInvestorAuditInfoByOperationTime("20170918");
      /*  以后全量测试for循环耗费时间
      for(QualifyInvestor  custom:list){
            System.out.println(custom.getCustNo());
            System.out.println(custom.getSerialNo());
            System.out.println(custom.getStatus());
        }*/
        System.out.println(list.size());
    }
    @Test
    public  void  testgetCustomersByRegisterTime()  throws  Exception{
       List<CustomerInfoVO>   list=customerMessageService.getCustomersByRegisterTime("20170622");
    /*   for(CustomerInfoVO  custom:list){
            System.out.println(custom.getCustomerName());
           System.out.println(custom.getCuststatus());
            System.out.println(custom.getCustomerType());
       }*/
        System.out.println(list.size());

    }

    @Test
    public  void  testgetAdvisersByCustomerNo()  throws  Exception{
        List<Broker>  list=customerMessageService.getAdvisersByCustomerNo("000007173752");
     /*   for(Broker  bro:list){
            System.out.println(bro.getCustomerName());
        }*/
        System.out.println(list.size());
    }

}

