package com.ylz.seaf.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseParam {
    Map<String, String> data = new HashMap<>();
    String funid;
    String pwd;
    String usr;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getFunid() {
        return funid;
    }

    public void setFunid(String funid) {
        this.funid = funid;
    }
    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data ) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<String> keys = data.keySet();
        for(String key : keys) {
            String value = data.get(key);

            builder.append(key).append("==>").append(value).append("\n");
        }
        return builder.toString();
    }
}
