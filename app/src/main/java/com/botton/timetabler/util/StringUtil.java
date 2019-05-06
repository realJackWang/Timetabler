package com.botton.timetabler.util;

/**
 * @author JackWang
 * @fileName StringUtil
 * @date on 2018/11/19 下午 2:45
 * @email 544907049@qq.com
 **/
public class StringUtil {

    public static int findIntInString(String str){
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }

        }
        return Integer.parseInt(str2);
    }
    public static int stringToDay(String str){
        int day = 0;
        switch (str){
            case "周一":
                day = 1;
                break;
            case "周二":
                day = 2;
                break;
            case "周三":
                day = 3;
                break;
            case "周四":
                day = 4;
                break;
            case "周五":
                day = 5;
                break;
            case "周六":
                day = 6;
                break;
            case "周天":
                day = 7;
                break;
        }

        return day;
    }

    public static String dayToString(int day){
        String str = "周一";
        switch (day){
            case 1:
                str = "周一";
                break;
            case 2:
                str = "周二";
                break;
            case 3:
                str = "周三";
                break;
            case 4:
                str = "周四";
                break;
            case 5:
                str = "周五";
                break;
            case 6:
                str = "周六";
                break;
            case 7:
                str = "周天";
                break;
        }

        return str;
    }

}