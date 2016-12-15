package com.finddreams.retrofit.api.interaction;


import com.finddreams.retrofit.api.net.BaseSubsribe;
import com.finddreams.retrofit.bean.WeatherResultBean;

import rx.Subscription;

/**
 * 获取天气信息接口
 *
 * @author finddreams
 * @address http://blog.csdn.net/finddreams
 */
public interface WeatherInteractor {
    Subscription queryWeather(String apikey, String cityname, BaseSubsribe<WeatherResultBean> subsribe);
}
