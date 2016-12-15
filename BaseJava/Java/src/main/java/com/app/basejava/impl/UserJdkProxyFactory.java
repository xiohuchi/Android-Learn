package com.app.basejava.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：JDK动态代理工厂
 */

public class UserJdkProxyFactory implements InvocationHandler {
    private Object target;

    //创建代理类
    public Object createProxy(Object target) {
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    //改变委托类方法的调用逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = null;
        if (method.getName().equals("login")) {
            doBefore();
            result = method.invoke(target, args);
        }
        if (method.getName().equals("updateInfo")) {
            doBefore();
            result = method.invoke(target, args);
            doAfter();
        }
        return result;
    }

    private void doBefore() {
        System.out.println("JDK动态代理:前置动作……");
    }

    private void doAfter() {
        System.out.println("JDK动态代理:后置动作……");
    }

}
