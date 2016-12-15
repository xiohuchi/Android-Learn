package com.app.basejava.impl;

import com.app.basejava.api.IUser;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：用户实现类
 */

public class UserImpl implements IUser {

    @Override
    public void login() {
        System.out.println("小明在登录……");

    }

    @Override
    public void updateInfo() {
        System.out.println("小明在更新信息……");
    }
}