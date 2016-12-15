package com.app.basejava.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：
 */

public class Container3<T> {

    public List<T> fillList(T t, int size) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            list.add(t);
        }
        return list;
    }
}
