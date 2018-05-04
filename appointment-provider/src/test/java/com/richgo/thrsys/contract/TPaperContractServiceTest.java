package com.richgo.thrsys.contract;

import com.richgo.thrsys.service.contract.TPaperContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 纸质合同测试类
 * Created by zhouxj on 2018/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class TPaperContractServiceTest {
    @Autowired
    private TPaperContractService tPaperContractService;
    @Test
    public void test_getAllReserveMsgByStatus()throws Exception {
        List<String> list=new ArrayList<>();
       // list.add("4");
      //  list.add("1");
     //  Map<String,Object> map=tPaperContractService.getAllReserveMsgByStatus(list,1,10);
       // Map<String,Object> map=tPaperContractService.getAllReserveMsgByStatus(list,null,null);
        Map<String,Object> map=tPaperContractService.getAllReserveMsgByStatus(list,null,null);
        if(map.size()>2){
            System.out.println(map.get("list"));
            System.out.println(map.get("total"));
        }
        System.out.println(map.get("status"));
        System.out.println(map.get("message"));
    }
    @Test
    public void test_getReserveMsgCountByStatus() throws Exception{
        List<String> list=new ArrayList<>();
        list.add("4");
        list.add("1");
        Map<String,Object> map=tPaperContractService.getReserveMsgCountByStatus(list);
        if(map.size()>2){
            System.out.println(map.get("total"));
        }
        System.out.println(map.get("status"));
        System.out.println(map.get("message"));
    }
    @Test
    public void test_getAddReserveMsgByDate() throws Exception{
        List<String> list=new ArrayList<String>();
        list.add("4");
        list.add("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date updateDate= sdf.parse("2017/08/08 12:10:12");
        Date startDate= sdf.parse("2011/01/01 12:10:12");
      // Map<String,Object> map=tPaperContractService.getAddReserveMsgByDate(list,null,null);
        //  Map<String,Object> map=tPaperContractService.getAddReserveMsgByDate(list,updateDate,startDate);
        Map<String,Object> map=tPaperContractService.getAddReserveMsgByDate(list,new Date(),null);
        if(map.size()>2){
            System.out.println(map.get("list"));
        }
        System.out.println(map.get("status"));
        System.out.println(map.get("message"));
    }
    @Test
    public void test_getReserveMsgByReserveNo() throws Exception{
        //Map<String,Object> map=tPaperContractService.getReserveMsgByReserveNo("");
        Map<String,Object> map=tPaperContractService.getReserveMsgByReserveNo(null);
        //Map<String,Object> map=tPaperContractService.getReserveMsgByReserveNo("201511200000078871");
        if(map.size()>2){
            System.out.println(map.get("list"));
        }
        System.out.println(map.get("status"));
        System.out.println(map.get("message"));
    }
    @Test
    public void test_selectSomeProjectsByFileds() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date startDate= sdf.parse("2011/01/01 12:10:12");
      //  Map<String, Object>   map=tPaperContractService.selectSomeProjectsByFileds(null,null,"","","",null);
        Map<String, Object>   map=tPaperContractService.selectSomeProjectsByFileds(0,10,"","","",startDate);
        if(map.size()>2){
            System.out.println(map.get("list"));
        }
        System.out.println(map.get("status"));
        System.out.println(map.get("message"));
    }
}
