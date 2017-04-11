package com.commission;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.commission.yore.util.MD5;
import com.commission.yore.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Commission.db";
    public static final String TABLE_NAME = "commission";
    private static String name = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static Connection conn = null;
    private DatabaseUtil helper;
    private SQLiteDatabase db;
    private Context context;
//    public static boolean debug = false;
    /**
     * 获取一个数据库连接，由于业务比较简单，不使用连接池
     *
     * @return java.sql.Connection 的一个实例
     */

    public static Connection getConnection() {
        if (conn == null) {
            try {
                getConnectionInfo();
                // 新版本jdk不需要这句话，旧版本有报错打开注释，Warning请忽略
                // Class.forName(name);
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 第一次连接数据库时获取数据库连接参数
     */
    private static void getConnectionInfo() {
        if (name == null) {

            name = PropertiesUtil.getProperty("db_name");
            url = PropertiesUtil.getProperty("db_url");
            username = PropertiesUtil.getProperty("db_username");
            password = PropertiesUtil.getProperty("db_password");
        }
    }

    public static ResultSet execSQL(String sql) {
        if (conn == null) getConnection();
        try {
//            if (debug) conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.execute(sql);
            ResultSet rs = st.getResultSet();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 长时间不用数据库时请调用此方法关闭连接
     */
    public static void closeConnection() {
        try {
            if (conn != null) conn.close();
            conn = null;
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        }
    }

    /**
     * 用于密码的加密
     *
     * @param str 密码明文
     * @return String 密码密文
     */
    public static String md5Encode(String str) {
        try {
            return MD5.md5(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 测试
    public static ResultSet addUser(String account, String password, String nickName, Long time) {
        String _password = md5Encode(password);
        String sql = "insert into cm_user" +
                " (user_account, user_password, user_nickname, user_sign_up_time)" +
                " values ('" + account + "', '" + _password + "', '" + nickName + "'," + time + ")";
        System.out.println("INFO: addUser(" + account + "," + password + "," + nickName + ")");
        return execSQL(sql);
    }

    public static void deleteLast() {
        String sql = "delete from cm_sale_record where 1 order by record_id desc limit 1";
        execSQL(sql);
    }

    public static void deleteFromCommit() {
        String sql = "delete from cm_end where 1 order by end_id desc limit 1";
        execSQL(sql);
    }
    /**
     * 第一次使用工程的时候调用以创建数据库和测试用户
     * 其他时间不要调用
     */
    public static void createDatabase() {

    }

//    /**
//     * Create a helper object to create, open, and/or manage a database.
//     * This method always returns very quickly.  The database is not actually
//     * created or opened until one of {@link #getWritableDatabase} or
//     * {@link #getReadableDatabase} is called.
//     *
//     * @param context to use to open or create the database
//     * @param name    of the database file, or null for an in-memory database
//     * @param factory to use for creating cursor objects, or null for the default
//     * @param version number of the database (starting at 1); if the database is older,
//     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
//     *                newer, {@link #onDowngrade} will be used to downgrade the database
//     */
//    public DatabaseUtil(Context context) {
//        super(context, DB_NAME, null, DB_VERSION );
//    }
//    public DatabaseUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//        helper = new DatabaseUtil(context);
//    }
//
//    /**
//     * Called when the database is created for the first time. This is where the
//     * creation of tables and the initial population of the tables should happen.
//     *
//     * @param db The database.
//     */
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db = helper.getWritableDatabase();
//        String createTable1 = "create table if not exsits cm_end (\n" +
//                "\tend_id integer unsinged not null auto_increment,\n" +
//                "\tend_time date not null,\n" +
//                "\tend_user_id int(11) NOT NULL DEFAULT 0,\n" +
//                "    end_commission int(11) NOT NULL DEFAULT 0,\n" +
//                "    end_lock int(11) NOT NULL DEFAULT 0,\n" +
//                "    end_stocks int(11) NOT NULL DEFAULT 0,\n" +
//                "    end_barrels int(11) NOT NULL DEFAULT 0,\n" +
//                "    end_update_time int(11) NOT NULL DEFAULT 0,\n" +
//                ")";
//
//        db.execSQL();
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public ResultSet selectSql(String sqlStr) {
//        db = helper.getWritableDatabase();
//    }
}
