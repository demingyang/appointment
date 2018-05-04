package com.richgo.thrsys.statistics;

import com.richgo.thrsys.service.statistics.ThsysStatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * Created by liuxinyu on 2017/11/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThsysStatisticsServiceTest {
    @Autowired
    private ThsysStatisticsService thsysStatisticsService;
    //todo 累计发行产品数量
    @Test
    public void test_countIssueProduct(){
        //没有参数，直接调用
        Integer result = thsysStatisticsService.countIssueProduct();
        System.out.println(result);
        // 输出结果：9148
    }

     // todo 年度资产配置规模
    @Test
    public  void  test_sumAnnualAssets(){
        BigDecimal result = thsysStatisticsService.sumAnnualAssets("2016");
        System.out.println(result);
        //输出结果：165641240972
    }

    // todo 累计资产配置规模
    @Test
    public void  test_sumAssets(){
        BigDecimal result = thsysStatisticsService.sumAssets();
        System.out.println(result);
        //输出结果;236004520972
    }
}
