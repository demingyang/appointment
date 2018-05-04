package com.richgo.thrsys.contract;

import com.richgo.thrsys.entity.contract.TOrderInfo;
import com.richgo.thrsys.entity.contract.TReserveInfo;
import com.richgo.thrsys.service.contract.TElectronicContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouxj on 2018/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class TElectronicContractServicelTest {
        @Autowired
        private TElectronicContractService tElectronicContractService;
        //查询项目列表
        @Test
        public void testselectAllProject() throws Exception {
            Map<String, Object> map=tElectronicContractService.selectAllProject(1,10);
            System.out.println(map.get("total"));

        }
        //根据部分字段模糊查询项目
        @Test
        public void testSelectSomeProjectsByFileds() throws Exception {
            Map<String, Object>   map=tElectronicContractService.selectSomeProjectsByFileds(1,10,"","","");
            System.out.println(map.get("total"));
        }

        //根据项目名称以及其他条件去查询项目订单的相关信息
        @Test
        public void test1() throws Exception {
            // Map<String,Object> map =  tElectronicContractService.selectProjectOrderByName(1,10,"001605260000123902","恒天稳金13号投资基金（新认购）",null,null,null);
            Map<String,Object> map =  tElectronicContractService.selectProjectOrderByName(1,10,null,"恒天稳金13号投资基金（新认购）",null,"1","0");
            // Map<String,Object> map =  tElectronicContractService.selectProjectOrderByName(1,10,"0900","恒天稳金13号投资基金（新认购）",null,null,null);
            System.out.println(map.get("total"));
            System.out.println(map.get("orderCountByPaystatus"));
            System.out.println(map.get("orderCount"));
            System.out.println(map.get("tProjectOrderInfos"));
        }

        //根据预约单号查询该单号是否足额到账  201611150000185643   201611150000185596  201611150000185638
        @Test
        public  void  testselectPayStatusByReserveNO() throws  Exception{
            //Boolean  blo=tElectronicContractService.selectPayStatusByReserveNO(null);
            // Boolean  blo=tElectronicContractService.selectPayStatusByReserveNO("201611150000185643");
            Boolean  blo=tElectronicContractService.selectPayStatusByReserveNO("201611150000185638");
            System.out.println(blo);

        }
        //测试根据预约单号查询预约信息
        @Test
        public  void  test2() throws  Exception{
            TReserveInfo reserveinfo=tElectronicContractService.selectReserveByReserveNO("201603080000099967");
            System.out.println(reserveinfo.getBrokerMobile());


        }
        //查询全部的订单
        @Test
        public  void  test3() throws  Exception{
            Map<String, Object>  map=tElectronicContractService.selectAllOrder(1,10);
            System.out.println(map.get("list"));
            List<TOrderInfo> list=(List<TOrderInfo>) map.get("list");
            for (TOrderInfo  info:list) {
                //所属分公司C_NODEVALUE  理顾名称 C_BROKERNAME
                System.out.println(info.getBrokerName());
                System.out.println(info.getMajorcorp());


            }

        }

        //根据部分字段查询订单  001605260000123902
        @Test
        public  void  test4() throws  Exception{
            Map<String, Object>  map=tElectronicContractService.selectOrderByField(1,10,"","周八零","","","","","201711240000258458");
            //Map<String, Object>  map=tElectronicContractService.selectOrderByField(1,10,"","","","","","","001605260000123902");
            System.out.println(map.get("list"));
        }
        //查询状态为已撤销、已失效的订单
        @Test
        public  void  test5() throws  Exception{
            List<TOrderInfo>  list=tElectronicContractService.selectDisableOrder("27052");
            // List<TOrderInfo>  list=tElectronicContractService.selectDisableOrder("");
            //所属分公司C_NODEVALUE  理顾名称 C_BROKERNAME
            System.out.println("理顾名称"+list.get(0).getBrokerName()+"所属分公司"+list.get(0).getMajorcorp());
            System.out.println("#############################");
        }

}
