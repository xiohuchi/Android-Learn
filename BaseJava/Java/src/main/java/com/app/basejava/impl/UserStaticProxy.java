package com.app.basejava.impl;

import com.app.basejava.api.IUser;
import com.app.basejava.impl.UserImpl;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：静态代理类
 */

public class UserStaticProxy implements IUser {

    private UserImpl userImpl;

    public UserStaticProxy(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    /**
     * 登录有前置动作
     */
    @Override
    public void login() {
        doBefore();
        this.userImpl.login();
    }

    /**
     * 更新有前置动作和后置动作
     */
    @Override
    public void updateInfo() {
        doBefore();
        this.userImpl.updateInfo();
        doAfter();
    }

    private void doBefore() {
        System.out.println("静态代理:前置动作……");
    }

    private void doAfter() {
        System.out.println("静态代理:后置动作……");
    }
}