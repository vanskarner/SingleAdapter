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

import java.util.List;

public class ListenerActivity extends AppCompatActivity {

    List<WomanModel> list = DataProvider.sampleData();
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
        SimpleListenerAdapter adapter = new SimpleListenerAdapter(position -> {
            WomanModel item = list.get(position);
            showMessage(item.toString());
        });
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(adapter);
        singleAdapter.set(list);
        recyclerView.setAdapter(singleAdapter);
    }

    private void showMultiListenerExample() {
        MultiListenerAdapter adapter = new MultiListenerAdapter(new MultiListenerAdapter
                .OnClickMultiListener() {
            @Override
            public void onClickItem(int position) {
                WomanModel item = list.get(position);
                showMessage("onClickItem->" + item.toString());
            }

            @Override
            public void onClickImageItem(int position) {
                WomanModel item = list.get(position);
                showMessage("onClickImageItem->" + item.toString());
            }

            @Override
            public void onClickNameItem(int position) {
                WomanModel item = list.get(position);
                showMessage("onClickNameItem->" + item.toString());
            }
        });
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(adapter);
        singleAdapter.set(list);
        recyclerView.setAdapter(singleAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
