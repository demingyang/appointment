package com.richgo.thrsys.report;

import com.common.util.DateUtil;
import com.richgo.thrsys.entity.report.CallCenterReport;
import com.richgo.thrsys.service.report.CallCenterReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyu on 2017/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class CallCenterReportServiceTest {
    @Autowired
    private CallCenterReportService callCenterReportService;

    @Test
    public void test_selectCallCenterReportMessage() throws Exception {
        String date = "20150111 00:00:00";
        Date startDate = DateUtil.strToDate(date, "yyyyMMdd HH:mm:ss");
        Map<String,Object> map =  callCenterReportService.selectCallCenterReportMessage("17", startDate);
        System.out.println(map.size());
    }
}
