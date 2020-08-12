package com.mz.prisonpolice.entity;

/**
 * @author Muzzz
 * @CreateTime 2020/4/12
 * @Desc
 */
public class HttpStatusBean {

    /**
     * msg : 操作成功
     * code : 0
     */

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return code == 0;
    }
}
