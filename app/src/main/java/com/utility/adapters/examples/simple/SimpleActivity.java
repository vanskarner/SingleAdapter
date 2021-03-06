package com.utility.adapters.examples.simple;

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

import com.utility.adapters.R;

import com.utility.adapters.examples.DataProvider;
import com.vanskarner.singleadapter.SingleAdapter;

public class SimpleActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
        recyclerView = findViewById(R.id.recycler);
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

    private void showLinearExample() {
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new LinearAdapter());
        singleAdapter.set(DataProvider.sampleData());
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(singleAdapter);
    }

    private void showGridExample() {
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new GridAdapter());
        singleAdapter.set(DataProvider.sampleData());
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(singleAdapter);
    }

    private void showMultiViewExample() {
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new MultiOneAdapter());
        singleAdapter.add(new MultiSecondAdapter());
        singleAdapter.set(DataProvider.sampleData());
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(singleAdapter);
    }

}
