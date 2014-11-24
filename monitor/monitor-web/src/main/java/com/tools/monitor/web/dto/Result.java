package com.tools.monitor.web.dto;

import java.io.Serializable;

/**
 * Created by Rolyer Luo(rolyer.live@gmail.com) on 14-11-24 上午10:53.
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean success;
    private Object data;

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}