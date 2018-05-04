package com.appointment.service.impl;

import com.alibaba.fastjson.JSON;
import com.appointment.manager.CommunicationManager;
import com.appointment.service.AppointmentService;
import com.richgo.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/6/6
 * Time: 17:44
 * User: Kayle 视频面签预约服务实现类
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final String QUERY_APT_LIST = ConfigUtil.getValue("api.queryAptList");
    private static final String SAVE_APT_INFO = ConfigUtil.getValue("api.saveAptInfo");
    private static final String CANCEL_APT_INFO = ConfigUtil.getValue("api.cancelAptInfo");
    private static final String QUERY_ORDER_RESULT = ConfigUtil.getValue("api.queryOrderResult");
    private static final String EDIT_ORDER_INTERVIEW_CONN_STATUS = ConfigUtil.getValue("api.editOrderInterviewConnStatus");
    private static final String UPDATE_BIZ_ORDER = ConfigUtil.getValue("api.updateBizOrder");

    /* 日志对象 */
    private final static Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    CommunicationManager manager;

    @Override
    public String queryAptList(String queryDate) {
        Map<String, String> param = new HashMap<>();
        param.put("queryDate", queryDate);
        return manager.post(QUERY_APT_LIST, JSON.toJSONString(param));
    }

    @Override
    public String saveAptInfo(String orderCode, String aptId, String orderTypeCode, String orderTypeDesc,String customerType,String customerCode,String identityType,String projectCode,String projectName,String amount,String salesPerson) {
        log.info("调用方法：saveAptInfo ---------开始------------"); //刘鑫宇添加  20171220
        log.info("入参：orderCode：{}，aptId：{}，orderTypeCode：{}，orderTypeDesc：{}，customerType：{}，customerCode：{}，" +
                "identityType：{}，projectCode：{}，projectName：{}，amount：{}，salesPerson：{}",orderCode,aptId,orderTypeCode,
                orderTypeDesc,customerType,customerCode,identityType,projectCode,projectName,amount,salesPerson); // 刘鑫宇添加  20171220
        Map<String, String> param = new HashMap<>();
        param.put("orderCode", orderCode);
        param.put("aptId", aptId);
        param.put("orderTypeCode", orderTypeCode);
        param.put("orderTypeDesc", orderTypeDesc);
        // 适当性二期 理顾宝1.3需求修改 start  刘鑫宇添加  20171220
        param.put("customerType", customerType); //客户类型
        param.put("customerCode", customerCode); //客户编号
        param.put("identityType", identityType); // 证件类型
        param.put("projectCode", projectCode); // 产品代码
        param.put("projectName", projectName); // 产品名称
        param.put("amount", amount);           // 打款金额
        param.put("salesPerson", salesPerson); // 客户经理
        String result = manager.post(SAVE_APT_INFO, JSON.toJSONString(param));
        log.info("出参：{}",result);
        log.info("调用方法：saveAptInfo ---------结束------------");
        // 适当性二期 理顾宝1.3需求修改 end  刘鑫宇添加  20171220
        return result;
    }

    @Override
    public String cancelAptInfo(String orderTypeCode, String orderCode) {
        Map<String, String> param = new HashMap<>();
        param.put("orderTypeCode", orderTypeCode);
        param.put("orderCode", orderCode);
        return manager.post(CANCEL_APT_INFO, JSON.toJSONString(param));
    }

    @Override
    public String queryOrderResult(String orderTypeCode, String orderCode) {
        Map<String, String> param = new HashMap<>();
        param.put("orderTypeCode", orderTypeCode);
        param.put("orderCode", orderCode);
        return manager.post(QUERY_ORDER_RESULT, JSON.toJSONString(param));
    }

    @Override
    public String editOrderInterviewConnStatus(String orderCode, String orderTypeCode) {
        Map<String, String> param = new HashMap<>();
        param.put("orderCode", orderCode);
        param.put("orderTypeCode", orderTypeCode);
        return manager.post(EDIT_ORDER_INTERVIEW_CONN_STATUS, JSON.toJSONString(param));
    }

    @Override
    public String updateBizOrder(String orderCode, String orderTypeCode) {
        Map<String, String> param = new HashMap<>();
        param.put("orderCode", orderCode);
        param.put("orderTypeCode", orderTypeCode);
        return manager.post(UPDATE_BIZ_ORDER, JSON.toJSONString(param));
    }
}
