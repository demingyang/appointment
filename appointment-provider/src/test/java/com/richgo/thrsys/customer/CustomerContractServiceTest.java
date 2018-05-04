package com.richgo.thrsys.customer;


import com.common.util.DateUtil;
import com.richgo.thrsys.entity.customer.*;
import com.richgo.thrsys.entity.message.Broker;
import com.richgo.thrsys.entity.message.QualifyInvestor;
import com.richgo.thrsys.entity.message.RealNameCustomer;
import com.richgo.thrsys.service.customer.CustomerMessageService;
import com.richgo.thrsys.service.customer.ICustomerContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by zhouxj on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class CustomerContractServiceTest {
    @Autowired
    private ICustomerContractService iCustomerContractService;

    @Test
    public void testgetCustomerContractByCustNo() throws Exception {
        Map<String, Object>  map=iCustomerContractService.getCustomerContractByCustNo("000007343920",1,10);
        List<CustomerContractVO>  list=(List<CustomerContractVO>)map.get("list");
       /* for(CustomerContractVO co:list){
            System.out.println(co.getSerialno()+','+co.getFundcode()+","+co.getFundName());
        }*/
        System.out.println(map.get("total"));
        System.out.println(list.size());
    }
    @Test
    public  void  testgetCustomerContractByDateList()  throws  Exception{
        //2017-09-29 00:00:00
        Date date= DateUtil.toDateYmd("20171029");
        List<ContractOrderBO>  list=iCustomerContractService.getCustomerContractByDateList(date,1,10);
     /*   for(ContractOrderBO  bo:list){
            System.out.println(bo.getSerialNo());
            System.out.println(bo.getCustomerNo());
            System.out.println(bo.getFundCode());
        }*/
        System.out.println(list.size());
    }

    @Test
    public  void  testgetCustomerContractCountByDate()  throws  Exception{
        Date date= DateUtil.toDateYmd("20170929");
        Integer  count=iCustomerContractService.getCustomerContractCountByDate(date);
        System.out.println(count);
    }

    @Test
    public  void  testgetContractOrderBOBySerialNo()  throws Exception{
        CustomerContractVO   contract=iCustomerContractService.getContractOrderBOBySerialNo(Long.parseLong("9999043745"),"000007173479");
        System.out.println(contract);
    }

    @Test
    public  void  testgetCustomerContractByDay()  throws  Exception{
        List<CustomerContractVO>   list=iCustomerContractService.getCustomerContractByDay(DateUtil.toDateYmd("20171114"));
        for(CustomerContractVO  bo:list){
            System.out.println(bo.getCustno());
            System.out.println(bo.getSerialno());
            System.out.println(bo.getSignDate());
        }
    }

    @Test
    public  void  testgetCustomerFirstContract()  throws  Exception{
        CustomerContractVO  custVo=iCustomerContractService.getCustomerFirstContract("000007181676");
        System.out.println(custVo);
    }
    @Test
    public  void  testgetContractCustomerCount()  throws  Exception{
        Integer  con=iCustomerContractService.getContractCustomerCount(DateUtil.toDateYmd("20171114"));
        System.out.println(con);
    }

    @Test
    public  void  testgetContractCustomerList()  throws  Exception{
        List<CustomerContractVO>  list=iCustomerContractService.getContractCustomerList(DateUtil.toDateYmd("20171114"),1,10);
        System.out.println(list.size());
    }
    @Test
    public  void   testgetFixedCustomerPointsReleaseList()  throws  Exception{
        List<CustomerPointsReleaseBO>  list=iCustomerContractService.getFixedCustomerPointsReleaseList(DateUtil.toDateYmd("20151111"));
        for(CustomerPointsReleaseBO  bo:list){
            System.out.println(bo.getSerialNo());
        }
    }

    @Test
    public  void  testgetFloatingCustomerPointsReleaseList()  throws  Exception{
        List<CustomerPointsReleaseBO>  list=iCustomerContractService.getFloatingCustomerPointsReleaseList("000007183089",DateUtil.toDateYmd("20991231"));
        for(CustomerPointsReleaseBO  bo:list){
            System.out.println(bo.getSerialNo());
        }
    }
    @Test
    public  void   testgetDistributeList()  throws  Exception{
        Map<String,Object> result=iCustomerContractService.getDistributeList(DateUtil.toDateYmd("20121026"));
        result.get("list");
        result.get("status");
        result.get("msg");

    }

}

