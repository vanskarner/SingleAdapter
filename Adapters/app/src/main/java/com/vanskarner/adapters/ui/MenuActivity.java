package com.vanskarner.adapters.ui;

import android.content.Intent;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.ui.grid_pagination.GridPaginationActivity;
import com.vanskarner.adapters.ui.search_pagination.SearchPaginationActivity;
import com.vanskarner.adapters.ui.simple_pagination.SimplePaginationActivity;
import com.vanskarner.adapters.ui.simple_search.SimpleSearchActivity;
import com.vanskarner.adapters.ui.staggered_pagination.StaggeredPaginationActivity;

public class MenuActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.menu_activity;
    }

    @Override
    protected void setupView() {
        findViewById(R.id.btnSimplePagination)
                .setOnClickListener(view -> {
                    Intent intent = new Intent(this, SimplePaginationActivity.class);
                    startActivity(intent);
                });
        findViewById(R.id.btnGridPagination)
                .setOnClickListener(view -> {
                    Intent intent = new Intent(this, GridPaginationActivity.class);
                    startActivity(intent);
                });
        findViewById(R.id.btnStaggeredPagination)
                .setOnClickListener(view -> {
                    Intent intent = new Intent(this, StaggeredPaginationActivity.class);
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
