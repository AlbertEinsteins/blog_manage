package com.item.javaee.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Admin
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-03 11:26
 * @Version: 1.0
 **/
@Entity
@Table(name = "admin")
public class Admin implements Serializable {
    @Column(name = "admin_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId ;

    @Column(name = "admin_name")
    private String adminName ;

    @Column(name = "admin_pid")
    private Integer adminPid ;

    @Column(name = "admin_path")
    private String adminPath ;

    //用来生成属性结构
    @Transient
    private List<Admin> children ;

    public Admin() { }
    public Admin(String adminName, Integer adminPid, String adminPath) {
        this.adminName = adminName;
        this.adminPid = adminPid;
        this.adminPath = adminPath;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAdminPid() {
        return adminPid;
    }

    public void setAdminPid(Integer adminPid) {
        this.adminPid = adminPid;
    }

    public String getAdminPath() {
        return adminPath;
    }

    public void setAdminPath(String adminPath) {
        this.adminPath = adminPath;
    }

    public List<Admin> getChildren() {
        return children;
    }

    public void setChildren(List<Admin> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPid=" + adminPid +
                ", adminPath='" + adminPath + '\'' +
                ", children=" + children +
                '}';
    }

}
