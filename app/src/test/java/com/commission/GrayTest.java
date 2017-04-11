package com.commission;

import com.commission.yore.commission.Business;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by duanbiwei on 2017/4/10.
 */
public class GrayTest {
    private static final String UNKNOWN_ERROR = "未知的错误";           // 这个不用解释了吧
    private static final String NO_SUCH_ACCOUNT = "用户名或密码错误";    // 登录错误
    private static final String UPPER_LIMIT = "数量超出可出售上限";      // 输入的数值太大或者数据库里的sum加上输入数值的和越界
    private static final String COMMIT_ERROR = "提交确认失败";          // 发送-1时未知的错误
    private static final String COMMIT_TWICE = "请不要重复确认";        // 发送过-1后又发了-1时候的错误
    private static final String COMMIT_NOT_REACH_REQUIRE = "未达到结束确认要求：每样商品至少售出1"; // 发送-1时每种商品没有至少售出1
    private static final String NUMBER_NO_SENSE = "数量不合法";        // 输入的数量小于0或者全为0
    private static final String NO_TOWN_NAME = "未填写城市名";          // 不用解释
    private static final String INVALID_DATE = "日期不合法";            // 不用解释
    private static final String DATABASE_EXEC_ERROR = "数据操作错误";   // 数据库连接错误，检查数据库吧
    private static final String COMMIT_ALREADY = "当月已经提交过终止消息，修改数据请联系管理员"; // 发送-1后又发送出售商品的消息
    private static final String AUTO_COMMIT_LAST_MONTH = "检测到上月份未自动提交出售信息确认，已自动提交"; // 登录提示
    private static final String NOT_REACH_LAST_MONTH = "上月业绩未达标，请联系管理人员进行处理";          // 登录提示
    private static final String ADMING_LOGIN = "欢迎管理员登录";
    Business business;
    String[] result;
    @Before
    public void login() {
        business = new Business("sale003", "12345");
        business.login();
    }

    @Test
    public void testIllegalInput() {
        result = business.saleNumUpdate(new Date(), -2, 1, 1, "Harbin", true);
        String[] en = {"0",NUMBER_NO_SENSE};
        assertEquals(result, en);

    }

    @Test
    public void testOverLimit() {
        business.saleNumUpdate(new Date(),30, 30, 30, "Harbin", true);
        result = business.saleNumUpdate(new Date(), 50, 1, 1, "Harbin", true);
        String[] en = {"0", "lock" + UPPER_LIMIT};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
        DatabaseUtil.deleteLast();
    }

    @Test
    public void testNullCity() {
        business.saleNumUpdate(new Date(),30, 30, 30, "Harbin", true);
        result = business.saleNumUpdate(new Date(), 10, 1, 1, null, true);
        String[] en = {"0", NO_TOWN_NAME};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();

    }

    @Test
    public void testSuccessUpdate() {
        business.saleNumUpdate(new Date(),30, 30, 30, "Harbin", true);
        result = business.saleNumUpdate(new Date(), 1, 1, 1, "Harbin", true);
        String[] en = {"1"};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
        DatabaseUtil.deleteLast();
    }

    @Test
    public void testOverLock() {
        business.saleNumUpdate(new Date(),69, 30, 30, "Harbin", true);
        result = business.saleNumUpdate(new Date(), 2, 1, 1, "Harbin", true);
        String[] en = {"0", "lock" + UPPER_LIMIT};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
    }

    @Test
    public void testOverStock() {
        business.saleNumUpdate(new Date(),23, 79, 30, "Harbin", true);
        result = business.saleNumUpdate(new Date(), 1, 2, 1, "Harbin", true);
        String[] en = {"0", "stocks" + UPPER_LIMIT};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
    }

    @Test
    public void testOverBarrel() {
        business.saleNumUpdate(new Date(),23, 34, 89, "Harbin", true);
        result = business.saleNumUpdate(new Date(), 1, 1, 2, "Harbin", true);
        String[] en = {"0", "barrels" + UPPER_LIMIT};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
    }

    @Test
    public void testCommit() {
        business.saleNumUpdate(new Date(),30, 30, 30, "Harbin", true);
        result = business.saleNumUpdate(new Date(), -1, 1, 1, "Harbin", true);
//        String[] en = {"0", "barrels" + UPPER_LIMIT};
        String[] en = {"1"};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
        DatabaseUtil.deleteFromCommit();
    }

    @Test
    public void testCommitFail() {
        business.saleNumUpdate(new Date(),23, 45, 0, "Harbin", true);
        result = business.saleNumUpdate(new Date(), -1, 1, 1, "Harbin", true);
        String[] en = {"0", COMMIT_NOT_REACH_REQUIRE};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
    }

    @Test
    public void testCommitFail1() {
        business.saleNumUpdate(new Date(),23, 0, 56, "Harbin", true);
        result = business.saleNumUpdate(new Date(), -1, 1, 1, "Harbin", true);
        String[] en = {"0", COMMIT_NOT_REACH_REQUIRE};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
    }
    @Test
    public void testCommitFail2() {
        business.saleNumUpdate(new Date(),0, 45, 56, "Harbin", true);
        result = business.saleNumUpdate(new Date(), -1, 1, 1, "Harbin", true);
        String[] en = {"1", COMMIT_NOT_REACH_REQUIRE};
        assertEquals(result, en);
        DatabaseUtil.deleteLast();
    }


//    @After
//    public void fakerollback() {
//        if (result[0].equals("1")) {
//            DatabaseUtil.deleteLast();
//        }
//        DatabaseUtil.deleteLast();
//    }

}
