package com.vanskarner.adapters.common.bases;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract void injectPresenter();

}
