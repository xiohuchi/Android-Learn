package com.app.basejava.bean;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：泛型类，
 * E – Element (在集合中使用，因为集合中存放的是元素)
 * T – Type（Java 类）
 * K – Key（键）
 * V – Value（值）
 * N – Number（数值类型）
 * ？ – 表示不确定的java类型（无限制通配符类型）
 * 在编译期，是无法知道K和V具体是什么类型，只有在运行时才会真正根据类型来构造和分配内存。
 * 可以看一下现在Container类对于不同类型的支持情况：
 */

public class Container<K, V> {
    private K key;
    private V value;

    public Container(K k, V v) {
        key = k;
        value = v;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}