package com.utility.adapters.examples;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.utility.adapters.R;
import com.utility.adapters.examples.diff_util.DiffUtilActivity;
import com.utility.adapters.examples.listener.ListenerActivity;
import com.utility.adapters.examples.progress.ProgressActivity;
import com.utility.adapters.examples.simple.SimpleActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        findViewById(R.id.btnSimple)
                .setOnClickListener(view -> goActivity(SimpleActivity.class));
        findViewById(R.id.btnListener)
                .setOnClickListener(view -> goActivity(ListenerActivity.class));
        findViewById(R.id.btnDiffUtil)
                .setOnClickListener(view -> goActivity(DiffUtilActivity.class));
        findViewById(R.id.btnProgress)
                .setOnClickListener(view -> goActivity(ProgressActivity.class));
    }

    private void goActivity(Class<? extends AppCompatActivity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}
