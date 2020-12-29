package com.mapper.pojo;

/**
 * @author lijichen
 * @date 2020/12/12 - 22:32
 */
public enum  EmpState {

    LOGIN(100,"登录中"),LOGOUT(200,"登出中"),REMOVE(300,"被移除");

    private int code;
    private String mag;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    EmpState(int code, String mag) {
        this.code = code;
        this.mag = mag;
    }
}
