package com.young.view.happycoin.modle;

/**
 * Created by Administrator on 2016/4/29.
 */
public class User  extends  Entity{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String name ;
    private String password ;



}
