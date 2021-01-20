package com.alibaba.csp.sentinel.dashboard.repository.machine;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "machine_info")
public class MachineInfoPO implements Serializable {

    private static final long serialVersionUID = 7200023615444172715L;

    /**id，主键*/
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**创建时间*/
    @Column(name = "app")
    private String app;

    /**修改时间*/
    @Column(name = "appType")
    private Integer appType;

    /**应用名称*/
    @Column(name = "hostname")
    private String hostname;

    /**统计时间*/
    @Column(name = "ip")
    private String ip;

    /**资源名称*/
    @Column(name = "port")
    private Integer port;

    /**通过qps*/
    @Column(name = "lastHeartbeat")
    private Timestamp lastHeartbeat;

    /**成功qps*/
    @Column(name = "heartbeatVersion")
    private String heartbeatVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Timestamp getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(Timestamp lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public String getHeartbeatVersion() {
        return heartbeatVersion;
    }

    public void setHeartbeatVersion(String heartbeatVersion) {
        this.heartbeatVersion = heartbeatVersion;
    }
}
