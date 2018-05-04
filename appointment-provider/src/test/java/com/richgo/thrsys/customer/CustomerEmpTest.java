package com.richgo.thrsys.customer;


import com.common.util.DateUtil;
import com.richgo.thrsys.entity.customer.CustomerEmp;
import com.richgo.thrsys.entity.customer.CustomerEmpInfoVO;
import com.richgo.thrsys.entity.customer.CustomerEmpVO;
import com.richgo.thrsys.service.customer.CustomerEmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by zhouxj on 2017/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class CustomerEmpTest {
    @Autowired
    private CustomerEmpService customerEmpService;
    /**
     *
     * @throws Exception
     */
    @Test
    public void testQueryAptInfo() throws Exception {
        CustomerEmp  customer=new CustomerEmp();
        customer.setStartRow(Long.parseLong("1"));
        customer.setEndRow(Long.parseLong("100"));
        List<CustomerEmp>  list=customerEmpService.getCustomerEmpList(customer);
        for (CustomerEmp  custInfo:list) {
            System.out.println(custInfo.getCustomerName());
            System.out.println(custInfo.getEmpNo());
            System.out.println(custInfo.getCustomerId());
        }
        System.out.println("返回条数"+list.size());
    }

    /**
     * 根据修改时间获取新修改和增加的理顾客户对照关系
     * @throws Exception
     */
    @Test
    public void testgetCustomerEmpListByModifyDate() throws Exception {
        Date date= DateUtil.toDateYmd("20160722");
        CustomerEmp  customer=new CustomerEmp();
        //customer.setLastModifyDate(date);
        List<CustomerEmp>  list=customerEmpService.getCustomerEmpListByModifyDate(customer);
        System.out.println("返回条数"+list.size());
        for (CustomerEmp  custInfo:list) {
            System.out.println(custInfo.getCustomerName());
            System.out.println(custInfo.getEmpNo());
            System.out.println(custInfo.getCustomerId());
        }

    }

    /**
     * 获取所有员工客户对照数据
     * @throws Exception
     */
    @Test
    public void testCustomerEmpAllList() throws Exception {
        List<CustomerEmp>  list=customerEmpService.getCustomerEmpAllList();
        System.out.println("返回条数"+list.size());

    }
    @Test
    public  void  testgetCustomerEmpCount()  throws Exception{
        CustomerEmp  cus=new CustomerEmp();
        Long  count=customerEmpService.getCustomerEmpCount(cus);
        System.out.println(count);
    }
    @Test
    public  void  testgetAllCustomerEmps()  throws  Exception{
        Map<String,Object>  map=customerEmpService.getAllCustomerEmps(1,10);
        System.out.println(map.get("list"));

    }
    @Test
    public  void  testgetEmpNoByCustNo()  throws Exception{
        String custNo=customerEmpService.getEmpNoByCustNo("200007380138");
        System.out.println(custNo);
    }
    @Test
    public  void testgetAllEmpNoByCustNo()  throws Exception{
        List<String>  list=customerEmpService.getAllEmpNoByCustNo("000007173116");
        System.out.println(list.size());
    }
    @Test
    public  void testisAllEmpDimissory()  throws Exception{
        List<String> list=new ArrayList<>();
        list.add("H000001");
        list.add("H000002");
        list.add("H000003");
        list.add("H000004");
        list.add("H000005");
        list.add("H000006");
        boolean   flag=customerEmpService.isAllEmpDimissory(list);
        System.out.println(flag);
    }
    @Test
    public void testgetCustomerEmps() throws Exception{
        Map<String, Object>  map=customerEmpService.getCustomerEmps("000007201511",1,10);
        List<CustomerEmpVO> list=(List)map.get("list");
        for(CustomerEmpVO cus:list){
          System.out.println(cus.getCustNo());
          System.out.println( cus.getEmpName());
        }
        System.out.println(map.get("total"));

    }
    @Test
    public  void testgetCustomerEmpInfoVO()  throws  Exception{
        Map<String, Object>  map=customerEmpService.getCustomerEmpInfoVO("",null,"",null,1,10);
        System.out.println(map.get("total"));
    }

    @Test
    public  void testgetAllCustomerEmpInfoVO()  throws  Exception{
        List<CustomerEmpInfoVO>  list=customerEmpService.getAllCustomerEmpInfoVO("",null,"",null);
        for(CustomerEmpInfoVO  custom:list){
            System.out.println(custom.getCustStatus()+","+custom.getEmpNo()+","+custom.getEmpName()+","+custom.getCustNo());
        }
    }
    @Test
    public  void  testsearchCustomerEmpInfoVO()  throws  Exception{
        Map<String, Object>  map=customerEmpService.searchCustomerEmpInfoVO(null,null,1,10);
        System.out.println(map.get("total"));
    }

    @Test
    public  void  testinsertCustomerBrokerRelation()  throws Exception{
        List<String>  list=new ArrayList<String>();
        list.add("000007175084");
        list.add("000007175095");
        Integer  count=customerEmpService.insertCustomerBrokerRelation("H005364",list);
        System.out.println(count);
    }
    @Test
    public  void  testupdateCustomerInfoByCustNo()  throws Exception{
       // Map<String,Object> map= customerEmpService.updateCustomerInfoByCustNo("200007224605","自测修改00","330501195607290642","1");
        Map<String,Object> map= customerEmpService.updateCustomerInfoByCustNo("000007173612","自测修改00","330501195607290642","");
        System.out.println(map.size());
        System.out.println(map.get(0));

    }
}
