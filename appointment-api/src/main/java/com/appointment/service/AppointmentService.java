package com.appointment.service;

/**
 * Date: 2017/6/6
 * Time: 17:43
 * User: Kayle 视频面签预约服务
 */
public interface AppointmentService {

    /**
     * 查询预约时间与坐席数量接口
     * @param queryDate 查询日期
     * @return JSON字符串
     */
    String queryAptList(String queryDate);

    /**
     * 预约接口
     * @param orderCode 合同编号
     * @param aptId 预约日期Id
     * @param orderTypeCode 业务类型编号
     * @param orderTypeDesc 业务类型描述
     * 参数添加 适当性二期 理顾宝1.3版本需求 start  by刘鑫宇  20171219
     * @param customerType  客户类型
     * @param customerCode  客户编号
     * @param identityType  证件类型
     * @param projectCode   产品代码
     * @param projectName   产品名称
     * @param amount         打款金额
     * @param salesPerson    客户经理
     * 参数添加 适当性二期 理顾宝1.3版本需求 end  by刘鑫宇  20171219
     * @return JSON字符串
     */
    String saveAptInfo(String orderCode, String aptId, String orderTypeCode, String orderTypeDesc,
                       String customerType,String customerCode, String identityType,
                       String projectCode,String projectName,String amount,String salesPerson);

    /**
     * 取消预约接口
     * @param orderTypeCode 业务类型编号
     * @param orderCode 合同编号
     * @return JSON字符串
     */
    String cancelAptInfo(String orderTypeCode, String orderCode);

    /**
     * 查询审核结果
     * @param orderTypeCode 业务类型编号
     * @param orderCode 合同编号
     * @return JSON字符串
     */
    String queryOrderResult(String orderTypeCode, String orderCode);

    /**
     * 视频接通状态接口(APP使用)
     * 为了使后台知道有视频进入、需要修改数据库状态。在启动SDK模块之前，
     * 先调用本接口，将数据库状态改为可接通。这样后台才能接通视频。
     * @param orderCode 合同编号
     * @return JSON字符串
     */
    String editOrderInterviewConnStatus(String orderCode, String orderTypeCode);

    /**
     * 视频断开状态接口(异常使用)
     * 为了解决浏览器同时崩溃，视频无法继续进行时所提供的的接口。
     * @param orderCode 合同编号
     * @return JSON字符串
     */
    String updateBizOrder(String orderCode, String orderTypeCode);
}
