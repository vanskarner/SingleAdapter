package com.utility.adapters.examples.listener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.utility.adapters.R;

import com.utility.adapters.examples.DataProvider;
import com.utility.adapters.examples.WomanModel;
import com.vanskarner.singleadapter.SingleAdapter;

public class ListenerActivity extends AppCompatActivity {

    SingleAdapter singleAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listener_activity);
        recyclerView = findViewById(R.id.recycler);
        showSimpleListenerExample();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listener, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.simpleListenerItem) {
            showSimpleListenerExample();
        } else {
            showMultiListenerExample();
        }
        return false;
    }

    private void showSimpleListenerExample() {
        SimpleListenerAdapter simpleListenerAdapter = new SimpleListenerAdapter();
        singleAdapter = new SingleAdapter();
        simpleListenerAdapter.setListener(position -> {
            WomanModel item = singleAdapter.getItem(position);
            showMessage(item.toString());
        });
        singleAdapter.add(simpleListenerAdapter);
        singleAdapter.set(DataProvider.sampleData());
        recyclerView.setAdapter(singleAdapter);
    }

    private void showMultiListenerExample() {
        MultiListenerAdapter multiListenerAdapter = new MultiListenerAdapter();
        singleAdapter = new SingleAdapter();
        multiListenerAdapter.setListener(new MultiListenerAdapter.OnClickMultiListener() {
            @Override
            public void onClickItem(int position) {
                WomanModel item = singleAdapter.getItem(position);
                showMessage("onClickItem->" + item.toString());
            }

            @Override
            public void onClickImageItem(int position) {
                WomanModel item = singleAdapter.getItem(position);
                showMessage("onClickImageItem->" + item.toString());
            }

            @Override
            public void onClickNameItem(int position) {
                WomanModel item = singleAdapter.getItem(position);
                showMessage("onClickNameItem->" + item.toString());
            }
        });
        singleAdapter.add(multiListenerAdapter);
        singleAdapter.set(DataProvider.sampleData());
        recyclerView.setAdapter(singleAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
