package com.appointment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Date: 2017/6/13
 * Time: 11:29
 * User: Kayle
 */
public class VideoRequestVO implements Serializable {

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
    private Date reserveTime;

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

    public VideoRequestVO() {
    }

    public VideoRequestVO(String orderTypeCode, String orderCode, String empNo, Date reserveTime, String startTime, String endTime) {
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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8" )
    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
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
}
