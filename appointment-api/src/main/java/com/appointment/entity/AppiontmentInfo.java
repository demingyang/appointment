package com.appointment.entity;

import java.io.Serializable;

/**
 * 视频预约信息
 * Date: 2017/6/7
 * Time: 15:59
 * User: Kayle
 */
public class AppiontmentInfo implements Serializable {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = -2175955592786786295L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 预约日期
     */
    private String appiontmentDate;

    /**
     * 预约开始时间
     */
    private String startTime;

    /**
     * 预约结束时间
     */
    private String endTime;

    /**
     * 空闲坐席
     */
    private Integer freeNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppiontmentDate() {
        return appiontmentDate;
    }

    public void setAppiontmentDate(String appiontmentDate) {
        this.appiontmentDate = appiontmentDate;
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

    public Integer getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(Integer freeNum) {
        this.freeNum = freeNum;
    }

    @Override
    public String toString() {
        return "AppiontmentInfo{" +
                "id=" + id +
                ", appiontmentDate='" + appiontmentDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", freeNum=" + freeNum +
                '}';
    }
}
