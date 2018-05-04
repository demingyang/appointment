package com.richgo.thrsys.customer;

import com.richgo.thrsys.entity.customer.CustomerTypeVO;
import com.richgo.thrsys.service.customer.IPointsCustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by liuxinyu on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class IPointsCustomerServiceTest {
    @Autowired
    private IPointsCustomerService iPointsCustomerService;
   // todo
    @Test
    public void  test_getAllCustomerList(){
        // 参数为分页 无需查询
        List<CustomerTypeVO> result =  iPointsCustomerService.getAllCustomerList(1,20);
        System.out.println(result.size());
        // 输出结果：20
    }
}
