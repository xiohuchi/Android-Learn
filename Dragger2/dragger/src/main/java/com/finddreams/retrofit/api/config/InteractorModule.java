package com.finddreams.retrofit.api.config;


import com.finddreams.retrofit.api.interaction.WeatherInteractor;
import com.finddreams.retrofit.api.interaction.WeatherInteractorImpl;
import com.finddreams.retrofit.api.net.RestApiAdapter;
import com.finddreams.retrofit.api.service.WeatherApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Module类
 * 提供需要注入的类
 *
 * @author finddreams
 * @address http://blog.csdn.net/finddreams
 */
@Module
public class InteractorModule {
    @Provides
    public Retrofit provideRestAdapter() {
        return RestApiAdapter.getInstance();
    }

    @Provides
    public WeatherApiService provideHomeApi(Retrofit restAdapter) {
        return restAdapter.create(WeatherApiService.class);
    }

    @Provides
    public WeatherInteractor provideHomeInteractor(WeatherApiService myApi) {
        return new WeatherInteractorImpl(myApi);
    }
}
