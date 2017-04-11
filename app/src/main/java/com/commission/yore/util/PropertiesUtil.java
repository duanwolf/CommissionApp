package com.commission.yore.util;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;


/**
 * 用于读取配置文件
 */
public class PropertiesUtil {
    public static final String propertiesFileName = "config.properties";
    private static Properties pps = null;

    // 文件名
    public PropertiesUtil(Context context) {
        pps = new Properties();
        try {
            pps.load(context.getAssets().open(propertiesFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//
//    // 初始化配置读取
//    private static Properties pps = new Properties();
//    // 静态模块程序初始化时缺少文件会报错
//    static {
//        try {
//            pps.load(Application.ACCOUNT_SERVICE.);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 获取配置值
     * @param propertyKey
     * @return 对应键值的配置值，缺省为""
     */
    public static String getProperty(String propertyKey) {
        switch (propertyKey) {
            case "db_name":
                return "com.mysql.jdbc.Driver";
            case "db_url":
                return "jdbc:mysql://127.0.0.1:3306/commission";
            case "db_username":
                return "cat";
            case "db_password":
                return "fish";
            case "lock_cost":
                return 45 + "";
            case "stocks_cost":
                return 30 + "";
            case "barrels_cost":
                return 25 + "";
            case "lock_limit":
                return 70 + "";
            case "stocks_limit":
                return 80 + "";
            case "barrels_limit":
                return 90 + "";
            default:
                return "";
        }
    }

}
