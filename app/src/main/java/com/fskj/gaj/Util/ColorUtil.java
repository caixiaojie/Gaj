package com.fskj.gaj.Util;

import android.graphics.Color;

/**
 * 功能说明:
 * 作    者:zhengwei
 * 创建日期:2017/3/29 12:56
 * 所属项目:fupin
 */
public class ColorUtil {


    public  static  int getReasonColor(int pos){
        int m=pos % 11;
        switch (m){
            case 0:
                return Color.parseColor("#13a4f7");

            case 1:
                return Color.parseColor("#1986ff");

            case 2:
                return Color.parseColor("#14c3f0");

            case 3:
                return Color.parseColor("#0883e0");

            case 4:
                return Color.parseColor("#447bd5");

            case 5:
                return Color.parseColor("#8fc1ff");

            case 6:
                return Color.parseColor("#ffd93a");

            case 7:
            return Color.parseColor("#ff9f0d");

            case 8:
            return Color.parseColor("#ffc000");

            case 9:
                return Color.parseColor("#f8670c");

            case 10:
                return Color.parseColor("#f56bb8");
            default:
                return Color.RED;
        }

    }


    public  static  int getSexColor(int pos){
        int m=pos % 3;
        switch (m){
            case 0:
                return Color.parseColor("#87cefa");

            case 1:
                return Color.parseColor("#d16fd2");
            case 2:
                return Color.parseColor("#cccccc");
            default:
                return Color.parseColor("#87cefa");
        }

    }

    public static  int getIncomeColor(int pos){
        int m=pos % 6;

        switch (m){
            case 0:
                return Color.parseColor("#2cc1f9");
            case 1:
                return Color.parseColor("#ffdd69");
            case 2:
                return Color.parseColor("#fb9dbe");
            case 3:
                return Color.parseColor("#7dba8e");
            case 4:
                return Color.parseColor("#aaf5fc");
            case 5:
                return Color.parseColor("#dd9155");
            default:
                return Color.parseColor("#2cc1f9");
        }
    }


    public static  int getPinkunColor(int pos){
        int m=pos % 4;

        switch (m){
            case 0:
                return Color.parseColor("#87cefa");
            case 1:
                return Color.parseColor("#447bd5");
            case 2:
                return Color.parseColor("#ff9d1c");
            case 3:
                return Color.parseColor("#ff7f50");

            default:
                return Color.parseColor("#2cc1f9");
        }
    }



    public static  int getAgeColor(int pos){
        int m=pos % 7;

        switch (m){
            case 0:
                return Color.parseColor("#91c5ff");
            case 1:
                return Color.parseColor("#a2ceff");
            case 2:
                return Color.parseColor("#a6abc1");
            case 3:
                return Color.parseColor("#d38860");
            case 4:
                return Color.parseColor("#e37c3a");
            case 5:
                return Color.parseColor("#fc7905");
            case 6:
                return Color.parseColor("#ff5500");
            default:
                return Color.parseColor("#2cc1f9");
        }
    }
}
