package com.river.toutiao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class KmsUserLoginHistory implements Serializable {
    /**
     * 序列编号
     */
    private Integer seqid;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 登陆ip
     */
    private String ip;

    /**
     * 更新时间
     */
    private Date logdate;

    private static final long serialVersionUID = 1L;

    public Integer getSeqid() {
        return seqid;
    }

    public void setSeqid(Integer seqid) {
        this.seqid = seqid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }
}