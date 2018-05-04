package com.appointment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liuyu on 2017/6/9.
 */
public class WaitFlowVO implements Serializable{
    //申请编号
    private long serialNo;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createDate;
    //状态 c_statusdic  0 已保存 1 审核中 2 审核通过 3审核驳回 4已撤销
    private String status;
    //客户名称
    private String customerName;
    //类型
    private String tType;

    public long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(long serialNo) {
        this.serialNo = serialNo;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8" )
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    @Override
    public String toString() {
        return "WaitFlowVO{" +
                "serialNo=" + serialNo +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                ", customerName='" + customerName + '\'' +
                ", tType='" + tType + '\'' +
                '}';
    }
}
