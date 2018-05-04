package com.richgo.thrsys.product;

import com.richgo.thrsys.entity.product.ThrsysProductInformation;
import com.richgo.thrsys.service.product.ThrsysProductStatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liuxinyu on 2017/11/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysProductStatusServiceTest {
    @Autowired
    private ThrsysProductStatusService thrsysProductStatusService;
    // todo 根据创建时间获取产品列表
    @Test
    public void test_getProductsByCreateDateTime()throws Exception{
        //直接输入时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2015-01-01 13:00:00");
        List<ThrsysProductInformation> result  =  thrsysProductStatusService.getProductsByCreateDateTime(date);
        System.out.println(result.size());
        //输出结果：7591
    }

    //TODO 获取所有的产品列表
    @Test
    public void test_getAllProducts(){
        // 没有参数 直接调用
        List<ThrsysProductInformation> result = thrsysProductStatusService.getAllProducts();
        System.out.println(result.size());
        //输出结果：7591
    }

}
