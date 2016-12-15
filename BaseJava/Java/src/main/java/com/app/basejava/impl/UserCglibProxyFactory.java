package com.app.basejava.impl;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：Gglib 动态代理工厂
 */

public class UserCglibProxyFactory{}
//        implements MethodInterceptor {
//    private Object target;
//
//    //创建代理类
//    public Object createProxy(Object target) {
//        this.target = target;
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(this.target.getClass());
//        enhancer.setCallback(this);
//        return enhancer.create();
//    }
//
//    //改变委托类方法的调用逻辑
//    @Override
//    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        Object result = null;
//        if (method.getName().equals("login")) {
//            doBefore();
//            result = proxy.invokeSuper(target, args);
//        }
//        if (method.getName().equals("updateInfo")) {
//            doBefore();
//            result = proxy.invokeSuper(target, args);
//            doAfter();
//        }
//        return result;
//    }
//
//    private void doBefore() {
//        System.out.println("Cglib动态代理:前置动作……");
//    }
//
//    private void doAfter() {
//        System.out.println("Cglib动态代理:后置动作……");
//    }
//}