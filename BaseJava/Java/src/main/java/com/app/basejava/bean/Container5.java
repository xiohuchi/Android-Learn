package com.app.basejava.bean;

import java.lang.reflect.Array;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：
 */

public class Container5<T> {

    public T[] create(Class<T> type) {
        return (T[]) Array.newInstance(type, 10);
    }

}
