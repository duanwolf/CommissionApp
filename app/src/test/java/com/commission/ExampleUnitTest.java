package com.commission;

import com.commission.yore.commission.Business;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    Business business;
    @Before
    public void login() throws Exception {
        business = new Business("sale001", "12345");
        business.login();
    }

    @Test
    public void testSaleNumUpdate() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 42, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate1() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 70, 42, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate2() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 71, 42, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate3() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 69, 42, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate4() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 80, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate5() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 81, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate6() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 79, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate7() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 42, 90, city, true);
    }

    @Test
    public void testSaleNumUpdate8() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 42, 91, city, true);
    }

    @Test
    public void testSaleNumUpdate9() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 42, 89, city, true);
    }

    @Test
    public void testSaleNumUpdate10() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 0, 42, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate11() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 1, 42, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate12() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, -1, 42, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate13() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 0, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate14() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 1, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate15() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, -1, 51, city, true);
    }

    @Test
    public void testSaleNumUpdate16() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 42, 0, city, true);
    }

    @Test
    public void testSaleNumUpdate17() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 42, 1, city, true);
    }

    @Test
    public void testSaleNumUpdate18() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 40, 42, -1, city, true);
    }

    @Test
    public void testSaleNumUpdate19() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 0, 0, 0, city, true);
    }

    @Test
    public void testSaleNumUpdate20() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 0, 0, 90, city, true);
    }

    @Test
    public void testSaleNumUpdate21() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 0, 80, 0, city, true);
    }

    @Test
    public void testSaleNumUpdate22() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 0, 80, 90, city, true);
    }

    @Test
    public void testSaleNumUpdate23() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 70, 0, 0, city, true);
    }

    @Test
    public void testSaleNumUpdate24() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 70, 0, 90, city, true);
    }

    @Test
    public void testSaleNumUpdate25() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 70, 80, 0, city, true);
    }

    @Test
    public void testSaleNumUpdate26() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 70, 80, 90, city, true);
    }
    //决策表
    @Test
    public void testSaleNumUpdate27() {
        Date date = new Date();
        String city = null;
        business.saleNumUpdate(date, 70, 80, 90, city, true);
    }

    @Test
    public void testSaleNumUpdate28() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 0, 80, 90, city, true);
    }

    @Test
    public void testSaleNumUpdate29() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 16, 0, 90, city, true);
    }

    @Test
    public void testSaleNumUpdate30() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 18, 30, 0, city, true);
    }

    @Test
    public void testSaleNumUpdate31() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 60, 70, 80, city, true);
    }

    @Test
    public void testSaleNumUpdate32() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 60, 70, 100, city, true);
    }

    @Test
    public void testSaleNumUpdate33() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 60, 90, 80, city, true);
    }
    @Test
    public void testSaleNumUpdate34() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 60, 90, 100, city, true);
    }


    @Test
    public void testSaleNumUpdate35() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 80, 70, 80, city, true);
    }


    @Test
    public void testSaleNumUpdate36() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 80, 70, 100, city, true);
    }


    @Test
    public void testSaleNumUpdate37() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 80, 90, 80, city, true);
    }


    @Test
    public void testSaleNumUpdate38() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, 80, 90, 100, city, true);
    }


    @Test
    public void testSaleNumUpdate39() {
        Date date = new Date();
        String city = "Harbin";
        business.saleNumUpdate(date, -1, 90, 80, city, true);
    }
}