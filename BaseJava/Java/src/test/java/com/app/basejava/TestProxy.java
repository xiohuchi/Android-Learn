package com.app.basejava;

import com.app.basejava.api.IUser;
import com.app.basejava.impl.UserCglibProxyFactory;
import com.app.basejava.impl.UserImpl;
import com.app.basejava.impl.UserJdkProxyFactory;
import com.app.basejava.impl.UserStaticProxy;

import org.junit.Before;
import org.junit.Test;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：一看就懂动态代理的作用了。
 */

public class TestProxy {
    private UserImpl target;//被代理类

    @Before
    public void setUp() {
        target = new UserImpl();
    }

    /**
     * 原始
     */
    @Test
    public void testTarget() {
        target.login();
        target.updateInfo();
    }

    /**
     * 静态代理
     */
    @Test
    public void testStaticProxy() {
        UserStaticProxy staticProxy = new UserStaticProxy(target);
        staticProxy.login();
        staticProxy.updateInfo();
    }

    /**
     * Jdk动态代理
     */
    @Test
    public void testJdkProxy() {
        UserJdkProxyFactory jdkFactory = new UserJdkProxyFactory();
        IUser jdkProxy = (IUser) jdkFactory.createProxy(target);
        jdkProxy.login();
        jdkProxy.updateInfo();
    }

    /**
     * Cglib动态代理
     * <p>
     * Jdk只能对接口进行代理，而cglib却可以对普通类进行代理！
     * <p>
     * 注意：此代码在Java环境可运行，但在Android环境会报错！
     * 原因：Java是JVM虚拟器,Android是DarvikVM虚拟机,类加载机制有区别，而Cglib是基于继承的字节码生成技术，所有有问题。
     * Android中推荐使用AspectJ，不过这个玩意在AS中的配置有点麻烦
     */
    @Test
    public void testCglibProxy() {
        UserCglibProxyFactory cglibFactory = new UserCglibProxyFactory();
//        IUser cglibProxy = (IUser) cglibFactory.createProxy(target);
//        cglibProxy.login();
//        cglibProxy.updateInfo();
    }
}
