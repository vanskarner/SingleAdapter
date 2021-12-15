package com.vanskarner.adapters.examples.simple;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.SingleAdapter;
import com.vanskarner.adapters.examples.DataProvider;

public class SimpleActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
        recyclerView = findViewById(R.id.recyclerSimple);
        showLinearExample();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.linearItem) {
            showLinearExample();
        } else if (item.getItemId() == R.id.gridItem) {
            showGridExample();
        } else {
            showMultiViewExample();
        }
        return false;
    }

    private void showMultiViewExample() {
        SingleAdapter singleAdapter = createSingleAdapter();
        singleAdapter.add(new MultiOneAdapter());
        singleAdapter.add(new MultiSecondAdapter());
        singleAdapter.changeList(DataProvider.sampleData());
        LinearLayoutManager manager = createLinearLayoutManager();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(singleAdapter);
    }

    private void showGridExample() {
        SingleAdapter singleAdapter = createSingleAdapter();
        singleAdapter.add(new GridAdapter());
        singleAdapter.changeList(DataProvider.sampleData());
        GridLayoutManager manager = createGridLayoutManager();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(singleAdapter);
    }

    private void showLinearExample() {
        SingleAdapter singleAdapter = createSingleAdapter();
        singleAdapter.add(new LinearAdapter());
        singleAdapter.add(new LinearAdapter());
        singleAdapter.add(new LinearAdapter());
        singleAdapter.changeList(DataProvider.sampleData());
        LinearLayoutManager manager = createLinearLayoutManager();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(singleAdapter);
    }

    private SingleAdapter createSingleAdapter() {
        return new SingleAdapter();
    }

    private LinearLayoutManager createLinearLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    private GridLayoutManager createGridLayoutManager() {
        return new GridLayoutManager(this, 2);
    }

}
