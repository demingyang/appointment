package com.appointment.entity;

import com.richgo.util.DateUtil;

import java.io.Serializable;

/**
 * 视频预约实体类
 * Date: 2017/6/13
 * Time: 11:29
 * User: Kayle
 */
public class VideoRequest implements Serializable {

    private static final long serialVersionUID = -5810647748684976745L;

    /**
     * 序列号
     */
    private Integer serialNo;

    /**
     * 业务类型编号
     */
    private String orderTypeCode;

    /**
     * 业务主键
     * 产品购买合同编号或投资者身份转换申请编号
     */
    private String orderCode;

    /**
     * 理顾或员工编号
     */
    private String empNo;

    /**
     * 预约时间
     */
    private String reserveTime;

    /**
     * 预约开始时间
     */
    private String startTime;

    /**
     * 预约结束时间
     */
    private String endTime;

    /**
     * 预约状态
     */
    private String status;

    public VideoRequest() {
    }

    public VideoRequest(String orderTypeCode, String orderCode, String empNo, String reserveTime, String startTime, String endTime) {
        this.orderTypeCode = orderTypeCode;
        this.orderCode = orderCode;
        this.empNo = empNo;
        this.reserveTime = reserveTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "VideoRequest{" +
                "serialNo=" + serialNo +
                ", orderTypeCode='" + orderTypeCode + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", empNo='" + empNo + '\'' +
                ", reserveTime='" + reserveTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public VideoRequestVO toVideoRequestVO() {
        VideoRequestVO videoRequestVO = new VideoRequestVO();
        videoRequestVO.setSerialNo(this.serialNo);
        videoRequestVO.setOrderTypeCode(this.orderTypeCode);
        videoRequestVO.setOrderCode(this.orderCode);
        videoRequestVO.setEmpNo(this.empNo);
        videoRequestVO.setReserveTime(DateUtil.toDateYmdWthH(this.reserveTime));
        videoRequestVO.setStartTime(this.startTime);
        videoRequestVO.setEndTime(this.endTime);
        videoRequestVO.setStatus(this.status);
        return videoRequestVO;
    }
}
