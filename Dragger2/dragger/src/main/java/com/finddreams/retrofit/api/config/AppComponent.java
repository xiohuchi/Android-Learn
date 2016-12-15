package com.finddreams.retrofit.api.config;


import com.finddreams.retrofit.App;
import com.finddreams.retrofit.api.interaction.WeatherInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 声明AppComponent组件
 *
 * @author finddreams
 * @address http://blog.csdn.net/finddreams
 */
@Singleton
@Component(
        modules = {
                InteractorModule.class,
        }
)
public interface AppComponent {
    void inject(App app);

    WeatherInteractor getWeatherInteractor();
}
