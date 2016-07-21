package com.young.view.happycoin.tool;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class PrefTool {
    /**
     *
     * @param name
     * @param context
     * @return SharedPreferences 操作类
     */
    public static SharedPreferences getSharedPreferences(String name ,Context context){
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);

    }
    /**
     * 获取配置参数
     * String
     * @return
     */
    public static String getStringPerferences(SharedPreferences sp ,Context context, String name,
                                              String defValues) {
        synchronized (sp) {
            return sp.getString(name, defValues);
        }
    }
    /*
     * String
     * 设置配置参数
     */
    public static void setStringsave(SharedPreferences sp , Context context, String name, String values) {
        Editor editor = sp.edit();
        editor.putString(name, values);
        editor.commit();
    }
    /*
     * int,获取配置参数
     */
    public static int getIntPreferences(SharedPreferences sp ,Context context,String name,int defValues){
        synchronized (sp) {
            return sp.getInt(name, defValues);
        }
    }
    /*
     * int,设置参数
     */
    public static void setIntSave(SharedPreferences sp ,Context context,String name,int value){
        synchronized(sp){
            Editor editor = sp.edit();
            editor.putInt(name, value);
            editor.commit();
        }
    }
    /*
     * boolean类型,获取配置参数
     */
    public static boolean getBooleanPreferences(SharedPreferences sp ,Context context,String name,boolean defValues){
        synchronized (sp) {
            return sp.getBoolean(name, defValues);
        }
    }
    /*
     * boolean类型,设置参数
     */
    public static void setBooleanSave(SharedPreferences sp ,Context context,String name,boolean value){
        synchronized(sp){
            Editor editor = sp.edit();
            editor.putBoolean(name, value);
            editor.commit();
        }
    }

}


