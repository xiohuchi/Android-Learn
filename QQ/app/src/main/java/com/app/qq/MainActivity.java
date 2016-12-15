package com.app.qq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Tencent mTencent;
    String AppId = "1105878116";
    TextView tv_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_text = (TextView) findViewById(R.id.tv_text);
        // 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取
        // 初始化视图
//        initViews();
    }

    @Override
    public void onClick(View view) {
        Log.d(this.getClass().getName(), "点击了");
        login();
    }

    public void login() {
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance(AppId, this.getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", new BaseUiListener());
        }
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {

            TokenResultBean tokenResultBean = new Gson().fromJson(o.toString(), TokenResultBean.class);
            mTencent.setOpenId(tokenResultBean.getOpenid());
            mTencent.setAccessToken(tokenResultBean.getAccess_token(), tokenResultBean.getExpires_in()+"");
            Log.d(this.getClass().getName(), "---------------yy----------onComple11te " + o);
            UserInfo info = new UserInfo(getApplicationContext(), mTencent.getQQToken());
            info.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    UserInfoBean userInfoBean =new Gson().fromJson(o.toString(), UserInfoBean.class);
                    userInfoBean.getNickname();
                    Log.d(this.getClass().getName(),userInfoBean.getNickname()+ "---------------yyinfo----------onComplete " + o);
                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            });
            showResult("《onComplete", "----------------->>>>");
        }

        @Override
        public void onError(UiError e) {
            Log.d(this.getClass().getName(), "BaseUiListener onError " + e);
            showResult("onError:", "code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail);
            tv_text.setText("onError:" + "\ncode:" + e.errorCode + "\nmsg:"
                    + e.errorMessage + "\ndetail:" + e.errorDetail);
        }

        @Override
        public void onCancel() {
            Log.d(this.getClass().getName(), "BaseUiListener onCancel");
            showResult("onCancel", "");
            tv_text.setText("onCancel");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(this.getClass().getName(), "onActivityResult requestCode=" + requestCode +
                ",resultCode=" + resultCode +
                ",data=" + data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
    }

    private void showResult(String s, String message) {
        Log.d(this.getClass().getName(), "s=" + s + ",message=" + message);
        tv_text.setText(s + "\n" + message);
    }

    /**
     * QQ注销接口
     */
    public void logout() {
        mTencent.logout(this);
    }
}
