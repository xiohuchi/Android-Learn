package com.app.basejava;

import com.app.basejava.bean.Container;
import com.app.basejava.bean.Point;

import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：
 * <p>
 * <p>
 * Class类是Reflection API 中的核心类，它有以下方法:
 * getName()：获得类的完整名字。
 * getFields()：获得类的public类型的属性。
 * getDeclaredFields()：获得类的所有属性。
 * getMethods()：获得类的public类型的方法。
 * getDeclaredMethods()：获得类的所有方法。
 * getMethod(String name, Class[] parameterTypes)：获得类的特定方法，name参数指定方法的名字，parameterTypes 参数指定方法的参数类型。
 * getConstructors()：获得类的public类型的构造方法。
 * getConstructor(Class[] parameterTypes)：获得类的特定构造方法，parameterTypes 参数指定构造方法的参数类型。
 * newInstance()：通过类的不带参数的构造方法创建这个类的一个对象。
 */

public class TestReflect {

    @Test
    @Ignore("Class类的常用方法")
    public void main1() {
        getClass().getName();//获得类的完整名字。
        getClass().getFields();//
        getClass().getName();//获得类的完整名字。
        getClass().getFields();//获得类的public类型的属性。
        getClass().getDeclaredFields();//获得类的所有属性。
        getClass().getMethods();//获得类的public类型的方法。
        getClass().getDeclaredMethods();//获得类的所有方法。
        getClass().getConstructors();//获得类的public类型的构造方法。
        try {
            //获得类的特定方法，name参数指定方法的名字，parameterTypes 参数指定方法的参数类型。
            getClass().getMethod("main2", TestReflect.class);
            //获得类的特定构造方法，parameterTypes 参数指定构造方法的参数类型。
            getClass().getConstructor(TestReflect.class);
            //通过类的不带参数的构造方法创建这个类的一个对象。
            getClass().newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore("获取Class对象的三种方式")
    public void main2() {
        //1、通过Object类的getClass()方法。
        Class c1 = new String("").getClass();
        //2、通过Class类的静态方法——forName()来实现：
        try {
            Class c2 = Class.forName("com.app.basejava.bean.Container2");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //3、如果T是一个已定义的类型的话，在java中，它的.class文件名：T.class就代表了与其匹配的Class对象
        Class c3 = Container.class;
        Class c4 = int.class;
        Class c5 = Double[].class;
    }

    @Test
    @Ignore("几个反射用例")
    public void main3() throws Exception {
        Class c = Class.forName("com.app.basejava.bean.Point");
        Point p = (Point) c.newInstance();
        p.setX(100);
        p.setY(200);
        Field f = null;
        try {
            f = c.getDeclaredField("x");//获取类的属性x,无视权限（获取方法类似）
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);//设置属性可编辑
        System.out.println(f.get(p));
    }

    @Test
    @Ignore("几个反射用例-修改私有属性")
    public void modifyFiled() throws Exception {
        Point pt1 = new Point(3, 5);
        Class<?> clazz = pt1.getClass();//通过Ponit对象获取Class实例
        //得到一个字段
        Field fieldY = clazz.getField("y"); //y 是变量名
        //fieldY的值是5么？？ 大错特错
        //fieldY和pt1根本没有什么关系，你看，是pt1.getClass()，是 字节码 啊
        //不是pt1对象身上的变量，而是类上的，要用它取某个对象上对应的值
        //要这样
        System.out.println(fieldY.get(pt1)); //这才是5

        //现在要x了

        /*
        Field fieldX = pt1.getClass().getField("x"); //x 是变量名
        System.out.println(fieldX.get(pt1));
        */

        //运行 报错 私有的，找不到
        //NoSuchFieldException
        //说明getField 只可以得到 公有的
        //怎么得到私有的呢？？

        /*
        Field fieldX = pt1.getClass().getDeclaredField("x"); //这个管你公的私的，都拿来
        //然后轮到这里错了
        // java.lang.IllegalAccessException:
        //Class com.ncs.ReflectTest can not access a member of class com.ncs.Point with modifiers "private"
        System.out.println(fieldX.get(pt1));
        */

        //三步曲 一是不让你知道我有钱 二是把钱晃一下，不给用  三是暴力抢了

        //暴力反射
        Field fieldX = pt1.getClass().getDeclaredField("x"); //这个管你公的私的，都拿来
        fieldX.setAccessible(true);//上面的代码已经看见钱了，开始抢了
        fieldX.set(pt1, 2);//抢到手自己改一下
        System.out.println(pt1.getX());
        //out 2 OK!!
    }

    @Test
    @Ignore("几个反射用例-调用私有方法")
    public void invokeMethod() throws Exception {
        Class<?> clazz = Point.class;//通过Ponit类获取Class实例
        //调用Point类中的draw方法
        Method method = clazz.getDeclaredMethod("draw", String.class);
        method.setAccessible(true);
        method.invoke(clazz.newInstance(), "HelloWorld");

    }

    @Test
    @Ignore("几个反射用例-调用构造方法")
    public void invokeConstructor() throws Exception {
        Class<?> clazz = Class.forName("com.app.basejava.bean.Point");//通过类名获取Class实例
        Point pt1 = null;
        Point pt2 = null;
        Point pt3 = null;
        //取得全部的构造函数
        Constructor<?> cons[] = clazz.getConstructors();

        for (int i = 0; i < cons.length; i++) {
            System.out.println("构造方法：  " + cons[i]);
        }
        //这里构造方法的顺序倒过来了,有点诡异
        pt1 = (Point) cons[2].newInstance();
        pt2 = (Point) cons[1].newInstance(20);
        pt3 = (Point) cons[0].newInstance(49, 81);
        System.out.println(pt1);
        System.out.println(pt2);
        System.out.println(pt3);
        System.out.println(clazz.getMethod("setX", int.class));
        System.out.println(clazz.getMethod("getX"));
    }
}
