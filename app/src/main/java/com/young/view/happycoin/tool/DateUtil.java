package com.young.view.happycoin.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;

import com.young.view.happycoin.HappyCoinApplication;

@SuppressLint("NewApi")
public class DateUtil {
    /**
     *
     * @param formtype 按照formtype格式，格式化日期
     *                 "yyyyMMdd" 、"yyyy-MM-dd HH:mm:ss“、
     *                 "yyyyMMddHHmmss"、"yyyy-MM-dd"
     * @return
     */
    public static String getFormatDateLogString(String formtype) {

        String type = formtype;
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Calendar calendat = Calendar.getInstance();
        String time = formatter.format(calendat.getTime());

        return time;
    }

    /**
     * 把字符串格式成"yyyy-MM-dd HH:mm:ss"
     * @param str
     * @return
     */
    public static String getFormatDateString(String str) {
        Date date = null;
        String time = null;
        try {
            if (str.isEmpty())
                return "2015-7-17 11:21";
            date = new Date(Long.parseLong(str));
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            HappyCoinApplication.loger.info(e.getMessage().toString());
        }
        return time;
    }

    /**
     * 把字符串格式成"yyyy-MM-dd"
     * @param str
     * @return
     */
    public static String getFormatDateString1(String str) {
        Date date = null;
        String time = null;
        try {
            if (str.isEmpty())
                return "2015-7-17";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            date = sdf.parse(str);
            time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            HappyCoinApplication.loger.info(e.getMessage().toString());
        }
        return time;
    }
    /**
     * 把"yyyy-MM-dd"格式的字符串，格式成"yyyyMMdd"
     * @param str
     * @return
     */
    public static String getFormatDateString2(String str) {
        Date date = null;
        String time = null;
        try {
            if (str.isEmpty())
                return "2015-7-17";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(str);
            time = new SimpleDateFormat("yyyyMMdd").format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            HappyCoinApplication.loger.info(e.getMessage().toString());
        }
        return time;
    }


//    public static String getFormatDateString2() {
//
//        String type = "yyyy-MM-dd";//HHmmss
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat(type);
//        Calendar calendat = Calendar.getInstance();
//        String time = formatter.format(calendat.getTime());
//        return time;
//    }
//
//    public static String getFormatDateString3() {
//        String type = "yyyy-MM";//HHmmss
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat(type);
//        Calendar calendat = Calendar.getInstance();
//        String time = formatter.format(calendat.getTime());
//        return time;
//    }

    public static String getFormatDateString3(int month) {

        String type = "yyyy-MM";//HHmmss
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Calendar calendat = Calendar.getInstance();
        int curMonth = calendat.get(Calendar.MONTH);
        calendat.set(Calendar.MONTH, curMonth - month);
        String time = formatter.format(calendat.getTime());

        return time;
    }

    public static String getFormatDateString2(int days) {

        String type = "yyyy-MM-dd";//HHmmss
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Calendar calendat = Calendar.getInstance();
        int curDay = calendat.get(Calendar.DAY_OF_MONTH);
        calendat.set(Calendar.DAY_OF_MONTH, curDay - days);
        String time = formatter.format(calendat.getTime());

        return time;
    }

    /**
     * 获取随机数
     * @return
     */
    public static String getSerialNo() {

        Random random = new Random();
        return getFormatDateString("yyyyMMddHHmmss") + (random.nextInt(8999) + 1000);
    }

    public static long getIntervalMills1(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM").parse(dateTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return -1;
        }
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static long getIntervalMills(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return -1;
        }
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static long getIntervalMills2(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return -1;
        }
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static long getIntervalMills3(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return -1;
        }
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static long getIntervalMills4(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("HH:mm:ss").parse(dateTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return -1;
        }
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static String turnMRDigit(String text_in) {
        String ret = "0.0";
        Double d = 0.0;
        String left = "";
        String right = "";

        String tt0[] = text_in.split("\\.");
        if (tt0.length > 2) {
            System.out.println(text_in + "\t:" + ret);
            return ret;
        } else if (tt0.length == 1) {
            ret = Integer.parseInt(text_in) + "";
            System.out.println(text_in + "\t:" + ret.length());
            return ret.length() + "";
        } else {
            d = Double.parseDouble(text_in);
            String tt[] = (d + "").split("\\.");
            if (tt.length == 2) {
                if (!tt[0].equals("0"))
                    left = tt[0].length() + "";
                else
                    left = "0";
                if (!tt[1].equals("0"))
                    right = tt[1].length() + "";
                else
                    right = "";

            } else {

                if (!tt[0].equals("0"))
                    left = tt[0].length() + "";
                else
                    left = "0";
            }
        }
        if (right.length() >= 1)
            ret = left + "." + right;
        else
            ret = left;
        System.out.println(text_in + "\t:" + ret);
        return ret;

    }

    public static String getColecionDeviceFlow(Context context) {

        String imei = PdaHelper.getIMEI(context);
        if (imei == null || imei.length() < 10)
            return null;
        else {
            String ret = imei + getFormatDateString("yyyyMMddHHmmss");
            if (ret.length() > 32)
                ret = ret.substring(ret.length() - 32, ret.length());
            return ret;
        }


    }

}
