package com.app.basejava.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：
 */

public class Container4<T> {

    public T create(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
