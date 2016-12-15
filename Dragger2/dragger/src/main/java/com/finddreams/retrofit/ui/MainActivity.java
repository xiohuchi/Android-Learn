package com.finddreams.retrofit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.finddreams.retrofit.App;
import com.finddreams.retrofit.R;
import com.finddreams.retrofit.api.config.AppComponent;
import com.finddreams.retrofit.api.config.ConstantApi;
import com.finddreams.retrofit.api.interaction.WeatherInteractor;
import com.finddreams.retrofit.api.net.BaseSubsribe;
import com.finddreams.retrofit.bean.WeatherResultBean;

import rx.Subscription;

/**
 * 主页
 *
 * @author finddreams
 * @address http://blog.csdn.net/finddreams
 */
public class MainActivity extends AppCompatActivity {


    private AppComponent component;
    private WeatherInteractor weatherInteractor;
    private EditText city;
    private TextView queryresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (EditText) findViewById(R.id.city);
        queryresult = (TextView) findViewById(R.id.queryresult);
        //获取到AppComponent组件
        component = App.get(this).component();
        //通过AppComponent拿到WeatherInteractor
        weatherInteractor = component.getWeatherInteractor();
        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryWeatherData();

            }

        });


    }

    public void queryWeatherData() {
        String content = city.getText().toString();
        //调用查询天气接口的方法
            Subscription subscription = weatherInteractor.queryWeather(
                    ConstantApi.baiduKey, content, new BaseSubsribe<WeatherResultBean>() {
                    @Override
                    public void onSuccess(WeatherResultBean result) {

                        WeatherResultBean.RetDataEntity retData = result.getRetData();
                        queryresult.setText(retData.getCity() + ":" + retData.getWeather() + ":" + retData.getDate());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        queryresult.setText("查询失败");

                    }
                }
        );
        //取消请求
//        subscription.unsubscribe();
    }
}
