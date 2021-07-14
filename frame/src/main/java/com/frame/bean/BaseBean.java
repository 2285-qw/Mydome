package com.frame.bean;

public class BaseBean {
    public int error;
    public long serverTime;
    public String msg;

    //判断数据是否为空(详情请看BaseModel)
    public boolean isEmpty() {
        return false;
    }
}
