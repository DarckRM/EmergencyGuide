package com.emergencyguide.Controller.Util;

import java.io.UnsupportedEncodingException;

/**
 * @author DarckLH
 * @date 2021/5/25 20:56
 * @Description
 */
public class BaseUtil {

    public static boolean IsNullOrEmpty(Object value){
        if (value == null || value.toString().isEmpty())
            return true;
        else
            return  false;
    }
    public static byte[] getBytes(String source,String charset){
        try {
            return source.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

}
