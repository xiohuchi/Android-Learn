package com.finddreams.retrofit.api.interaction;


import com.finddreams.retrofit.api.net.BaseSubsribe;
import com.finddreams.retrofit.api.service.WeatherApiService;
import com.finddreams.retrofit.bean.WeatherResultBean;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 获取天气信息实现类
 */
public class WeatherInteractorImpl implements WeatherInteractor {
    private final WeatherApiService api;

    @Inject
    public WeatherInteractorImpl(WeatherApiService myApi) {
        this.api = myApi;
    }


    @Override
    public Subscription queryWeather(String apikey, String cityname, BaseSubsribe<WeatherResultBean> subsribe) {
        Observable<WeatherResultBean> observable = api.queryWeather(apikey, cityname);
        Subscription subscribe = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscribe;
    }
}
