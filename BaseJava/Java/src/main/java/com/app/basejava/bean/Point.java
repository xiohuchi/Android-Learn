package com.app.basejava.bean;

/**
 * Author：YangBin
 * Time：2016/12/15.
 * Email：1250211588@qq.com
 * explain：
 */

public class Point {
    private int x;
    public int y;

    public Point() {
    }

    public Point(int x) {
        this.x = x;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    private void draw(String msg) {
        System.out.println("msg=" + msg);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
