package com.richgo.thrsys.customer;


import com.richgo.common.CodeConts;
import com.richgo.thrsys.entity.customer.BankCardOperationVO;
import com.richgo.thrsys.entity.customer.CustomerInfoVO;
import com.richgo.thrsys.entity.message.Broker;
import com.richgo.thrsys.entity.message.QualifyInvestor;
import com.richgo.thrsys.entity.message.RealNameCustomer;
import com.richgo.thrsys.service.customer.CustomerMessageService;
import com.richgo.thrsys.service.customer.IBankCardOperationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


/**
 * Created by zhouxj on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class BankCardOperationServiceTest {
    @Autowired
    private IBankCardOperationService iBankCardOperationService;
    /**
     *客户实名认证信息抓取
     * @throws Exception
     */
    @Test
    public void testbindBankCard() throws Exception {
        BankCardOperationVO  cardVo=new BankCardOperationVO();
        cardVo.setCustomerId("000007173698");
        cardVo.setBankNo("007");
        cardVo.setStorageIds("5092");
        cardVo.setOperator("测试的0");
        Map<String, Object>  map=iBankCardOperationService.bindBankCard(cardVo);
        System.out.println(map.get(CodeConts.FAILURE));

    }

    @Test
    public  void   testmodifyBankCard()   throws  Exception{
        BankCardOperationVO  cardVo=new BankCardOperationVO();
        cardVo.setTradeacco("Z1008000002838");
        cardVo.setStorageIds("5092");
        cardVo.setBankNo("002");
        cardVo.setBankCardName("中国工商银行");
        cardVo.setOperator("H009999");
        cardVo.setSubbranch("江南分行");
        cardVo.setPhone("13411111111");
        cardVo.setBankAccount("6214852601232666");
        Map<String, Object>  map=iBankCardOperationService.modifyBankCard(cardVo);
        System.out.println(map.get("message")+","+map.get("status"));

    }
    @Test
    public  void  testcloseBankCard()   throws  Exception{
        //int  count=iBankCardOperationService.closeBankCard("39463474386");
        int  count=iBankCardOperationService.closeBankCard("Z1008000002838");
        System.out.println(count);

    }


}

