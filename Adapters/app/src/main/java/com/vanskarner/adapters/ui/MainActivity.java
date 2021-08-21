package com.vanskarner.adapters.ui;

import android.content.Intent;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.common.bases.SearchPaginationActivity;
import com.vanskarner.adapters.ui.simple_pagination.SimplePaginationActivity;
import com.vanskarner.adapters.ui.simple_search.SimpleSearchActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.main_activity;
    }

    @Override
    protected void setupView() {
        findViewById(R.id.btnSimplePagination)
                .setOnClickListener(view -> {
                    Intent intent = new Intent(this, SimplePaginationActivity.class);
                    startActivity(intent);
                });
        findViewById(R.id.btnSimpleSearch)
                .setOnClickListener(view -> {
                    Intent intent = new Intent(this, SimpleSearchActivity.class);
                    startActivity(intent);
                });
        findViewById(R.id.btnSearchPagination)
                .setOnClickListener(view -> {
                    Intent intent = new Intent(this, SearchPaginationActivity.class);
                    startActivity(intent);
                });
    }


}
