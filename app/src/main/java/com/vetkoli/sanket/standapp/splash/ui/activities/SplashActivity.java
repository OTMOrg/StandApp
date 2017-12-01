package com.vetkoli.sanket.standapp.splash.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vetkoli.sanket.standapp.R;
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity;

/**
 * Created by Sanket on 30/11/17.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
