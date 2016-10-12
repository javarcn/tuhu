package com.main;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by zhangyi on 16/10/9.
 */
public enum DataSingleton {
    INSTANCE;
    private ConcurrentMap<String ,Object >  map = new ConcurrentHashMap<String,Object>();
    public void put(String key,Object value){
        map.put(key,value);
    }
    public Object get(String key){
        return map.get(key);
    }
}
