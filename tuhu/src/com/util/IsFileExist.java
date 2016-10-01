package com.util;

import java.io.File;

/**
 * Created by Administrator on 2016/9/28.
 */
public class IsFileExist {
    public static File path(String destFileName){
        try {
            File file=new File(destFileName);
            if(file.exists()){
                return file;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
