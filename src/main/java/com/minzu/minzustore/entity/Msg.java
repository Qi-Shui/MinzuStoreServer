package com.minzu.minzustore.entity;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Map;

public class Msg extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public Msg() {
        put("code", 0);
    }

    public static Msg error() {
        return error(500, "信息错误，请重试");
    }

    public static Msg error(String msg) {
        return error(500, msg);
    }

    public static Msg error(int code, String msg) {
        Msg m = new Msg();
        m.put("code", code);
        m.put("msg", msg);
        return m;
    }

    public static Msg ok() {
        return new Msg();
    }

    public static Msg ok(String msg) {
        Msg m = new Msg();
        m.put("msg", msg);
        return m;
    }

    public static Msg ok(Map<String, Object> map) {
        Msg m = new Msg();
        m.putAll(map);
        return m;
    }

    public Msg put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
