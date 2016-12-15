package com.app.basejava;

import com.app.basejava.bean.Container;
import com.app.basejava.bean.Container2;
import com.app.basejava.bean.Container3;
import com.app.basejava.bean.Container4;
import com.app.basejava.bean.Container5;
import com.app.basejava.impl.FruitGenerator;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：
 */

public class TestGenericity {
    @Test
    @Ignore("泛型类")
    public void main1() {
        Container<String, String> c1 = new Container<>("name", "findingsea");
        Container<String, Integer> c2 = new Container<>("age", 24);
        Container<Double, Double> c3 = new Container<>(1.1, 2.2);
        System.out.println(c1.getKey() + " : " + c1.getValue());
        System.out.println(c2.getKey() + " : " + c2.getValue());
        System.out.println(c3.getKey() + " : " + c3.getValue());
    }

    @Test
    @Ignore("泛型接口")
    public void main2() {
        FruitGenerator generator = new FruitGenerator();
        System.out.println(generator.next());
        System.out.println(generator.next());
        System.out.println(generator.next());
        System.out.println(generator.next());
    }

    @Test
    @Ignore("泛型方法")
    public void main3() {
        out("findingsea");
        out(123);
        out(11.11);
        out(true);
        out("findingsea", 123, 11.11, true);
    }

    public <T> void out(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }

    @Test
    @Ignore("类型擦除")
    public void main4() {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2);
        //这就是Java泛型的类型擦除造成的!

        List<Integer> list = new ArrayList<Integer>();
        Map<Integer, String> map = new HashMap<Integer, String>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        //我们期待的是得到泛型参数的类型，但是实际上我们只得到了一堆占位符。
    }

//    public T[] makeArray() {
//        // error: Type parameter 'T' cannot be instantiated directly
//        return new T[5];
//    }

    @Test
    @Ignore("内部一致性1")
    public void main5() {
        Container2<String> m = new Container2<String>();
        m.set("findingsea");
        String s = m.get();
        System.out.println(s);

        //内部一致性，也是我们放进去的是什么类型的对象，取出来还是相同类型的对象，这一点让Java的泛型起码还是有用武之地的。
    }

    @Test
    @Ignore("内部一致性2")
    public void main6() {
        Container3<String> m = new Container3<String>();
        List<String> list = m.fillList("findingsea", 5);
        System.out.println(list.toString());

        //内部一致性，也是我们放进去的是什么类型的对象，取出来还是相同类型的对象，这一点让Java的泛型起码还是有用武之地的。
    }

    @Test
    @Ignore("擦除的补偿-显式地传递类型标签,用类型标签生成新对象的方法")
    public void main7() {
        Container4<String> m = new Container4<String>();
        String s = m.create(String.class);
        //展示了一种用类型标签生成新对象的方法，但是这个办法很脆弱，因为这种办法要求对应的类型必须有默认构造函数，遇到 Integer 类型的时候就失败了，而且这个错误还不能在编译器捕获。
    }

    @Test
    @Ignore("擦除的补偿-显式地传递类型标签,对泛型数组的擦除补偿")
    public void main8() {
        Container5<Integer> m = new Container5<Integer>();
        Integer[] strings = m.create(Integer.class);
        //展示了对泛型数组的擦除补偿，本质方法还是通过显式地传递类型标签，通过Array.newInstance(type, size)来生成数组，同时也是最为推荐的在泛型内部生成数组的方法。
    }
}
