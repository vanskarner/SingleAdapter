package com.vanskarner.adapters.examples.simple;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.SingleAdapter;


public class SimpleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SingleAdapter singleAdapter = new SingleAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_pagination_activity);
        recyclerView = findViewById(R.id.recyclerPersons);
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
        singleAdapter.setList(DataProvider.sampleData());
    }

    private void showGridExample() {
        singleAdapter.setList(DataProvider.sampleData());
    }

    private void showLinearExample() {
        singleAdapter.setList(DataProvider.sampleData());
    }
}
