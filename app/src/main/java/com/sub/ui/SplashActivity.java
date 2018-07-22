package com.sub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sub.R;
import com.sub.ui.BaseActivity;

public class SplashActivity extends BaseActivity {

    /**
     * Hold splash screen timeout
     */
    private final int DELAY_TIME_IN_MS = 2000;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(mSplashRunnable, DELAY_TIME_IN_MS);
    }

    /**
     * post delay action
     */
    private Runnable mSplashRunnable = new Runnable() {
        @Override
        public void run() {

            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    };
}
