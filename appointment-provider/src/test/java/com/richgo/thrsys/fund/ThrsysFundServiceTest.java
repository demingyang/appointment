package com.richgo.thrsys.fund;

import com.richgo.thrsys.entity.ThrsysFund;
import com.richgo.thrsys.entity.customer.CustomerContractInfoVO;
import com.richgo.thrsys.service.fund.ThrsysFundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liuxinyu on 2017/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysFundServiceTest {
    @Autowired
    private ThrsysFundService thrsysFundService;
    //todo  根据时间段查询有哪些成立的基金
    @Test
    public void  test_getFoundedFunds() throws Exception{
        //参数为时间戳，无需查询
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart = sdf.parse("2016-05-05 16:00:00");
        Date dateEnd = sdf.parse("2017-08-05 16:00:00");
        List<ThrsysFund> resultList = thrsysFundService.getFoundedFunds(dateStart,dateEnd);
        System.out.println(resultList.size());
        //输出结果：6277
    }

    //todo 查询T月和T+2月之间过期的基金
    @Test
    public  void  test_getExpiredFunds() throws Exception{
        //参数为时间戳，无需查询
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2014-05-05 16:00:00");
        List<ThrsysFund>  resultList = thrsysFundService.getExpiredFunds(date);
        System.out.println(resultList.size());
        //输出结果：186
    }

    // todo 根据基金编号查询购买该基金的客户
    @Test
    public void test_getCustomersByFundCode(){
        /*查询测试数据：SELECT DISTINCT
        t.C_FUNDCODE ,
                t.C_CUSTNO ,
                c.C_CUSTNAME,
                t.C_BROKERACCOUNT
        FROM TTRUSTCONTRACTDETAILS t
        LEFT JOIN tcustomerinfo c ON c.C_CUSTNO = t.C_CUSTNO  取任意c_fundcode */
        List<CustomerContractInfoVO>  resultList =  thrsysFundService.getCustomersByFundCode("HT0471");
        System.out.println(resultList.size());
        //输出结果:2
    }

}
