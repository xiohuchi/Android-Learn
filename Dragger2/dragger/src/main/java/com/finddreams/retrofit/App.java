package com.finddreams.retrofit;

import android.app.Application;
import android.content.Context;

import com.finddreams.retrofit.api.config.AppComponent;
import com.finddreams.retrofit.api.config.DaggerAppComponent;
import com.finddreams.retrofit.api.config.InteractorModule;

/**
 * Application类
 *
 * @author finddreams
 * @address http://blog.csdn.net/finddreams
 */
public class App extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        setDraggerConfig();
    }

    public AppComponent component() {
        return component;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    /**
     * 初始化Dragger，DaggerAppComponent是自动生成，需要Rebuild
     */
    private void setDraggerConfig() {
        component = DaggerAppComponent.builder().interactorModule(new InteractorModule())
                .build();
        component.inject(this);
    }
}
