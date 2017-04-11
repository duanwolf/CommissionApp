package com.commission;

import com.commission.yore.commission.Business;

import org.junit.Test;

/**
 * Created by duanbiwei on 2017/4/5.
 */
public class LoginTest {
    Business business;
    @Test
    public void login() {
        business = new Business("", "");
        business.login();
    }

    @Test
    public void login1() {
        business = new Business("", "fdasfa");
        business.login();
    }

    @Test
    public void login2() {
        business = new Business("fdas f", "");
        business.login();
    }

    @Test
    public void login3() {
        business = new Business("sale2", "fasf");
        business.login();
    }

    @Test
    public void login4() {
        business = new Business("sale001", "12345");
        business.login();
    }

    @Test
    public void login5() {
        business = new Business("sale001", "fdaasf");
        business.login();
    }
}
